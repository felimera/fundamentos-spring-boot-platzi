package com.fundamentosplatzi.sprintboot.fundamentos.configuration;

import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosplatzi.sprintboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }
}
