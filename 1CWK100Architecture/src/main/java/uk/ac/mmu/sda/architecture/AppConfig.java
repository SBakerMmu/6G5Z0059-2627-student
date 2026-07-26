package uk.ac.mmu.sda.architecture;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.mmu.sda.architecture.applicationcode.usecase.Required;
import uk.ac.mmu.sda.architecture.infrastructure.Technology;
import uk.ac.mmu.sda.architecture.applicationcode.usecase.replay.Provided;
import uk.ac.mmu.sda.architecture.applicationcode.usecase.replay.UseCase;

@Configuration
public class AppConfig
{

    @Bean
    Required required()
    {
        return new Technology();
    }


    @Bean
    uk.ac.mmu.sda.architecture.applicationcode.usecase.play.Provided playUseCase(Required required)
    {
        return new uk.ac.mmu.sda.architecture.applicationcode.usecase.play.UseCase(required);
    }

    @Bean
    Provided replayUseCase(Required required)
    {
        return new UseCase(required);
    }

}
