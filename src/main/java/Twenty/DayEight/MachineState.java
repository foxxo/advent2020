package Twenty.DayEight;

public class MachineState {

    int instructionPointer;
    int accumulator;
    boolean succeeded;

    public String toString()
    {
        return "instructionPointer: " + instructionPointer + ", accumulator: " + accumulator;
    }
}
