/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.famcs.JavaLaba3;

import java.util.Scanner;
import java.util.Vector;


public class Java_Laba3 
{
    public static String strInput ()
{ 
    Scanner scan = new Scanner (System.in);
    String line = scan.nextLine();
    return line;
}
    public static void main(String[] args) 
    {
        System.out.printf("Enter the first line: \t");
        String firstLine = strInput();

        System.out.printf ("Enter the second line containing separators: \t");
        String separatorLine = strInput();
        
        String[] tokens = StringEditor.Separate(firstLine, separatorLine);
        System.out.printf("\nFound tokens:\n");
        for (String token : tokens)
        {
            System.out.println(token);
        }
        System.out.println();

        Vector<Integer>octNumbers = new Vector<>();
        for (String token : tokens)
        {
            octNumbers.addAll(StringEditor.FindOctNumbs(token));
            StringEditor.FindDate(token);
        }
    }
}
