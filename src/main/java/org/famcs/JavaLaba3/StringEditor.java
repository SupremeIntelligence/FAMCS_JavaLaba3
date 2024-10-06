package org.famcs.JavaLaba3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringEditor 
{
    public static String[] Separate (String line, String separatorLine)
    {
        if (separatorLine.length() >= 2)
        {
            StringTokenizer st = new StringTokenizer(line, separatorLine);
            int count = st.countTokens();
            String[] tokens = new String[count];
            for (int i = 0; i < count; i++)
            {
                tokens[i] = st.nextToken();
            }
            return tokens;
        }
        else //if (separatorLine.length() == 1) //maybe useless
        {
            String[] tokens = line.split(separatorLine);
            return tokens;
        }
    }

    public static Vector <Integer> FindOctNumbs (String token)
    {
        StringBuilder currentNumb = new StringBuilder();
        char c;
        int octNumb;
        String result;
        Vector<Integer> octNumbers = new Vector<>();
        for (int i = 0; i < token.length(); i++)
        {
            c = token.charAt(i);
            if(Character.isDigit(c))
            {
                    currentNumb.append(c);
            }
            else if (!currentNumb.isEmpty() && currentNumb.indexOf("8")== -1 && currentNumb.indexOf("9")== -1)
                {
                    octNumb = Integer.parseInt(currentNumb.toString(), 8);
                    octNumbers.add(octNumb);
                    result = String.format("In the token %s there is an oct number %d", token, octNumb);
                    System.out.println(result);    
                    currentNumb.setLength(0);
                }
        }
        //check in case the string ends with a digit
        if (!currentNumb.isEmpty() && currentNumb.indexOf("8")== -1 && currentNumb.indexOf("9")== -1)
                {
                    octNumb = Integer.parseInt(currentNumb.toString(), 8);
                    octNumbers.add(octNumb);
                    result = String.format("In the token %s there is an oct number %d", token, octNumb);
                    System.out.println(result);    
                    currentNumb.setLength(0);
                }
        return octNumbers;
    }

    public static void FindDate (String token)
    {
        Pattern datePattern = Pattern.compile ("\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = datePattern.matcher(token);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd:MM:yy");
        dateFormat.setLenient(false);
        String result;
        String dateString;
        while(matcher.find())
        {
            try
            {
                Date date = dateFormat.parse(matcher.group());
                dateString = dateFormat.format(date);
                result = String.format("In the token %s there is a date %s", token, dateString);
                System.out.println(result);
            }
            catch (ParseException exception)
            {
                System.out.println("The date  " + matcher.group() + " is incorrect.");
            }
            
        }
    }
}
