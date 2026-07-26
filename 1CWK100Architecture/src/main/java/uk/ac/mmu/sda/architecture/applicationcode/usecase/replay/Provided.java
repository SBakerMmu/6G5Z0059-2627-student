package uk.ac.mmu.sda.architecture.applicationcode.usecase.replay;


import uk.ac.mmu.sda.architecture.applicationcode.usecase.Required;

public interface Provided {
    void replay(int gameId);

    static Provided getInstance(Required required) {
        return new UseCase(required);
    }

}
