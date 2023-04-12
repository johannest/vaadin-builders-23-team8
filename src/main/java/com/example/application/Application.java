package com.example.application;

import com.example.application.data.generator.DataLoader;
import com.example.application.data.service.TopicRepository;
import com.example.application.data.service.UpVoteRepository;
import com.example.application.data.service.VaadinerRepository;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = "ssodemo")
@PWA(name = "SSO Demo", shortName = "SSO Demo", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner loadData(TopicRepository topicRepository, UpVoteRepository upVoteRepository,
                                      VaadinerRepository vaadinerRepository) {
        return args -> new DataLoader(topicRepository, upVoteRepository, vaadinerRepository);
    }

}
