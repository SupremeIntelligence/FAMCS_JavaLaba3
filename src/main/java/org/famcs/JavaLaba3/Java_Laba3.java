/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.famcs.JavaLaba3;

import java.util.Scanner;


public class Java_Laba3 
{
    public static String strInput ()
{   String line;
    Scanner scan = new Scanner (System.in);
    return scan.nextLine();
}
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.printf("Enter the first line: \t");
        String firstLine = strInput();

        System.out.printf ("Enter the second line containing separators: \t");
        String separatorLine = strInput();

        StringEditor.Separate(firstLine, separatorLine);

    }
}
