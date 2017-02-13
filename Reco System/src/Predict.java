

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;


public class Predict {

	public static void main(String[] args) throws IOException {
		
		FileWriter fw = null;
		String entry[][]=new String[1682][3];
		BufferedReader r1 = new BufferedReader(new FileReader("data/item_sim.csv"));	
		String line1 = r1.readLine();
		int i=0;
		while (line1 != null) {
			
			String token1[] = line1.split(",");							
			entry[i][0]=token1[0];		
			entry[i][1]=token1[1];					// Stores all the best similarities
			entry[i][2]=token1[2];
			i++;
			line1 = r1.readLine();
		 }
		
		
		
		
		BufferedReader r = new BufferedReader(new FileReader("data/sorted_movies.csv"));	
		fw = new FileWriter("data/prediction.csv");
		String line = r.readLine();
		
		
		while (line != null) {
			String token[] = line.split(",");			
		
			if (token[2].equals("5")){
						
						int row = Integer.parseInt(token[1]);					
						float rec = Float.parseFloat(entry[row][2]) *5;		// multiply similarity of item with 5
						
						
						fw.write(token[0]);
						fw.write(",");
						fw.write(entry[row][1]);						// Store the prediction for each "5" rating
						fw.write(",");							
						fw.write(String.valueOf(rec));
		                fw.write("\n");			
						
			}
			else if (token[2].equals("4")){
				
				
				int row = Integer.parseInt(token[1]);
				float rec = Float.parseFloat(entry[row][2]) *4;		// multiply similarity of item with 4
				
				
				fw.write(token[0]);
				fw.write(",");
				fw.write(entry[row][1]);				// Store the prediction for each "4" rating
				fw.write(",");
				fw.write(String.valueOf(rec));
                fw.write("\n");			
				
	}
	
			line = r.readLine();
		}
					
	
		r1.close();  
		r.close();
		fw.flush();
		fw.close();
	}	
}


