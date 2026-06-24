package uk.ac.mmu.game;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.applicationcode.usecase.play.Provided;

@Component
class Play implements org.springframework.boot.CommandLineRunner, Ordered {

    private final Provided usecase;

    Play(Provided usecase) {
        this.usecase = usecase;
    }


    @Override
    public void run(String... args)  {
        System.out.format("Played Game Id = %d%n", usecase.play());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
