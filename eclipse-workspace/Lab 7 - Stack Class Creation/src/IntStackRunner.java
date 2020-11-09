
import java.util.ArrayList;
import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class IntStackRunner
{
	public static void main ( String[] args ) throws IOException
	{
		Scanner input = new Scanner(new File("IntStack.txt"));
		IntStack test = new IntStack();
		
		while(input.hasNext())
			test.push(input.nextInt());
		
		System.out.println(test);
		
		int count = 2;
		while(!test.isEmpty())
		{
			if(count%3==0)
				System.out.println(test.peek());
			else
				System.out.println(test.pop());
			count++;
		}
		System.out.println(test);
	}
}
