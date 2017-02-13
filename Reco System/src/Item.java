

import java.io.File;
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

public class Item {
	
	public static void main(String args[]){	
	
	
		FileWriter fileWriter = null;
	{
	try {
		
		// Find the Item Similarity 
		DataModel dm = new FileDataModel (new File(("data/sorted_movies.csv")));
		ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
		
		fileWriter = new FileWriter(("data/item_sim.csv"));
		
		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm,sim);
		
		int x=1;
		
		for(LongPrimitiveIterator items= dm.getItemIDs(); items.hasNext();){
			long itemId= items.nextLong();
			List<RecommendedItem>recommendations=recommender.mostSimilarItems(itemId,1);
				// Computes the best Similar item for each Item
				
			for(RecommendedItem recommendation: recommendations){
									
					fileWriter.write(String.valueOf(itemId));
					fileWriter.write(",");
					fileWriter.write(String.valueOf(recommendation.getItemID()));
					fileWriter.write(",");
					fileWriter.write(String.valueOf(recommendation.getValue()));
	                fileWriter.write("\n");
				}
			
		}  

	
		
	}
	catch (IOException e1) {
		
		System.out.println("error");
		e1.printStackTrace();
	}
	catch (TasteException e) {
		
		System.out.println("error1");
		e.printStackTrace();
	}
	finally {
            try {
		                fileWriter.flush();
		                fileWriter.close();
		    } 
            catch (IOException e){
		                System.out.println("Error while flushing/closing fileWriter !!!");
		                e.printStackTrace();
		    }
	}

	
	}
}
}