import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.System.*;

public class HashTableRunner1 {

    public static void main(String[] args) throws FileNotFoundException {

        HashTable ht = new HashTable();

        Scanner sc = new Scanner(new File("numbers.dat"));

        if (!sc.hasNextLine()) return;

        int numberOfNumbers = Integer.parseInt(sc.nextLine());
        while (numberOfNumbers > 0) {
            ht.add(new Number(Integer.parseInt(sc.nextLine())));
            numberOfNumbers--;
        }
        sc.close();

        out.println(ht);
    }
}
