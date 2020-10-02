
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class RemoveCards {
	
	public static ArrayList<UnoCards> listRangeColorToRemove(String start, String end, String color, UnoDeck deck) {
	}
	
	public static ArrayList<UnoCards> listRangeToRemove(String start, String end, UnoDeck deck) {
    }
	
	public static void listValueToRemove(String val, UnoDeck deck) {
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		while (iter.hasNext()) {
			if (iter.next().getValue().equals(val)) {
				iter.remove();
			}
		}
    }
	
	public static ArrayList<UnoCards> listColorToRemove(String val, UnoDeck deck) {
    }
	
	public static int removal(ArrayList<UnoCards> findList, UnoDeck deck) {
	}
	
	//other necessary methods
	
	public static List<String> deckStringToList(String inputDeck) { // converts input String deck to List of cards deck
		
		List<String> cards = new ArrayList();
		
		String accum = "";
		for (char c : (inputDeck + " ").toCharArray()) {
			accum += c;
			
			if (accum.matches("\\w+ \\w+ ")) {
				String[] cardInfo = accum.split(" ");
				if (Arrays.stream(UnoDeck.color).anyMatch(cardInfo[0]::equals)
				&&  Arrays.stream(UnoDeck.values).anyMatch(cardInfo[1]::equals)) {
					
					cards.add(accum.substring(0, accum.length() - 1));
					
					accum = "";
				}
			}
		}
		return cards;
	}
	
	public static void printDeck(List<String> deck) {
		System.out.println("Deck: " + String.join(" ", deck));
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.print("Please Enter Deck: "); // yellow 1 blue 9 black wilddraw4 green skip green 8 red 7 blue 2 yellow 3 red 2 yellow 7 red 2 yellow 4 red 1 red 3
		
		String inputDeck = sc.nextLine();
		UnoDeck deckCards = new UnoDeck(inputDeck);
		
		System.out.println(deckCards);
		
		System.out.println("Please enter command. Enter STOP to end program");
		
		
		
		String entry = sc.nextLine();
		
		while (!entry.equals("STOP")) { // STOP command ends program
			
			String[] tokens = entry.split("\\s");
			
			String command = tokens[0];
			String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
			
			switch (command) {
			case "value":
				listValueToRemove(arguments[0], deckCards);
				System.out.println(deckCards);
				break;
				
			default: // invalid command
				System.out.println("Command \"" + command + "\" not found. Try again.");
			}
			
			entry = sc.nextLine();
		}
		
		
		
		sc.close();
	}
}
