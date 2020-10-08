
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class RemoveCards_Old {
	
	public static ArrayList<UnoCards> listRangeColorToRemove(String start, String end, String color, UnoDeck deck) {
		
		ArrayList<UnoCards> cardsToRemove = new ArrayList<UnoCards>();
		ListIterator<String> unoValueIter = Arrays.asList(UnoDeck.values).listIterator();
		
		boolean collect = false;
		
		while (unoValueIter.hasNext()) {
			String val = unoValueIter.next();
			if (val.equals(start)) {
				collect = true;
			}
			if (!collect) continue;
			
			ListIterator<UnoCards> iter = deck.getDeck().listIterator();
			while (iter.hasNext()) {
				UnoCards i = iter.next();
				if (i.getValue().equals(val) && (color == null ? true : i.getColor().equals(color))) {
					cardsToRemove.add(i);
				}
			}
			
			if (val.equals(end)) break;
		}
		
		return cardsToRemove;
	}
	
	public static ArrayList<UnoCards> listRangeToRemove(String start, String end, UnoDeck deck) {
		
		return listRangeColorToRemove(start, end, null, deck);
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
		LinkedList<Integer> distances = new LinkedList<Integer>();
		int direction = 0;
		int moves = 0;
		boolean justRemoved = false;
		
		while (findList.size() > 0) {
			/*
			//System.out.println(deck);
			//System.out.println(seen);
			System.out.println(findList);
			
			UnoCards i;
			
			if (direction == 1) {
				i = iter.next();
			}
			else {
				i = iter.previous();
			}
			
			if (seen.size() > 0 && seen.getLast().equals(i) && directions.getLast() == -direction) {
				//System.out.println("Remove from seen");
				seen.remove(seen.size() - 1);
			}
			
			if (i.equals(findList.get(0))) {
				iter.remove();
				findList.remove(0);
				System.out.println(i.getColor() + i.getValue());
				System.out.println(iter.previousIndex());
				
				if (seen.size() > 0 && seen.contains(findList.get(0))) {
					//System.out.println("Backwards");
					direction = ;
				}
				else direction = 1;
			}
			else if (findList.contains(i) && !realContains((List<UnoCards>)seen, i)) {
				//System.out.println("Add to seen");
				seen.add(i);
			}
			moves++;
			
			//System.out.println();
			*/
			
			direction = 1;
			
			ListIterator<UnoCards> seenIter = seen.listIterator();
			ListIterator<Integer> distancesIter = distances.listIterator();
			
			int shortestDistance = (int)Double.POSITIVE_INFINITY;
			
			while (seenIter.hasNext()) {
				UnoCards nextSeen = seenIter.next();
				Integer seenDistance = distancesIter.next();
				
				if (nextSeen.equals(findList.get(0))) {
					if (Math.abs(seenDistance) < Math.abs(shortestDistance)) {
						shortestDistance = seenDistance;
						direction = (int)Math.signum(shortestDistance);
					}
				}
			}
			
			UnoCards i;
			
			if (direction == 1 || direction == 0) {
				direction = 1 * (justRemoved ? 0 : 1);
				i = iter.next();
			}
			else {
				direction = -1;
				i = iter.previous();
			}
			
			distancesIter = distances.listIterator();
			while (distancesIter.hasNext()) {
				Integer d = distancesIter.next();
				distancesIter.set(d - direction);
			}
			
			justRemoved = false;
			
			if (i.equals(findList.get(0))) {
				iter.remove();
				findList.remove(0);
				//System.out.println(i.getColor() + i.getValue());
				//System.out.println(iter.previousIndex());
				if (realContains((List<UnoCards>)seen, i)) {
					seenIter = seen.listIterator();
					distancesIter = distances.listIterator();
					while (seenIter.hasNext()) {
						UnoCards nextSeen = seenIter.next();
						Integer seenDistance = distancesIter.next();
						if (nextSeen == i) {
							seenIter.remove();
							distancesIter.remove();
							justRemoved = true;
						}
						else if (seenDistance > 0) {
							distancesIter.set(seenDistance - 1);
						}
					}
				}
			}
			else if (findList.contains(i) && !realContains((List<UnoCards>)seen, i)) {
				//System.out.println("Add to seen");
				seen.add(i);
				distances.add(0);
			}
			moves++;
			
			//System.out.println(seen);
		}
		
		return moves;
	}
	
	//other necessary methods
	
	public static boolean realContains(List<UnoCards> a, UnoCards lookFor) {
		for (Object item : a) {
			if (item == lookFor) return true;
		}
		return false;
	}
	
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
		
		List<String> cards = new ArrayList<String>();
		
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
		
		
		
		System.out.println("Please Enter Deck:"); // yellow 1 blue 9 black wilddraw4 green skip green 8 red 7 blue 2 yellow 3 red 2 yellow 7 red 2 yellow 4 red 1 red 3
		// red 7 red 4 blue 8 green 4 red 4 red 6 red 8 red 8 blue 7 blue 9 green 4 blue 8 red 9 blue 4 red 9 blue 9 blue 6 red 7 blue 7 blue 6 blue 4
		
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
					System.out.println("Remove " + arguments[0]);
					moves = removal(listValueToRemove(arguments[0], deckCards), deckCards);
					break;
					
				case "color":
					System.out.println("Remove " + arguments[0]);
					moves = removal(listColorToRemove(arguments[0], deckCards), deckCards);
					break;
					
				case "range":
					System.out.println("Remove " + arguments[0] + " thru " + arguments[1]);
					moves = removal(listRangeToRemove(arguments[0], arguments[1], deckCards), deckCards);
					break;
					
				case "rangecolor":
					System.out.println("Remove " + arguments[0] + " thru " + arguments[1] + " color:" + arguments[2]);
					moves = removal(listRangeColorToRemove(arguments[0], arguments[1], arguments[2], deckCards), deckCards);
					break;
					
				default: // invalid command
					System.out.println("Command \"" + command + "\" not found. Try again.");
					
					entry = sc.nextLine();
					continue;
			}
			
			System.out.println("Number of moves for removal: " + moves);
			System.out.println("Deck: " + deckCards);
			
			entry = sc.nextLine();
		}
		
		
		
		sc.close();
	}
}
