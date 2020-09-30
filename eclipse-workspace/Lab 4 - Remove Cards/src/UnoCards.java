
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
	
	public boolean equals(UnoCards x)
	{
		return this.compareTo(x)==0;
	}
	
	public int compareTo(UnoCards x)
	{
		if(color.equals(x.color))
			return value.compareTo(x.value);
		return color.compareTo(x.color);
	}
	
	public String toString()
	{
		return color+" "+value;
	}
}
