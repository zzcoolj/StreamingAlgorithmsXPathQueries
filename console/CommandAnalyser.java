package console;

public class CommandAnalyser {
	private String xmlFile;
	private String xpathQuery;

	public CommandAnalyser(String xmlFile, String xpathQuery) {
		this.xmlFile = xmlFile;
		this.xpathQuery = xpathQuery;
	}

	public int getXpathQueryType() {
		boolean result = xpathQuery.substring(2).contains("//");
		// If substring(starts from the 3rd letter) of xpath query has "//",
		// it's for the streaming algorithm using the lazy DFA, return 2.
		if (result) {
			return 2;
			// If not, it's for simple streaming algorithm, return 1.
		} else {
			return 1;
		}
	}
	
	

	public String getXmlFile() {
		return xmlFile;
	}
}
