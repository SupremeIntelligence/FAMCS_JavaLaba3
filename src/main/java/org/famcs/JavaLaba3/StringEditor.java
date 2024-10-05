package org.famcs.JavaLaba3;

import java.util.StringTokenizer;

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
                System.out.println(tokens[i]);
            }
            return tokens;
        }
        else //if (separatorLine.length() == 1)
        {
            String[] tokens = line.split(separatorLine);
            for (String token : tokens)
            {
                System.out.println(token);
            }
            return tokens;
        }
    }
}
