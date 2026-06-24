package uk.ac.mmu.game.applicationcode.usecase.play;


import uk.ac.mmu.game.applicationcode.usecase.Required;

public class UseCase implements Provided {

    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public int play() {
        return 0;
    }
}
