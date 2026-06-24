package uk.ac.mmu.game.applicationcode.usecase.play;

import uk.ac.mmu.game.applicationcode.usecase.Required;

public interface Provided {
    int play(); //Play the game and return the id of the played game

    static Provided getInstance(Required required)
    {
        return new UseCase(required);
    }

}
