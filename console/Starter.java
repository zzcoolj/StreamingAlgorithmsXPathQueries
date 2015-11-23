package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Starter {

	public static void main(String[] args) throws IOException {
		// Measure execution time
		long startTime = System.nanoTime();
		
		// TODO we define fileName is example.txt temporarily -> TODO xmlFile = args[0]
		//String xmlFile = "input.txt";
		String xmlFile = args[0];
		// TODO command = args[1]
		//String xpathQuery = "//a/b//a/b";
		String xpathQuery = args[1];
		
		CommandAnalyser ca = new CommandAnalyser(xmlFile, xpathQuery);
		String result = null;
		String resultType = null;
		SimpleStreamingAlgorithm ssa = null;
		LazyDFAAlgorithm lda = null;
		if(ca.getXpathQueryType()==1){
			resultType = "Type 1";
			// print
			//System.out.println("xpathQuery: " + xpathQuery + "	queryType: " + resultType + "\n");
			ssa = new SimpleStreamingAlgorithm(xpathQuery);
		} else if(ca.getXpathQueryType()==2) {
			resultType = "Type 2";
			// print
			//System.out.println("xpathQuery: " + xpathQuery + "	queryType: " + resultType + "\n");
			lda = new LazyDFAAlgorithm(xpathQuery);
			
			// startDocument
			lda.startDocument();
		} else {
			result = "XPath query is wrong.";
		}
		
		// The document present in the input file should not be loaded in
		// memory, so we have to process line by line
		try (BufferedReader br = new BufferedReader(new FileReader(ca.getXmlFile()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(ca.getXpathQueryType() == 1){
		    	   ssa.processXmlLine(line);
		       }else if(ca.getXpathQueryType()==2){
		    	   lda.processXmlLine(line);
		       }
		    }
		}
		
		// TODO return result
		if(ca.getXpathQueryType()==1){
			result = ssa.getResult();
			// print
			//System.out.println("***********************************Result***********************************"+"\n"+"result:" + "\n" +  result);
		} else if(ca.getXpathQueryType()==2) {
			result = lda.getResult();
			// print
			//System.out.println("***********************************Result***********************************"+"\n"+"result:" + "\n" +  result);
		} else {
			
		}
		System.out.println(result);
	
		// Measure execution time
		long stopTime = System.nanoTime();
		// print
		//System.out.println("Execution time(nanoseconds): " + (stopTime - startTime));
		
		// Measure memory usage
		Runtime rt = Runtime.getRuntime();
		rt.gc();
	    long usedBytes = (rt.totalMemory() - rt.freeMemory());
	    // print
	    //System.out.println("memory usage(bytes): " + usedBytes);
	}

}
