import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PalinListRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("PalinList.txt"));

        while (sc.hasNextLine()) {
            String[] list = sc.nextLine().split("\\s");

            PalinList pl = new PalinList(list);

            System.out.println(Arrays.toString(list) + " is " + (pl.isPalin() ? "" : "not ") + "a palinlist.\n");
        }

        sc.close();
    }
}
