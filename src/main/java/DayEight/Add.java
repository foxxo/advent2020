package DayEight;

public class Add extends Instruction {

    public Add(int inc)
    {
        super(inc);
    }
    @Override
    public MachineState execute(MachineState state) {
        state.accumulator += increment;
        state.instructionPointer++;
        return state;
    }
}
