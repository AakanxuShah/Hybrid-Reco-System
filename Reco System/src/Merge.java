

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Merge {

	public static void main(String[] args) throws IOException {
		
		BufferedReader r = new BufferedReader(new FileReader("data/sorted_movies.csv"));
		String line = r.readLine();				// reads File 1
		
		BufferedReader r1 = new BufferedReader(new FileReader("data/prediction.csv"));
		String line1 = r1.readLine();			// reads File 2
		
		FileWriter fw = null;
		fw = new FileWriter("data/merged.csv");		// Both the files are merged
		
		while(line!=null){
		
		fw.write(line+"\n");
		line = r.readLine();
		}
		
		r.close();
		
		while(line1 != null){
			fw.write(line1+"\n");
			line1 = r1.readLine();
			}
		
		r1.close();
		fw.flush();
		fw.close();
		
	}

}
