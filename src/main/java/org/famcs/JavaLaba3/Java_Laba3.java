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
import java.util.Comparator;
import java.util.Arrays;
import java.util.Collections;
/**
 *
 * @author fpm.evdokimoAV
 */
public class Java_Laba3 {

    public static String strInput (Scanner scan) 
{  
    String line = scan.nextLine(); 
    return line; 
} 
    
    public static void outputDates(ArrayList<Date> dateList) 
    {
        System.out.println();
        System.out.println ("List of the dates found in the tokens:");
        for (Date date : dateList)
        {
            System.out.println(date);
        }
    }
    
    public static void outputOctNumbers(Vector<Integer> octNumbers)
    {
        System.out.println();
        System.out.println ("List of the octal numbers found in the tokens:");
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
   
    public static void main(String[] args)  
    { 
        Scanner scan = new Scanner (System.in); 
        System.out.println("Enter the first line: \t"); 
        String firstLine = strInput(scan); 
 
        System.out.println ("Enter the second line containing separators: \t"); 
        String separatorLine = strInput(scan); 
         
        scan.close(); 
         
        String[] tokens = StringEditor.Separate(firstLine, separatorLine); 
        System.out.printf("\nFound tokens:\n"); 
        for (String token : tokens) 
        { 
            System.out.println(token); 
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
        
        outputOctNumbers(octNumbers);
        sortNumbers(octNumbers);
        outputOctNumbers(octNumbers);

        outputDates(dates);
        sortDates (dates);
        outputDates(dates);
        
        StringBuilder str = new StringBuilder(firstLine);
        StringEditor.insertRandNumbAfterDate(str, StringEditor.indexOfDate(firstLine, 0));
        System.out.println(str);
    } 
}