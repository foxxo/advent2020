package Twenty.DayEight;

public class NoOp extends Instruction {

    public NoOp(int inc)
    {
        super(inc);
    }

    @Override
    public MachineState execute(MachineState state) {
        state.instructionPointer++;
        return state;
    }
}
