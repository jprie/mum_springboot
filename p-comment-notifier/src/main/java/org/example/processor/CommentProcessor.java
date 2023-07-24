package org.example.processor;

import lombok.extern.slf4j.Slf4j;
import org.example.Comment;
import org.example.repository.CommentRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CommentProcessor {

    private final CommentRepository repository;

    private Comment comment;

    public CommentProcessor(CommentRepository repository) {
        this.repository = repository;
    }

    public void validate() {
      log.info("Validate comment {}", comment);
    }

    public void save() {
        log.info("Store comment {}", comment);
        repository.save(comment);
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
