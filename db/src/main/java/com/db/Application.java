package com.db;

import com.common.utils.ShutDown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDown());
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        try {
            // ===== 在项目初始化bean后检验数据库连接是否
            DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
            Connection connection = dataSource.getConnection();
            connection.close();
        } catch (Exception e) {
            System.exit(-1);
        }
    }

}
