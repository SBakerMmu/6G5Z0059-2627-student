package uk.ac.mmu.game;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.mmu.game.applicationcode.usecase.Required;
import uk.ac.mmu.game.infrastructure.Technology;

@Configuration
public class AppConfig
{

    @Bean
    Required required()
    {
        return new Technology();
    }


    @Bean
    uk.ac.mmu.game.applicationcode.usecase.play.Provided playUseCase(Required required)
    {
        return new uk.ac.mmu.game.applicationcode.usecase.play.UseCase(required);
    }

    @Bean
    uk.ac.mmu.game.applicationcode.usecase.replay.Provided replayUseCase(Required required)
    {
        return new uk.ac.mmu.game.applicationcode.usecase.replay.UseCase(required);
    }

}
