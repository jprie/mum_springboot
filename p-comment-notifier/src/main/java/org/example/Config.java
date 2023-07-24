package org.example;

import org.example.proxy.CommentEmailNotificationProxy;
import org.example.proxy.CommentNotificationProxy;
import org.example.proxy.CommentPushNotificationProxy;
import org.example.repository.CommentRepository;
import org.example.repository.DBCommentRepository;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.service", "org.example.proxy", "org.example.repository"})
public class Config {

//    @Bean
//    public CommentService commentService(CommentRepository repository,
//                                         @Qualifier("PUSH") CommentNotificationProxy proxy) {
//
//        return new CommentService(repository, proxy);
//    }
//
//    @Bean(name = "EMAIL")
//    public CommentNotificationProxy commentEmailNotificationProxy() {
//
//        return new CommentEmailNotificationProxy();
//    }
//
//    @Bean(name = "PUSH")
//    public CommentNotificationProxy commentPushNotificationProxy() {
//
//        return new CommentPushNotificationProxy();
//    }
//
//    @Bean
//    public CommentRepository dbCommentRepository() {
//
//        return new DBCommentRepository();
//    }
}
