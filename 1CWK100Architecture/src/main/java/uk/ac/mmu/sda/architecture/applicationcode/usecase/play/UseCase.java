package uk.ac.mmu.sda.architecture.applicationcode.usecase.play;


import uk.ac.mmu.sda.architecture.applicationcode.usecase.Required;

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
