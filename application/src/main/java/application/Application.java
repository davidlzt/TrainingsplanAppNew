package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "application",
        "restcontroller",
        "services",
        "repositories",
        "entitys",
        "valueobjects",
        "strategies"
})
@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "entitys")
@Import(CorsConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
