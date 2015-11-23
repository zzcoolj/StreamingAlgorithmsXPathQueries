package console;

public class seqElement {
	private int startEnd;
	private String name;
	
	public seqElement(int startEnd, String name) {
		this.startEnd = startEnd;
		this.name = name;
	}

	public int getStartEnd() {
		return startEnd;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "[" + startEnd + ", " + name + "]";
	}
}
