
import java.util.*;

public class UnoDeck {
	public final static String[] values = {"0","1","2","3","4","5","6","7","8","9","draw2","reverse","skip","wild","wilddraw4"};
	public final static String[] color = {"black","blue","green","red","yellow"};
	private ArrayList<UnoCards> deck;
	
	public UnoDeck()
	{
		deck = new ArrayList<UnoCards>();
		for(int v = 0; v<values.length; v++)
			for(int c = 0; c<color.length; c++)
				if(color[c].equals("black") && (values[v].equals("wild") || values[v].equals("wilddraw4")) ||
				   !color[c].equals("black") && (!values[v].equals("wild") && !values[v].equals("wilddraw4")))
				{	
					deck.add(new UnoCards(values[v],color[c]));
					if(!values[v].equals("0"))
					{
						deck.add(new UnoCards(values[v],color[c]));
						if(color[c].equals("black"))
						{
							deck.add(new UnoCards(values[v],color[c]));
							deck.add(new UnoCards(values[v],color[c]));
						}
					}
				}	
	}
	
	public UnoDeck(UnoDeck x)
	{
		deck = new ArrayList<UnoCards>();
		for(UnoCards k:x.deck)
			deck.add(k);
	}
	
	public UnoDeck(String x)
	{
		String[] list = x.split(" ");
		deck = new ArrayList<UnoCards>();
		for(int i=0; i<list.length; i+=2)
		{
			//System.out.println("length:"+list.length+" i:"+i+" i+1:"+(i+1));
			deck.add(new UnoCards(list[i+1], list[i]));
		}
	}
	
	public void remove(int k)
	{
		deck.remove(k);
	}
	
	public UnoCards get(int k)
	{
		return deck.get(k);
	}

	public ArrayList<UnoCards> getDeck()
	{
		return deck;
	}
	
	public void sortDeck()
	{
		Collections.sort(deck);
	}
	
	public void shuffle()
	{
		for(int i=0; i<deck.size(); i++)
		{
			int loc = (int)(Math.random()*deck.size());
			UnoCards k = deck.get(i);
			deck.set(i, deck.get(loc));
			deck.set(loc, k);
		}
	}
	
	@Override
	public String toString()
	{
		String temp = "";
		for(UnoCards k:deck)
			temp+=k+" ";
		temp.trim();
		return temp;
	}
}
