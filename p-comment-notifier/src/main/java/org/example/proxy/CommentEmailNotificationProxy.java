package org.example.proxy;

import lombok.extern.slf4j.Slf4j;
import org.example.Comment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Qualifier("EMAIL")
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CommentEmailNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendNotification(Comment comment) {
      log.info("Email notification {}", comment);
    }
}
