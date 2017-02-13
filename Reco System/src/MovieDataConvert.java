import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieDataConvert {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("data/u.data")); 		//Input  : Original given data
		FileWriter bw = new FileWriter("data/movies.csv");							//Output : cleaned CSV data
		
		String line;
		line = br.readLine();

		
		while(line != null) {
			String[] values = line.split("\t"); 	// splits the values 
			bw.write(values[0] + "," + values[1] + "," + values[2] + "\n");		// writes in CSV formet
			line = br.readLine();
		}
		
		br.close();
		bw.flush();
		bw.close();

	}

}
