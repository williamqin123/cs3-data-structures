import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PQTestRunner {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("PQTester.txt"));

        while (sc.hasNextLine()) {
            String[] items = sc.nextLine().split("\\s");

            PQTester pq = new PQTester(items);

            System.out.println(
                "toString() - " + pq.toString() + "\n" +
                "getMin() - " + pq.getMin() + "\n" +
                "getNaturalOrder() - " + pq.getNaturalOrder() + "\n"
            );
        }

        sc.close();
    }
}
