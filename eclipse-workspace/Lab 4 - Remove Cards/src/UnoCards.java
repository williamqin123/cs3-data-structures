
public class UnoCards implements Comparable<UnoCards>{
	private String value;
	private String color;
	private final static String[] colorOrder = {"black", "blue", "green", "red", "yellow"}; 
	
	public UnoCards(String v, String c)
	{
		value = v;
		color = c;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String getColor()
	{
		return color;
	}
	
	@Override
	public boolean equals(Object x)
	{
		if (!(x instanceof UnoCards)) return false;
		return this.compareTo((UnoCards)x)==0;
	}
	
	@Override
	public int compareTo(UnoCards x)
	{
		if(color.equals(x.color))
			return value.compareTo(x.value);
		return color.compareTo(x.color);
	}
	
	@Override
	public String toString()
	{
		return color+" "+value;
	}
}
