package console;

import java.util.LinkedList;

public class NVertexInfo {
	public int n_vertex; // name of NFA node
	public LinkedList<NEdge> edgeList; // all of the neighbor edges of this node
	public boolean selfEnclosure;
	public boolean endVertex;
	
	public NVertexInfo(int n_vertex, boolean selfEnclosure, boolean endVertex) {
		super();
		this.n_vertex = n_vertex;
		this.selfEnclosure = selfEnclosure;
		this.endVertex = endVertex;
		this.edgeList = new LinkedList<NEdge>();
	}

	public NVertexInfo() {
		super();
	}

	@Override
	public String toString() {
		return "NVertexInfo [n_vertex=" + n_vertex + ", edgeList=" + edgeList
				+ ", selfEnclosure=" + selfEnclosure + ", endVertex="
				+ endVertex + "]";
	}
	
	// Don't consider the condition of selfEnclosure
	public boolean hasEdge(NVertexInfo nvertex, String element){
		if(nvertex.endVertex) return false;
		if(nvertex.edgeList.get(0).n_transtoken.equals(element)){
			return true;
		} else {
			return false;
		}
	}
	
	// Don't consider the condition of selfEnclosure
	public NVertexInfo getNextVer(NVertexInfo nver, String element){
		if(hasEdge(nver, element)){
			return nver.edgeList.get(0).n_dest;
		} else {
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n_vertex;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NVertexInfo other = (NVertexInfo) obj;
		if (n_vertex != other.n_vertex)
			return false;
		return true;
	}
	
	
}
