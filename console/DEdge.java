package console;

public class DEdge {
	public DVertexInfo d_dest;
	public String d_transtoken;

	@Override
	public String toString() {
		return "[" + d_dest + ", " + d_transtoken + "]";
	}
}
