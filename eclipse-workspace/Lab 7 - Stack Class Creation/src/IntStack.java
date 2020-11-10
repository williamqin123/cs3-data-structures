
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntStack extends ArrayList<Integer> {
	
	public void push(int eger) {
		add(0, eger);
	}

	public int peek() {
		return get(0);
	}
	
	public int pop() {
		final int returnVal = peek();
		remove(0);
		return returnVal;
	}
	
	public String normalToString() {
		return super.toString();
	}
	
	@Override
	public String toString() {
		final List reversed = (List)this.clone();
		Collections.reverse(reversed);
		
		return ((IntStack)reversed).normalToString();
	}
}
