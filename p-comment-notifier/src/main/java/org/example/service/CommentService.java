package org.example.service;

import org.example.Comment;
import org.example.processor.CommentProcessor;
import org.example.proxy.CommentNotificationProxy;
import org.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// Durch diese Annotation wird im context ein Bean "commentService" erstellt und
// versucht beans vom Typ CommentRepository und CommentNotificationProxy zu
// injizieren.
@Component
public class CommentService {

    // Ein Singleton-Bean ist IMMUTABLE wenn all seine fields final sind!
    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    private final ApplicationContext context;

    @Autowired // optional
    public CommentService(CommentRepository commentRepository,
                          @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy,
                          ApplicationContext context) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
        this.context = context;
    }

    public void receiveMessage(Comment comment) {

        /*
            Nur über den ApplicationContext bekomme ich bei jedem Aufruf eine neue
            Instanz des CommentProcessor.
            Sonst wird bei normaler Injection nur genau 1x ein getBean() aufgerufen und
            damit eine neue Instanz des Prototype erstellt.
         */
        var commentProcessor = context.getBean(CommentProcessor.class);
//        commentRepository.save(comment);
        commentProcessor.setComment(comment);
        commentProcessor.validate();
        commentProcessor.save();
        // TODO: process message and save with processor
        commentNotificationProxy.sendNotification(comment);

    }
}
