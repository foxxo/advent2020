package DayEighteen;

import AdventUtil.AdventUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEighteen {

    static final Pattern MULT_OR_ADD = Pattern.compile("[+*]");
    static final Pattern NUMBER = Pattern.compile("[0-9]+");
    static Matcher operationMatcher;
    static Matcher numberMatcher;

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("test18");

        String line = inputLines.get(0).replace(" ", "");

        int value = evaluate(line);

        System.out.println("Result: " + value);
    }

    static int evaluate(String expression)
    {
        numberMatcher = NUMBER.matcher(expression);
        operationMatcher = MULT_OR_ADD.matcher(expression);
        Queue<Integer> numbers = new LinkedList<>();

        while (numberMatcher.find()) {
            numbers.add(Integer.parseInt(numberMatcher.group()));
        }

        Queue<Character> ops = new LinkedList<>();

        while (operationMatcher.find()) {
            ops.add(operationMatcher.group().charAt(0));
        }
        int result = numbers.poll();
        while(!ops.isEmpty())
        {
            result = eval(result,  numbers.poll(), ops.poll());

        }

        return result;
    }

    static int eval(int a, int b, char op)
    {

        int result = 0;
        System.out.print(a + " " +  op + " " + b + " = ");
        switch(op)
        {
            case '+':
                result =  a + b;
                break;
            case '*':
                result = a * b;
                break;
        }
        System.out.println(result);
        return result;
    }
}


