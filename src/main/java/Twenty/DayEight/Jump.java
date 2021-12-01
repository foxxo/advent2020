package Twenty.DayEight;

public class Jump extends Instruction {

    public Jump(int inc)
    {
        super(inc);
    }
    @Override
    public MachineState execute(MachineState state) {
        state.instructionPointer += increment;
        return state;
    }
}
