# 1CWK100 Clean Architecture Starter Code


The main() method launches the Spring Boot application.

```Java
@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {

        SpringApplication.run(GameApplication.class, args);
	}

}
```

The Spring Boot application scans for components (any class annotated with `@Component`) and configuration classes (any class annotated with `@Configuration`)in the same package and sub-packages of the class with the main() method.

Classes annotated with `@Configuration` are used to define Beans that will be managed by the Spring Boot application. Beans are objects that are instantiated, assembled and  managed by the Spring dependency injection (DI) container.

```Java
import uk.ac.mmu.sda.architecture.applicationcode.usecase.replay.Provided;
import uk.ac.mmu.sda.architecture.applicationcode.usecase.replay.UseCase;

@Configuration
public class AppConfig {

    @Bean
    Required required() {
        return new Technology();
    }


    @Bean
    uk.ac.mmu.sda.architecture.applicationcode.usecase.play.Provided playUseCase(Required required) {
        return new play.usecase.applicationcode.uk.ac.mmu.sda.architecture.UseCase(required);
    }

    @Bean
    Provided replayUseCase(Required required) {
        return new replay.usecase.applicationcode.uk.ac.mmu.sda.architecture.UseCase(required);
    }

}
```

Classes annotated with `@Component` and that implementing `org.springframework.boot.CommandLineRunner` will be automatically run by the Spring Boot application. Spring Boot will pass any dependencies required into the class constructor.

As we have more than one class implementing CommandLineRunner we also need to implement the Ordered interface to control the order in which they are run.

```Java
import play.usecase.applicationcode.uk.ac.mmu.sda.architecture.Provided;

@Component
class Play implements org.springframework.boot.CommandLineRunner, Ordered {

    private final Provided usecase;

    Play(Provided usecase) {
        this.usecase = usecase;
    }


    @Override
    public void run(String... args) {
        System.out.format("Played Game Id = %d%n", usecase.play());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}


```

```java
int u
```

