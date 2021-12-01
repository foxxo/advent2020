package Twenty.DayTwo;

public class PasswordRule
    {
        String checkLetter;
        int min, max;
        
        public PasswordRule(String input)
        {
            parseRule(input);
        }
        
        public PasswordRule(String checkLetter, int min, int max)
        {
            this.checkLetter = checkLetter;
            this.min = min;
            this.max = max;
        }
        
        public void parseRule(String ruleString)
        {
            min = Integer.parseInt(ruleString.substring(0, ruleString.indexOf("-")));
            max = Integer.parseInt(ruleString.substring(ruleString.indexOf("-")+1, ruleString.indexOf(" ")));
            
            checkLetter = ruleString.substring(ruleString.indexOf(":")-1, ruleString.indexOf(":"));
        }
        

        public boolean passwordIsValid(String password)
        {
           int matches = (password.length() - password.replace(checkLetter, "").length());
            System.out.println(String.format("Found %d matches for %s within %s", matches, checkLetter, password));
           return (matches >= min && matches <= max);
        }

        public boolean passwordIsValidByPlacement(String password)
        {
            return(password.substring(min-1, min).equals(checkLetter) ^ password.substring(max-1, max).equals(checkLetter));
        }

    }