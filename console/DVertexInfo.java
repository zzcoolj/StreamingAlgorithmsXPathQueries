package console;

import java.util.Iterator;
import java.util.LinkedList;

public class DVertexInfo {
	public int dver_name;
	public LinkedList<NVertexInfo> d_vertex; // All of NFA states(NVertexInfo)
												// correspond to this DFA state
	public LinkedList<DEdge> edgeList;
	public boolean endVertex;
	
	public DVertexInfo(int dver_name, LinkedList<NVertexInfo> d_vertex, boolean endVertex) {
		super();
		this.dver_name = dver_name;
		this.d_vertex = d_vertex;
		this.edgeList = new LinkedList<DEdge>();
		this.endVertex = endVertex;
	}

	@Override
	public String toString() {
		String d_vertex_string="[";
		Iterator<NVertexInfo> it = d_vertex.iterator();
		while(it.hasNext()){
			NVertexInfo n = it.next();
			d_vertex_string = d_vertex_string + n.n_vertex + " ";
		}
		d_vertex_string = d_vertex_string + "]";
		
		return "DVertexInfo [dver_name=T" + dver_name + ", d_vertex=" + d_vertex_string
				+ ", edgeList=" + edgeList + ", endVertex=" + endVertex + "]";
	}
	
	public boolean hasEndNvertex(){
		boolean result = false;
		Iterator<NVertexInfo> it = this.d_vertex.iterator();
		while(it.hasNext()){
			NVertexInfo n = it.next();
			if(n.endVertex) return true;
		}
		return result;
	}
	
}
