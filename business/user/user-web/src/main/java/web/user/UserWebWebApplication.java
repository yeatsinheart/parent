package web.user;

import com.common.utils.ShutDown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import javax.sql.DataSource;
import java.sql.Connection;

@ComponentScan(basePackages={
        "web.user.controller",
        "service.user.services",
        "com.db.config",
        "db.user.daos.impls",
        "com.redis",
        "com.common.pool",
        "com.common.annotation"
})
@SpringBootApplication
public class UserWebWebApplication {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDown());
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserWebWebApplication.class, args);
        try {
            /*String[] beans = applicationContext
                    .getBeanDefinitionNames();
            for (String beanName : beans) {
                Class<?> beanType = applicationContext
                        .getType(beanName);
                System.out.println("BeanName:" + beanName);
                System.out.println("Bean的类型：" + beanType);
                System.out.println("Bean所在的包：" + beanType.getPackage());
                System.out.println("Bean：" + applicationContext.getBean(
                        beanName));
            }*/
            // ===== 在项目初始化bean后检验数据库连接是否
            DataSource dataSource = (DataSource) applicationContext.getBean("shardingDataSource");
            Connection connection = dataSource.getConnection();
            connection.close();
        } catch (Exception e) {
            System.exit(-1);
        }
    }

}
