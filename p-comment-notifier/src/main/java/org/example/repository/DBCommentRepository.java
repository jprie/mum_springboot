package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.Comment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBCommentRepository implements CommentRepository {


    @Override
    public void save(Comment comment) {
        log.info("Save in repository {}", comment);
    }
}
