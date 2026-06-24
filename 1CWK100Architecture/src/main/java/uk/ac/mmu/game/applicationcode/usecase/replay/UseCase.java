package uk.ac.mmu.game.applicationcode.usecase.replay;


import uk.ac.mmu.game.applicationcode.usecase.Required;

public class UseCase implements Provided {

    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public void replay(int gameId) {

    }
}
