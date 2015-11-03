package gxc.rcalc.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("hibernate-context.xml")
@ComponentScan("gxc.rcalc.rest.repository")
public class PersistenceConfig {

}
