package console;

import java.util.LinkedList;

public class SimpleStreamingAlgorithm {
	private String[] queryParameters;
	private int lineNumber;
	private LinkedList<seqElement> seq;
	private String result;
	private int nodeOrder;

	public SimpleStreamingAlgorithm(String query) {
		queryParameters = getParametersOfQueryForSSA(query);
		lineNumber = 0;
		seq = new LinkedList<seqElement>();
		result = "";
		nodeOrder = 0;
		showQueryParameters();
	}

	// SSA: SimpleStreamingAlgorithm
	public String[] getParametersOfQueryForSSA(String query) {
		String[] parameters = query.substring(2).split("/");
		return parameters;
	}
	
	public void showQueryParameters() {
		for(int i=0; i<queryParameters.length; i++) {
			// print
			//System.out.println("queryParameters[" + i + "]: " + queryParameters[i]);
		}
		// print
		//System.out.println();
	}
	
	public void processXmlLine(String line){
		// print
		//System.out.println("lineNumber: " + lineNumber + "	" + "processLine: " + line);
		String[] lineElements = line.split(" "); 
		int startEnd = Integer.parseInt(lineElements[0]);
		String seqName = lineElements[1];
		
		seq.add(new seqElement(startEnd, seqName));
		//TODO test
		
		
		if((startEnd==0) && (seqName.equals(queryParameters[queryParameters.length-1]))) {
			failureTransition();
			pathMatchCheck();
		}
		// print
		//System.out.println();
		lineNumber++;
		if(startEnd == 0) nodeOrder++;
	}
	
	public void failureTransition(){
		// print
		//System.out.println("------failureTransition function in------");
		// print
		//System.out.println("original Seq:	" + seq);
		boolean stop = false;
		while((!stop) && (seq.size()>1)) {
			boolean seqChanged = false;
			for(int i=0; i<seq.size()-1; i++){
				seqElement e1 = seq.get(i);
				seqElement e2 = seq.get(i+1);
				if((e1.getStartEnd()==0) && (e2.getStartEnd()==1) && (e1.getName().equals(e2.getName()))){
					seq.remove(e1);
					seq.remove(e2);
					seqChanged = true;
					break;
				}
			}
			if(!seqChanged) stop=true;
		}
		// print
		//System.out.println("Seq:		" + seq);
		//System.out.println("------failureTransition function out------");
	}
	
	public void pathMatchCheck(){
		if(seq.size() >= queryParameters.length){
			boolean totalMatch = true;
			for(int i=0; i<queryParameters.length; i++) {
				if(!queryParameters[queryParameters.length-1-i].equals(seq.get(seq.size()-1-i).getName())){
					totalMatch = false;
					break;
				}
			}
			if(totalMatch){
				// print
				//System.out.println("Match!!!");
				//result = result + lineNumber +"\n";
				result = result + nodeOrder +"\n";
			}
		}
	}

	public String getResult() {
		return result;
	}
}
