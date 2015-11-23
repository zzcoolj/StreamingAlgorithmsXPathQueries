package console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class LazyDFAAlgorithm {
	private NFA nfa;
	private LazyDFA ldfa;
	private int lineNumber;
	private int dVertexInfoNumber;
	private Stack<DVertexInfo> stack;
	private String result;
	private int nodeOrder;

	LazyDFAAlgorithm(String query) {
		nfa = new NFA(query);
		// print
		//System.out.println(nfa);
		ldfa = new LazyDFA();
		lineNumber = 0;
		dVertexInfoNumber = 1;
		stack = new Stack<DVertexInfo>();
		result = "";
		nodeOrder = 0;
	}

	public void processXmlLine(String line) {
		// print
		//System.out.println("lineNumber: " + lineNumber + "	" + "processLine: " + line);
		String[] lineElements = line.split(" ");
		int startEnd = Integer.parseInt(lineElements[0]);
		String seqName = lineElements[1];

		if (startEnd == 0) {
			startElement(seqName);
		} else {
			endElement();
		}
		showStack();
		// print
		//System.out.println();
		lineNumber++;
	}

	private void endElement() {
		// print
		//System.out.println("------endElement------");
		stack.pop();
		// print
		//System.out.println("stackPoped");
	}

	private void startElement(String seqName) {
		// print
		//System.out.println("------startElement------");

		// stack.top.satisfied
		DVertexInfo stackTop = stack.peek();
		if (ldfa.hasEdge(stackTop, seqName)) {
			stackTopSatisfied(seqName, stackTop);
		} else {
			stackTopFailed(seqName, stackTop);
		}
		
		nodeOrder++;
	}

	private void stackTopFailed(String seqName, DVertexInfo stackTop) {
		// print
		//System.out.println("	------stackTopFailed------");
		
		DVertexInfo temp = stack.firstElement();
		stack.push(temp);
		
	}

	private void stackTopSatisfied(String seqName, DVertexInfo stackTop) {
		// print
		//System.out.println("	------stackTopSatisfied------");
		ArrayList<NVertexInfo> nextNVertexInfos = ldfa.getAllNVertexInfos(
				stackTop, seqName);
		DVertexInfo temp = this.stateAlreadyExistedInStack(nextNVertexInfos);
		// TODO not sure
		//if (set1.equals(set2)) {
		if(temp != null){
			// print
			//System.out.println("		------Next state already exists in stack------");
			stack.push(temp);
			pathMatchCheck(temp);
			// print
			//System.out.println("stack push: " + temp);
		} else {
			// print
			//System.out.println("		------Next state does not exist in stack------");
			LinkedList<NVertexInfo> d_vertex = new LinkedList<NVertexInfo>();
			for (int i = 0; i < nextNVertexInfos.size(); i++) {
				if (d_vertex.contains(nextNVertexInfos.get(i))) {
				} else {
					d_vertex.add(nextNVertexInfos.get(i));
				}

				if (nextNVertexInfos.get(i).hasEdge(nextNVertexInfos.get(i),
						"e")) {
					NVertexInfo afterEdgeE = nextNVertexInfos.get(i)
							.getNextVer(nextNVertexInfos.get(i), "e");
					if (d_vertex.contains(afterEdgeE)) {
					} else {
						d_vertex.add(afterEdgeE);
					}
				}
			}
			DVertexInfo newState = new DVertexInfo(dVertexInfoNumber++,
					d_vertex, false);
			stack.push(newState);
			pathMatchCheck(newState);
			// print
			//System.out.println("newStatePushed: " + newState);
		}
	}

	public void startDocument() {
		NVertexInfo nfaInitial0 = nfa.nfa.get(0);
		NVertexInfo nfaInitial1 = nfa.nfa.get(1);

		LinkedList<NVertexInfo> d_vertex = new LinkedList<NVertexInfo>();
		d_vertex.add(nfaInitial0);
		d_vertex.add(nfaInitial1);

		DVertexInfo dfaStartState = new DVertexInfo(dVertexInfoNumber++,
				d_vertex, false);
		ldfa.lazyDFA.add(dfaStartState);
		stack.push(dfaStartState);
		// print
		//System.out.println("***********************************Lazy DFA***********************************"+"\n"+"initialStatePushed: " + stack.peek());
		showStack();
		// print
		//System.out.println();
	}

	public void pathMatchCheck(DVertexInfo newState){
		boolean totalMatch = newState.hasEndNvertex();
		if(totalMatch){
			// print
			//System.out.println("!!!QUERY MATCH!!!");
			//result = result + lineNumber +"\n";
			result = result + nodeOrder +"\n";
		}
	}
	
	public DVertexInfo stateAlreadyExistedInStack(ArrayList<NVertexInfo> nextNVertexInfos){
		DVertexInfo result = null;
		Iterator<DVertexInfo> it = stack.iterator();
		while(it.hasNext()){
			DVertexInfo d = it.next();
			LinkedList<NVertexInfo> currentNVertexInfos = d.d_vertex;
			Set<Object> set1 = new HashSet<Object>();
			set1.addAll(currentNVertexInfos);
			Set<Object> set2 = new HashSet<Object>();
			set2.addAll(nextNVertexInfos);
			/*
			System.out.println("set1: " + set1);
			System.out.println("set2: " + set2);
			*/
			// TODO not sure
			if (set1.equals(set2)) {
				return d;
			}
		}
		return result;
	}
	
	public void showStack(){
		String result = "Stack: [";
		Iterator<DVertexInfo> it = stack.iterator();
		while(it.hasNext()){
			DVertexInfo d = it.next();
			result = result + "T" + d.dver_name + " ";
		}
		// print
		//System.out.println(result + "]");
	}
	
	public String getResult() {
		return result;
	}
}
