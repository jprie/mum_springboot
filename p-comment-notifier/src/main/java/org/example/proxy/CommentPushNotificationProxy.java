package org.example.proxy;

import lombok.extern.slf4j.Slf4j;
import org.example.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Qualifier("PUSH")
@Component
public class CommentPushNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendNotification(Comment comment) {
        log.info("Push notification {}", comment);
    }
}
