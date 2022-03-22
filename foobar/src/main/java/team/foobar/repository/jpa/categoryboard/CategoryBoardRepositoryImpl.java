package team.foobar.repository.jpa.categoryboard;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;
import team.foobar.domain.CategoryBoard;

import java.util.List;

import static team.foobar.domain.QBoard.board;
import static team.foobar.domain.QCategoryBoard.*;

@RequiredArgsConstructor
public class CategoryBoardRepositoryImpl implements CategoryBoardRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Page<CategoryBoard> findBoardByCategoryId(Integer id, Pageable pageable) {
        List<CategoryBoard> list = factory.selectFrom(categoryBoard)
                .join(categoryBoard.board, board).fetchJoin()
                .where(categoryBoard.category.id.eq(id)).fetchJoin()
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }
}
