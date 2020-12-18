package DayEighteen;

import AdventUtil.AdventUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayEighteen {

    static final Pattern MULT_OR_ADD = Pattern.compile("[+*]");
    static Matcher operationMatcher;

    public static void main(String[] args) throws IOException {

        List<String> inputLines = AdventUtil.readInputLines("test18");

        String line = StringUtils.reverse(inputLines.get(0).replace(" ", ""));

        int value = evaluate(line);

        System.out.println("Result: " + value);
    }

    public static int evaluate(String expression)
    {
        if(expression.matches("^[0-9]+$"))
            return Integer.parseInt(expression);
        operationMatcher = MULT_OR_ADD.matcher(expression);

        int operatorIndex = operationMatcher.find() ? operationMatcher.start() : -1;
        char operator = expression.charAt(operatorIndex);
        String leftOperand = expression.substring(0, operatorIndex);
        String rightOperand = expression.substring(operatorIndex+1);

        switch(operator)
        {
            case '+': {
                int result = evaluate(leftOperand) + evaluate(rightOperand);
                System.out.println(leftOperand + operator + rightOperand + "=" + result);
                return result;
            }
            case '*': {
                int result = evaluate(leftOperand) * evaluate(rightOperand);
                System.out.println(leftOperand + operator + rightOperand + "=" + result);
                return result;
            }

        }

        return 0;
    }
}
