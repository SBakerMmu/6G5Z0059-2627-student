package uk.ac.mmu.game;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import uk.ac.mmu.game.applicationcode.usecase.replay.Provided;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class Replay implements org.springframework.boot.CommandLineRunner, Ordered {

    private final Provided usecase;

    Replay(Provided usecase) {
        this.usecase = usecase;
    }


    @Override
    public void run(String... args)  {

        System.out.println("Enter a Game Id to replay.");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int input = scanner.nextInt();
        try {
            usecase.replay(input);
        } catch (Exception e) {
            System.out.println("Error during replay: " + e.getMessage());
        }
        scanner.close();

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
