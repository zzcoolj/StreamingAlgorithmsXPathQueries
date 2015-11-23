package console;

import java.util.ArrayList;
import java.util.Iterator;

public class LazyDFA {
	public ArrayList<DVertexInfo> lazyDFA;

	public LazyDFA() {
		this.lazyDFA = new ArrayList<DVertexInfo>(); 
	}

	@Override
	public String toString() {
		String result = "";
		Iterator<DVertexInfo> it = lazyDFA.iterator();
		while(it.hasNext()){
			DVertexInfo d = it.next();
			result = result + d.toString() + "\n";
		}
		return result;
	}
	
	// Consider selfEnclosure
	public boolean hasEdge(DVertexInfo dver, String element){
		boolean result = false;
		Iterator<NVertexInfo> it = dver.d_vertex.iterator();
		while(it.hasNext()){
			NVertexInfo n = it.next();
			if(n.hasEdge(n, element)){
				result = true;
			}
			if(n.selfEnclosure){
				result = true;
			}
		}
		return result;
	}
	
	public ArrayList<NVertexInfo> getAllNVertexInfos(DVertexInfo dver, String element){
		ArrayList<NVertexInfo> result = new ArrayList<NVertexInfo>();
		Iterator<NVertexInfo> it = dver.d_vertex.iterator();
		while(it.hasNext()){
			NVertexInfo n = it.next();
			NVertexInfo temp = new NVertexInfo();
			temp = n.getNextVer(n, element);
			if(temp != null){
				result.add(temp);
			}
			if(n.selfEnclosure){
				result.add(n);
			}
			if(n.hasEdge(n, "e")){
				result.add(n);
			}
		}
		return result;
	}
}
