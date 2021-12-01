package Twenty.DayEight;


public abstract class Instruction {
    int increment;

    public Instruction(int inc)
    {
        increment = inc;
    }

    public abstract MachineState execute(MachineState state);

}
