import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import static java.lang.System.*;

public class IntQueueRunner
{
    public static void main ( String[] args ) throws IOException
    {
        Scanner input = new Scanner(new File("IntQueue.txt"));
        IntQueue test = new IntQueue();

        while(input.hasNext())
            test.add(input.nextInt());

        System.out.println(test);

        int count = 2;
        while(!test.isEmpty())
        {
            if(count%3==0)
                System.out.println(test.peek());
            else
                System.out.println(test.remove());
            count++;
        }
        System.out.println(test);
    }
}