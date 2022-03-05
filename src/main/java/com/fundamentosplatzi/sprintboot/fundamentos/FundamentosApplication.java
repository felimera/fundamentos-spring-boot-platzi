package com.fundamentosplatzi.sprintboot.fundamentos;

import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.sprintboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.sprintboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;

    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;

    private UserRepository userRepository;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {
        this.myBeanWithProperties = myBeanWithProperties;
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        this.ejemplosAnteriores();
        this.saveUsersInDataBase();
        this.getInformationJpqlFromUser();
    }

    private void getInformationJpqlFromUser() {
        LOGGER.info("Usuario con el metodo findByUserEmail : " +
                userRepository.findByUserEmail("andres@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el usuario.")));

        userRepository.findAndSourt("A", Sort.by("id").descending())
                .stream()
                .forEach(user -> LOGGER.info("Usuario con metodo sort : " + user));
    }

    private void saveUsersInDataBase() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 03, 20));
        User user2 = new User("Andres", "andres@domain.com", LocalDate.of(1989, 05, 21));
        User user3 = new User("Julian", "julian@domain.com", LocalDate.of(1999, 05, 21));
        User user4 = new User("Maria", "maria@domain.com", LocalDate.of(2001, 05, 21));
        User user5 = new User("Jonas", "jonas@domain.com", LocalDate.of(1888, 05, 21));
        User user6 = new User("Thor", "thor@domain.com", LocalDate.of(2014, 05, 21));
        User user7 = new User("Lupe", "lupe@domain.com", LocalDate.of(2011, 05, 21));
        User user8 = new User("TuMadre", "tumadre@domain.com", LocalDate.of(2013, 05, 21));
        User user9 = new User("Lucho Portuano", "luchoandres@domain.com", LocalDate.of(2015, 05, 21));
        User user10 = new User("Mehdi Duro", "mehdi@domain.com", LocalDate.of(2021, 05, 21));
        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
        userList.stream().forEach(userRepository::save);
    }

    private void ejemplosAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println("Email : " + userPojo.getEmail().concat(" Password :").concat(userPojo.getPassword()).concat(" Edad :").concat(Integer.toString(userPojo.getAge())));
        try {
            int value = 10 / 0;
            LOGGER.info("Mi valor : " + value);
        } catch (Exception e) {
            LOGGER.error("Esto al dividir por cero. " + e.getMessage());
        }
    }
}
