/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.famcs.JavaLaba3;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.Locale;
import java.text.NumberFormat;
import java.io.FileReader;
import java.io.FileWriter;
/**
 *
 * @author fpm.evdokimoAV
 */
public class Java_Laba3 {

    public static String strInput (Scanner scan) 
{  
    //if (Scanner)
    String line = scan.nextLine(); 
    return line; 
} 
    
    public static void outputDates(ArrayList<Date> dateList, FileWriter out) throws Exception
    {
        System.out.println();
        System.out.println ("List of the dates found in the tokens:");
        out.write ("\nList of the dates found in the tokens:\n");
        
        for (Date date : dateList)
        {
            out.write(date + "\n");
            System.out.println(date);
        }
    }
    
    public static void outputOctNumbers(Vector<Integer> octNumbers, FileWriter out) throws Exception
    {
        System.out.println();
        System.out.println ("List of the octal numbers found in the tokens:");
        out.write("\nList of the octal numbers found in the tokens:\n");
        for (int numb : octNumbers)
        {
            out.write(numb + "\t");
        }
        
        System.out.println (octNumbers);
    }

    public static void sortDates (ArrayList<Date> dateList)
    {
        Collections.sort(dateList, new Comparator<Date>() 
        {
            @Override
            public int compare (Date x, Date y)
            {
                return x.compareTo(y);
            }
        });
    }

    public static void sortNumbers (Vector<Integer> numbers)
    {
        numbers.sort( new Comparator<Integer>()
        {
            @Override
            public int compare (Integer x, Integer y)
            {
                return Integer.compare(x, y);
            }
        });
        
    }

    public static boolean isValidLocale(Locale locale) {
        for (Locale availableLocale : Locale.getAvailableLocales()) {
            if (availableLocale.equals(locale)) 
            {
                return true;
            }
        }
        return false;
    }

    public static void convertToCurrency (Vector<Integer> numbers, String language, String country, FileWriter out) throws Exception
    {
        Locale locale = new Locale (language, country);
        //Locale locale = Locale.US;
        
        if (!isValidLocale(locale)) {
            System.out.println("The entered locale does not exist.");
            return;
        }
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        out.write("\nList of values converted into " + country + " currency:\n");
        System.out.printf ("%nList of values converted into %s currency: %n", country);
        String formattedValue;
        for (int numb : numbers)
        {
            formattedValue = currencyFormat.format(numb);
            out.write(formattedValue + "\n");
            System.out.println (formattedValue);
        }
    }

    public static void convertToPercents (Vector<Integer> numbers, FileWriter out) throws Exception
    {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.US);
        double percent;
        String formattedValue;
        out.write("\nList of values as a percentage:\n");
        System.out.println ("List of values as a percentage:");
        for (int numb : numbers)
        {
            percent = numb;
            //while (percent>10) //mb it's worth remove it
            //{
            //   percent /= 10;
            //}
            formattedValue = percentFormat.format(percent);
            out.write(formattedValue + "\n");
            System.out.println (formattedValue);
        }
    }

    public static String[] removeElement (String[] tokens, int delIndex)
    {
        if (delIndex == -1 || delIndex >= tokens.length)
        {
            System.out.println("Incorrect deletion index.");
            return tokens;
        }
        return Arrays.stream(tokens).filter(token-> !token.equals(tokens[delIndex])).toArray(String[]::new);
    }
    
    public static void main(String[] args)  throws Exception
    { 
        FileReader input = new FileReader("input.txt");
        FileWriter out = new FileWriter("output.txt");
        
        
        Scanner scan = new Scanner (input); 
        System.out.println("Enter the first line: \t"); 
        String firstLine = strInput(scan); 
 
        System.out.println ("Enter the second line containing separators: \t"); 
        String separatorLine = strInput(scan); 
         
        String[] tokens = StringEditor.Separate(firstLine, separatorLine); 
        out.write("\nFound tokens:\n");
        System.out.printf("\nFound tokens:\n"); 
        for (String token : tokens) 
        { 
            System.out.println(token); 
            out.write(token + "\n");
        } 
        System.out.println(); 
 
        Vector<Integer>octNumbers = new Vector<>(); 
        ArrayList<Date> dates = new ArrayList<>(); 
        String copy_token;  
         
        for (String token : tokens) 
        { 
            StringBuilder StrBToken = new StringBuilder (token); 
            dates.addAll(StringEditor.FindDate(StrBToken));
            octNumbers.addAll(StringEditor.FindOctNumbs(StrBToken.toString())); 
        } 
        
        if (!octNumbers.isEmpty())
        {
            outputOctNumbers(octNumbers, out);
            sortNumbers(octNumbers);
            outputOctNumbers(octNumbers, out);
        }
        else 
        {
            System.out.printf("%nThere is no octal number in the string.%n");
        }

        if (!dates.isEmpty())
        {
            outputDates(dates, out);
            sortDates (dates);
            outputDates(dates, out);
        }
        else 
        {
            System.out.printf("%nThere is no date in the string.%n");
        }
        
        
        StringBuilder str = new StringBuilder(firstLine);
        StringEditor.insertRandNumbAfterDate(str, StringEditor.indexOfDate(firstLine, 0));
        System.out.printf("%nThe string with inserted random number:%n %s %n%n", str);
    if (!octNumbers.isEmpty())
    {
       System.out.println("""
           Examples of currency language and country:
           en_US  English (United States)
           en_GB  English (United Kingdom)
           fr_FR  French (France)
           de_DE  German (Germany)
           es_ES  Spanish (Spain)
           ru_RU  Russian (Russia)
           zh_CN  Chinese (China)
           ja_JP  Japanese (Japan)
           it_IT  Italian (Italy)
           ko_KR  Korean (South Korea)""");   
            
        System.out.println();
        
        System.out.println("Enter the currency language: ");
        scan.close();
        input.close();
        scan = new Scanner (System.in);
        String language = strInput(scan);
        System.out.println("Enter the currency country: ");
        String country = strInput(scan);
        convertToCurrency(octNumbers, language, country, out);

        System.out.println();
        convertToPercents(octNumbers, out);
    }
        scan.close();

        int delIndex = StringEditor.indexOfShortestToken(tokens);
        
        String[] newTokens = removeElement(tokens, delIndex);
        if (newTokens.length != tokens.length)
        {
            System.out.println (Arrays.toString(newTokens));
        }
        else
        {
            System.out.println ("There is no the shortest token that starts with a digit and ends with a letter");
        }
        out.close();
    } 
    
    
}