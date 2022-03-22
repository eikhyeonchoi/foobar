package team.foobar.repository.jpa.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Comment;
import java.util.List;

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

        list.stream().forEach(el -> {
            el.replaceChild(this.findChildById(el.getId()));
        });
        return list;
    }

    private List<Comment> findChildById(Integer parentId) {
        List<Comment> childList = factory.selectFrom(comment)
                .join(comment.board, board).fetchJoin()
                .join(comment.member, member).fetchJoin()
                .leftJoin(comment.tagMember, member).fetchJoin()
                .where(comment.parent.id.eq(parentId))
                .orderBy(comment.createDt.asc())
                .fetch();
        return childList;
    }
}
