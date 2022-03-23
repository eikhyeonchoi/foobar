package team.foobar.repository.jpa.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Comment;
import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QBoard.board;
import static team.foobar.domain.QComment.comment;
import static team.foobar.domain.QMember.member;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public List<Comment> findByBoardId(Integer id) {
        List<Comment> list = factory.selectFrom(comment)
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .leftJoin(comment.tagMember, member).fetchJoin()
                .where(comment.board.id.eq(id), comment.parent.id.isNull())
                .orderBy(comment.createDt.asc())
                .fetch();

        list.forEach(el -> {
            el.replaceChild(this.findChildById(el.getId()));
        });

        return list;
    }

    /**
     * 자식 comment get
     * @param parentId 부모
     * @return List
     */
    @Override
    public List<Comment> findChildById(Integer parentId) {
        return factory.selectFrom(comment)
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .leftJoin(comment.tagMember, member).fetchJoin()
                .where(comment.parent.id.eq(parentId))
                .orderBy(comment.createDt.asc())
                .fetch();
    }

    @Override
    public Optional<Comment> findByIdWithFetch(Integer id) {
        return Optional.ofNullable(factory.selectFrom(comment)
                .leftJoin(comment.parent).fetchJoin()
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .leftJoin(comment.tagMember, member).fetchJoin()
                .where(comment.id.eq(id))
                .fetchOne()
        );
    }

    @Override
    public Page<Comment> findByMemberIdWithFetch(Integer id, Pageable pageable) {
        List<Comment> list = factory.selectFrom(comment)
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .join(comment.tagMember, member).fetchJoin()
                .where(comment.member.id.eq(id))
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }
}
