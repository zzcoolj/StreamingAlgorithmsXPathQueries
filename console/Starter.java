package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Starter {

	public static void main(String[] args) throws IOException {
		// The document present in the input file should not be loaded in
		// memory, so we have to process line by line
		try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    }
		}
	}

}
