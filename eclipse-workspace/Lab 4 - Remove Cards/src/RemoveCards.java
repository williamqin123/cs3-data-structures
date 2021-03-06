

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
		return (ArrayList<UnoCards>) (deck.getDeck().stream().filter(uc -> uc.getValue().equals(val)).collect(Collectors.toList()));
    }
	
	public static ArrayList<UnoCards> listColorToRemove(String color, UnoDeck deck) {
		return (ArrayList<UnoCards>) (deck.getDeck().stream().filter(uc -> uc.getColor().equals(color)).collect(Collectors.toList()));
    }
	
	public static int removal(ArrayList<UnoCards> findList, UnoDeck deck) {
		
		//final long start = System.nanoTime();
		
		if (findList.size() == 0) return 0;
		
		UnoDeck.sortDeck(findList);
		
		//System.out.println(findList);
		
		ListIterator<UnoCards> iter = deck.getDeck().listIterator();
		
		LinkedList<UnoCards> seen = new LinkedList<UnoCards>();
		LinkedList<Integer> distances = new LinkedList<Integer>();
		int direction = 0;
		int moves = 0;
		boolean justRemoved = false;
		
		while (true) {
			
			//int prevDirection = 0;
			
			direction = 1;
			
			ListIterator<UnoCards> seenIter = seen.listIterator();
			ListIterator<Integer> distancesIter = distances.listIterator();
			
			int shortestDistance = (int)Double.POSITIVE_INFINITY;
			while (seenIter.hasNext()) {
				UnoCards nextSeen = seenIter.next();
				Integer seenDistance = distancesIter.next();
				
				if (nextSeen.equals(findList.get(0))) {
					//System.out.println(nextSeen);
					if (Math.abs(seenDistance) < Math.abs(shortestDistance)) {
						shortestDistance = seenDistance;
						direction = (int)Math.signum(shortestDistance);
						//System.out.println(shortestDistance);
					}
				}
			}
			
			//System.out.println("dir:" + direction);
			
			UnoCards i;
			
			if (direction == 1 || direction == 0) {
				direction = 1 * (justRemoved ? 0 : 1);
				i = iter.next();
			}
			else {
				direction = -1;
				i = iter.previous();
			}
			
			//System.out.println(direction > 0 ? "--->" : "<---");
			
			/*if (prevDirection == -direction) {
				direction *= 2;
				if (direction > 0) {
					i = iter.next();
				}
				else if (direction < 0) {
					i = iter.previous();
				}
			}*/
			
			//System.out.println("i:" + i);
			
			//distancesIter = distances.listIterator();
			while (direction != 0 && distancesIter.hasPrevious()) {
				Integer d = distancesIter.previous();
				distancesIter.set(d - direction);
			}
			
			justRemoved = false;
			
			if (i.equals(findList.get(0))) {
				iter.remove();
				justRemoved = true;
				
				if (findList.size() <= 1) {
					moves++;
					break;
				}
				findList.remove(0);
				
				//System.out.println(i.getColor() + i.getValue());
				//System.out.println(iter.previousIndex());
				if (UnoDeck.deckContainsSpecificCardInstance((List<UnoCards>)seen, i)) {
					seenIter = seen.listIterator();
					distancesIter = distances.listIterator();
					while (seenIter.hasNext()) {
						UnoCards nextSeen = seenIter.next();
						Integer seenDistance = distancesIter.next();
						if (nextSeen == i) {
							seenIter.remove();
							distancesIter.remove();
						}
						else if (seenDistance > 0) {
							distancesIter.set(seenDistance - 1);
						}
					}
				}
			}
			else if (findList.contains(i) && !UnoDeck.deckContainsSpecificCardInstance((List<UnoCards>)seen, i)) {
				//System.out.println("Add to seen");
				seen.add(i);
				distances.add(0);
			}
			moves++;
			
			//System.out.println(distances);
		}
		
		//final long stop = System.nanoTime();
		
		//System.out.println((stop - start) / 1000000);
		
		return moves;
	}
	
	//other necessary methods
	
	/*public static void alphaNumericThenColorSort(ArrayList<UnoCards> deck) {
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
	}*/
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("Please Enter Deck:"); // yellow 1 blue 9 black wilddraw4 green skip green 8 red 7 blue 2 yellow 3 red 2 yellow 7 red 2 yellow 4 red 1 red 3
		// red 7 red 4 blue 8 green 4 red 4 red 6 red 8 red 8 blue 7 blue 9 green 4 blue 8 red 9 blue 4 red 9 blue 9 blue 6 red 7 blue 7 blue 6 blue 4

		// Unit Tests:
		// green reverse red 9 blue 1 blue 7 yellow 4 green 5 red 4 yellow 9 blue 4 green 9 yellow 7 blue 8 blue skip green reverse yellow 2 yellow 8 red 0 green 1 blue 4 red 2 green 2 red 5 black wild red 1 red reverse green 2 blue 9 blue 3 red 7 red 3 red 7 red reverse blue 2 red 2 yellow 2 green 4 black wild yellow 5 blue reverse yellow 1 blue draw2 yellow reverse blue 8 blue 9 blue 1 black wilddraw4 blue 5 green 8 green 4
		// yellow 1 blue 9 black wilddraw4 green skip green 8 red 7 blue 2 yellow 3 red 2 yellow 7 red 2 yellow 4 red 1 red 3
		// red 5 blue 6 red 5 green 0 green 7 yellow 3 blue 4 blue 2 red 9 red 3 yellow 5 yellow 9 blue 4 green skip red 1 red 7 blue skip red reverse blue reverse yellow draw2 blue 6 yellow 1 green skip yellow 4 blue 9 yellow 6 green 4 green 8 green 4 green reverse red 3 yellow 7 red 2 green 1 yellow draw2 black wild red 1 red skip red 6 black wild green 9 red 4 blue reverse red reverse blue 5 yellow 8 green 6 green 2 blue 5
		// green 4 blue 6 yellow 0 red 5 yellow reverse blue 8 green 0 red 7 red 3
		// blue skip yellow 4 green 9 yellow 3 green 7 green 5 green 2 yellow reverse green 8 yellow 8 red 1 blue skip yellow draw2 red draw2 yellow 5 green reverse red 7 black wilddraw4 red draw2 red 4 red 3 yellow skip blue 8 green 3 blue 5 blue reverse black wild blue 3 blue 2 green 4 red 5 blue 5 yellow 3 green 8 blue 1 blue 2 red 4 red 6 yellow 6 yellow 2 red 8 yellow 5 green 5 green draw2 red 1 green skip green 1 green 1 green 7 yellow 2 red reverse red 8 yellow 9 yellow 1 green 9 blue 7 blue 9 green 4 yellow 8 yellow 6 green draw2 blue 8 green reverse yellow 1 red 0 green 3 red 9 yellow 4 yellow 0 green 0 black wild green 2 black wilddraw4 blue 1 blue draw2 yellow skip red skip black wild red 5 blue 4 green skip blue draw2 red 9 yellow draw2 blue 9 green 6 red 2 black wild yellow 9 green 6 blue 6 red reverse red 2 black wilddraw4 red skip yellow 7 yellow reverse red 7 blue reverse blue 7 black wilddraw4 blue 6 blue 4 red 3 yellow 7 blue 3 blue 0
		// green 1 red reverse red 8 blue 7 black wilddraw4 red draw2 yellow 0 yellow 9 black wilddraw4 black wilddraw4 yellow 1 blue skip red 6 blue 2 black wild blue 6 green 4 red draw2 blue 4
		
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
