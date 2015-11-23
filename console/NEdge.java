package console;

public class NEdge {
	public NVertexInfo n_dest; // destination node of this edge
	public String n_transtoken; // element name of the transition condition

	public NEdge(NVertexInfo n_dest, String n_transtoken) {
		super();
		this.n_dest = n_dest;
		this.n_transtoken = n_transtoken;
	}

	@Override
	public String toString() {
		return "[" + n_dest.n_vertex + ", " + n_transtoken + "]";
	}
}
