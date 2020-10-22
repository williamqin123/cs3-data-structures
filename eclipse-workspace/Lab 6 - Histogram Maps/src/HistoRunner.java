
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import static java.lang.System.*;

public class HistoRunner
{
	public static void main( String args[] ) 
	{
		Scanner input = new Scanner(in);
			
		out.println("Enter in String:");
		Histogram test = new Histogram(input.nextLine());
		out.println(test);
	}
}
