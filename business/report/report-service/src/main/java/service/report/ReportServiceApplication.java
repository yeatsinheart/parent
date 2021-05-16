package service.report;
import com.common.utils.ShutDown;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@ComponentScan(basePackages={
        "service.report.services",
        "com.common.annotation",
        "com.db.config",
        "db.report.daos.impls",
        "com.common.pool",
        "com.redis"
})
public class ReportServiceApplication {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDown());
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ReportServiceApplication.class, args);
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
