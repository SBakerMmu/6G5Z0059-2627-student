package uk.ac.mmu.sda.architecture.applicationcode.usecase.play;

import uk.ac.mmu.sda.architecture.applicationcode.usecase.Required;

public interface Provided {
    int play(); //Play the game and return the id of the played game

    static Provided getInstance(Required required)
    {
        return new UseCase(required);
    }

}
