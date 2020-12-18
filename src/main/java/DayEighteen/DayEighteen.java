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
    static final Pattern PARENS = Pattern.compile("(?=\\()(?:(?=.*?\\((?!.*?\\1)(.*\\)(?!.*\\2).*))(?=.*?\\)(?!.*?\\2)(.*)).)+?.*?(?=\\1)[^(]*(?=\\2$)");
    static Matcher operationMatcher;
    static Matcher numberMatcher;
    static Matcher parensMatcher;

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("input18");

        long sum = 0;
        for (String line : inputLines) {
            line = line.replace(" ", "");

            long value = evaluate(line);

            System.out.println("\tResult: " + value);
            sum += value;
        }
        System.out.println("*************************************************");
        System.out.println("********Sum: " + sum + "***********");
    }

    static long evaluate(String expression)
    {
        System.out.println("Now Evaluating " + expression);
        parensMatcher = PARENS.matcher(expression);

        while(parensMatcher.find())
        {
            String interior = parensMatcher.group();
            long parenResult = evaluate(interior.substring(1, interior.length()-1));

            String newExp = expression.replace(interior, String.valueOf(parenResult));
            expression = newExp;
            System.out.println("Now evaluating " + expression);
            parensMatcher = PARENS.matcher(expression);
        }


        numberMatcher = NUMBER.matcher(expression);
        operationMatcher = MULT_OR_ADD.matcher(expression);

        Queue<Long> numbers = new LinkedList<>();

        while (numberMatcher.find()) {
            numbers.add(Long.parseLong(numberMatcher.group()));
        }

        Queue<Character> ops = new LinkedList<>();

        while (operationMatcher.find()) {
            ops.add(operationMatcher.group().charAt(0));
        }
        long result = numbers.poll();
        while(!ops.isEmpty())
        {
            result = eval(result,  numbers.poll(), ops.poll());

        }

        return result;
    }

    static long eval(long a, long b, char op)
    {

        long result = 0;
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


