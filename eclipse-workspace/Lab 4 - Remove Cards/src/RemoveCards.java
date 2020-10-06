
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveCards {
	
	public static ArrayList<UnoCards> listRangeColorToRemove(String start, String end, String color, UnoDeck deck) {
	}
	
	public static ArrayList<UnoCards> listRangeToRemove(String start, String end, UnoDeck deck) {
		
		ArrayList<UnoCards> cardsToRemove = new ArrayList<UnoCards>();
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		while (iter.hasNext()) {
			UnoCards i = iter.next();
			int io = Arrays.asList(UnoDeck.values).indexOf(i.getValue());
			if (io >= Arrays.asList(UnoDeck.values).indexOf(start) && io <= Arrays.asList(UnoDeck.values).indexOf(end)) {
				cardsToRemove.add(i);
			}
		}
		
		return cardsToRemove;
    }
	
	public static ArrayList<UnoCards> listValueToRemove(String val, UnoDeck deck) {
		
		ArrayList<UnoCards> cardsToRemove = new ArrayList<UnoCards>();
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		while (iter.hasNext()) {
			UnoCards i = iter.next();
			if (i.getValue().equals(val)) {
				cardsToRemove.add(i);
			}
		}
		
		return cardsToRemove;
    }
	
	public static ArrayList<UnoCards> listColorToRemove(String val, UnoDeck deck) {
		
		ArrayList<UnoCards> cardsToRemove = new ArrayList<UnoCards>();
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		while (iter.hasNext()) {
			UnoCards i = iter.next();
			if (i.getColor().equals(val)) {
				cardsToRemove.add(i);
			}
		}
		
		return cardsToRemove;
    }
	
	public static int removal(ArrayList<UnoCards> findList, UnoDeck deck) {
		
		alphaNumericThenColorSort(findList);
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		LinkedList<UnoCards> seen = new LinkedList<UnoCards>();
		int direction = 1;
		int moves = 0;
		while (findList.size() > 0) {
			
			UnoCards i;
			
			if (direction == 1) {
				i = iter.next();
			}
			else {
				i = iter.previous();
			}
			
			if (i == findList.get(0)) {
				iter.remove();
				findList.remove(i);
				
				if (seen.contains(i)) seen.remove(i);
				
				if (seen.size() > 0 && seen.contains(findList.get(0))) direction = -1;
				else direction = 1;
			}
			else if (findList.contains(i) && !seen.contains(i)) {
				seen.add(i);
			}
			moves++;
		}
		
		return moves;
	}
	
	//other necessary methods
	
	public static void alphaNumericThenColorSort(ArrayList<UnoCards> deck) {
		Collections.sort(deck, new Comparator<UnoCards>() {
	        public int compare(UnoCards c1, UnoCards c2) {
	            String x1 = (c1).getValue();
	            String x2 = (c2).getValue();
	            int sComp = x1.compareTo(x2);
	            if (sComp != 0) {
	               return sComp;
	            } 
	            String f1 = (c1).getColor();
	            String f2 = (c2).getColor();
	            return f1.compareTo(f2);
	    }});
		//return (ArrayList<UnoCards>) a.stream().sorted((card1, card2) -> card1.getValue().compareTo(card2.getValue())).collect(Collectors.toList());
	}
	
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
		
		System.out.println("Deck: " + deckCards);
		
		System.out.println("Please enter command. Enter STOP to end program");
		
		
		
		String entry = sc.nextLine();
		
		while (!entry.equals("STOP")) { // STOP command ends program
			
			String[] tokens = entry.split("\\s");
			
			String command = tokens[0];
			String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
			
			int moves = 0;
			
			switch (command) {
				case "value":
					System.out.println("remove " + arguments[0]);
					moves = removal(listValueToRemove(arguments[0], deckCards), deckCards);
					break;
					
				case "color":
					System.out.println("remove " + arguments[0]);
					moves = removal(listColorToRemove(arguments[0], deckCards), deckCards);
					break;
					
				case "range":
					System.out.println("remove " + arguments[0]);
					moves = removal(listRangeToRemove(arguments[0], arguments[1], deckCards), deckCards);
					break;
					
				case "rangecolor":
					System.out.println("remove " + arguments[0]);
					//moves = removal(listRangeColorToRemove(arguments[0], deckCards), deckCards);
					break;		
					
				default: // invalid command
					System.out.println("Command \"" + command + "\" not found. Try again.");
					continue;
			}
			
			System.out.println("Number of moves for removal: " + moves);
			System.out.println("Deck: " + deckCards);
			
			entry = sc.nextLine();
		}
		
		
		
		sc.close();
	}
}