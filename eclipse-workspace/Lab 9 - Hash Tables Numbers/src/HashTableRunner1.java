import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.*;

public class HashTableRunner1 {

    public static void main(String[] args) throws FileNotFoundException {

        HashTable ht = new HashTable();

        Scanner sc = new Scanner(new File("numbers.dat"));
        while (sc.hasNextLine()) {

            ht.add(new Number(Integer.parseInt(sc.nextLine())));

        }
        sc.close();

        out.println(ht);
    }
}
