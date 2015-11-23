package console;

import java.util.ArrayList;
import java.util.Iterator;

public class NFA {
	public ArrayList<NVertexInfo> nfa;
	
	public NFA(String query) {
		nfa = new ArrayList<NVertexInfo>();
		xPathToNFA(query);
	}

	public void xPathToNFA(String query) {
		int NVertexInfo_n_vertex = 0;
		// Assume that query form is: //p1//p2//...//pn, each pi is:
		// ei1/ei2/.../eim
		String[] parametersP = query.substring(2).split("//");
		NVertexInfo temp = new NVertexInfo();
		// Traverse each pi
		for (int i = 0; i < parametersP.length; i++) {
			String[] parametersE = parametersP[i].split("/");
			// Traverse each eij in the pi
			for (int j = 0; j < parametersE.length; j++) {
				if (j == 0) {
					// "//eij"
					if((i==0) && (j==0)){
						// The first element in the path
						NVertexInfo starter = new NVertexInfo(NVertexInfo_n_vertex++, false, false);
						NVertexInfo middle = new NVertexInfo(NVertexInfo_n_vertex++, true, false);
						NVertexInfo next = new NVertexInfo(NVertexInfo_n_vertex++, false, false);
						NEdge edgeStarterMiddle = new NEdge(middle, "e");
						NEdge edgeMiddleNext = new NEdge(next, parametersE[j]);
						starter.edgeList.add(edgeStarterMiddle);
						middle.edgeList.add(edgeMiddleNext);
						nfa.add(starter);
						nfa.add(middle);
						temp = next;
					} else {
						NVertexInfo starter = temp;
						NVertexInfo middle = new NVertexInfo(NVertexInfo_n_vertex++, true, false);
						NVertexInfo next = new NVertexInfo(NVertexInfo_n_vertex++, false, false);
						NEdge edgeStarterMiddle = new NEdge(middle, "e");
						NEdge edgeMiddleNext = new NEdge(next, parametersE[j]);
						starter.edgeList.add(edgeStarterMiddle);
						middle.edgeList.add(edgeMiddleNext);
						nfa.add(starter);
						nfa.add(middle);
						temp = next;
					}
				} else {
					// "//ei"
					NVertexInfo current = temp;
					NVertexInfo next = new NVertexInfo(NVertexInfo_n_vertex++, false, false);
					NEdge currentNext = new NEdge(next, parametersE[j]);
					current.edgeList.add(currentNext);
					nfa.add(current);
					temp = next;
				}
			}
		}
		NVertexInfo end = temp;
		end.endVertex = true;
		nfa.add(end);
	}

	@Override
	public String toString() {
		String result = "***********************************NFA***********************************"+"\n"+"";
		Iterator<NVertexInfo> it = nfa.iterator();
		while(it.hasNext()){
			NVertexInfo n = it.next();
			result = result + n.toString() + "\n";
		}
		return result;
	}
	
	
}
