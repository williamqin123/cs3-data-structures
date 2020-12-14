import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class JavaLinkedListRunner
{
    public static void main ( String[] args ) throws IOException
    {
        Scanner input = new Scanner(new File("JavaLinkedList.txt"));

        while(input.hasNext())
        {
            String x[] = input.nextLine().split(" ");;
            int[] list = new int[x.length];
            for(int i=0; i<x.length; i++)
                list[i] = Integer.parseInt(x[i]);
            JavaLinkedList test = new JavaLinkedList(list);
            out.println(test);
        }
    }
}