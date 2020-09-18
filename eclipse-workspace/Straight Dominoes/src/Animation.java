
public class Animation {

	public static double linterp(double y0, double y1, double x) {
		if (x < y0) return 0;
		else if (x > y1) return 1;
		
		return x * y1 + (1.0 - x) * y0;
	}
	
	public static double smootherstep(double y0, double y1, double x) {
		if (x < y0) return 0;
		else if (x > y1) return 1;
		
		return (Math.pow(x, 3)) * (x * (x * 6 - 15) + 10) * (y1 - y0) + y0;
	}
	
}
