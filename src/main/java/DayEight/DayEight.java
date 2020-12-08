package DayEight;

import AdventUtil.AdventUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DayEight {
    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input8");

        List<Instruction> instructions = new ArrayList();
        for(String s : inputLines)
            instructions.add(parseInstruction(s));

        MachineState state = new MachineState();



        for(int i = 0; i < instructions.size(); i++) {
            state = new MachineState();
            Stack<Instruction> callStack = new Stack();
            instructions.set(i, swap(instructions.get(i)));

            state = executeInstructions(instructions, state, callStack);
            if(state.succeeded) {
                System.out.println("Swapped operation on line " + callStack.size()+1 + " (" + inputLines.get(callStack.size()+1) + ")");
                break;
            }

            instructions.set(i, swap(instructions.get(i)));
        }


        System.out.println("State at start of reloop: " + state);

    }

    private static MachineState executeInstructions(List<Instruction> instructions, MachineState state, Stack<Instruction> callStack) {
        Instruction i = instructions.get(0);
        while(!callStack.contains(i))
        {
            state = i.execute(state);
            callStack.push(i);
            if(state.instructionPointer == instructions.size()) {
                state.succeeded = true;
                return state;
            }
            else if(state.instructionPointer > instructions.size())
                return state;
            i = instructions.get(state.instructionPointer);
        }

        return state;
    }

    public static Instruction parseInstruction(String input)
    {
        switch(input.substring(0, 3))
        {
            case "nop": return new NoOp(Integer.parseInt(input.substring(input.indexOf(" ")+1)));
            case "jmp": return new Jump(Integer.parseInt(input.substring(input.indexOf(" ")+1)));
            case "acc": return new Add(Integer.parseInt(input.substring(input.indexOf(" ")+1)));
        }
        return null;
    }

    public static Instruction swap(Instruction in)
    {
        if(in instanceof NoOp)
            return new Jump(in.increment);
        if(in instanceof Jump)
            return new NoOp(in.increment);
        return in;
    }

}

