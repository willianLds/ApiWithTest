package io.github.willianlds.apiWithTest.config;

import io.github.willianlds.apiWithTest.domain.User;
import io.github.willianlds.apiWithTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "Willian Lucas", "willian.lucas10@hotmail.com", "123");
        User u2 = new User(null, "Lucas", "lucas10@hotmail.com", "1233");

        repository.saveAll(List.of(u1,u2));
    }
}
