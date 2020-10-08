
public class UnoCards implements Comparable<UnoCards> {
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
	public int compareTo(UnoCards card) {
        String val1 = getValue();
        String val2 = (card).getValue();
        int vComp = val1.compareTo(val2);
        if (vComp != 0) {
           return vComp;
        } 
        String col1 = getColor();
        String col2 = (card).getColor();
        return col1.compareTo(col2);
	}
	
	@Override
	public String toString()
	{
		return color+" "+value;
	}
}
