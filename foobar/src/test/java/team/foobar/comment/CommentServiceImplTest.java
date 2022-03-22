package team.foobar.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Comment;
import team.foobar.service.comment.CommentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceImplTest {
    @Autowired
    CommentService service;

    @Test
    void searchByBoardId() {
        List<Comment> list = service.searchByBoardId(1);

        for (Comment comment : list) {
            System.out.println("comment = " + comment);

            List<Comment> childList = comment.getChildList();
            for (Comment child : childList) {
                System.out.println("child = " + child);
            }
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}