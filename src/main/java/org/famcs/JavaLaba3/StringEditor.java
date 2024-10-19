
package org.famcs.JavaLaba3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
            else if (!(currentNumb.length() == 0)) 
                { 
                    if (currentNumb.indexOf("8")== -1 && currentNumb.indexOf("9")== -1) 
                    { 
                        octNumb = Integer.parseInt(currentNumb.toString(), 8); 
                    octNumbers.add(octNumb); 
                    result = String.format("In the token %s there is an oct number %d", token, octNumb); 
                    System.out.println(result);     
                    currentNumb.setLength(0); 
                    } 
                    else  
                    { 
                        currentNumb.setLength(0); 
                    } 
                } 
        } 
         
        //check in case the string ends with a digit 
        if (!(currentNumb.length() == 0) && currentNumb.indexOf("8")== -1 && currentNumb.indexOf("9")== -1) 
                { 
                    octNumb = Integer.parseInt(currentNumb.toString(), 8); 
                    octNumbers.add(octNumb); 
                    result = String.format("In the token %s there is an oct number %d", token, octNumb); 
                    System.out.println(result);     
                    currentNumb.setLength(0); 
                } 
        if (octNumbers.isEmpty()) 
        { 
            result = String.format("There isn't any oct numbers in the token %s", token); 
            System.out.println(result); 
        } 
        return octNumbers; 
    } 
 
    public static String remove (String str, int start, int end) 
    { 
        StringBuilder line = new StringBuilder(str); 
        line.delete(start, end); 
        return line.toString(); 
    } 
     
    public static int indexOfDate(String token, int index)  //returns index of date found starting from the index, if there isn't any date returns -1 
    { 
        Pattern datePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");  
        Matcher matcher = datePattern.matcher(token); 
        if (matcher.find(index)) 
        { 
            return matcher.start(); 
        } 
        return -1; 
    } 
     
    public static ArrayList<Date> FindDate (StringBuilder StrBToken) 
    { 
        String token = StrBToken.toString(); 
        Pattern datePattern = Pattern.compile ("\\d{2}:\\d{2}:\\d{2}"); 
        Matcher matcher = datePattern.matcher(token); 
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd:MM:yy"); 
        dateFormat.setLenient(false); 
        String result; 
        String dateString; 
        ArrayList<Date> dateList = new ArrayList<>(); 
         
        int index = 0; 
        while ((index = StringEditor.indexOfDate(token, index)) != -1) 
        { 
            matcher.find(index); 
            try 
            { 
                Date date = dateFormat.parse(matcher.group()); 
                dateString = dateFormat.format(date); 
                dateList.add(date); 
                 
                result = String.format("In the token %s there is a date %s on the position %d", token, dateString, index); 
                System.out.println(result); 
                 
                token = StringEditor.remove(token, index, index+8); 
 
            } 
            catch (ParseException exception) 
            { 
                System.out.println("The date  " + matcher.group() + " is incorrect."); 
                index+=8; 
            } 
         StrBToken.setLength(0); 
         StrBToken.append(token); 
        } 
        return dateList; 
    } 

    public static void insertRandNumbAfterDate(StringBuilder line, int index)
    {
        Random rand = new Random();
        int number = rand.nextInt();
        if (index != -1)
        {
            line.insert (index+8, Integer.toString(number));
        }
        else 
        {
            index = line.length()/2;
            line.insert (index, Integer.toString(number));
        }
    }

    public static int indexOfShortestToken(String[] tokens)
    {
        int MIN_size = Integer.MAX_VALUE;
        int size;
        int index = -1;
        Character b;
        Character e;

        for (int i = 0; i < tokens.length; i++)
        {
            size = tokens[i].length();
            b = tokens[i].charAt(0);
            e = tokens[i].charAt(size-1);
            if (Character.isDigit(b) && Character.isLetter(e) && size<= MIN_size)
            {
                MIN_size = size;
                index = i;
            }
        }
        return index;
    }


}