package com.fundamentosplatzi.sprintboot.fundamentos;

import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.sprintboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;

    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
        this.myBeanWithProperties = myBeanWithProperties;
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.userPojo = userPojo;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
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
