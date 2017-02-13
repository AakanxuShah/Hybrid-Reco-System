
import java.io.*;
import java.util.*;
 
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
 
public class CF_movies {
 
  public static void main(String[] args) throws Exception {
      // Create a data source from the CSV file
      File userPreferencesFile = new File("data/sorted_movies.csv");
      
      //BufferedReader br = new BufferedReader(new FileReader("data/BX-Book-Ratings_comma.csv"));
      BufferedWriter bw = new BufferedWriter(new FileWriter("data/CF_result_movies.csv"));
      
      DataModel dataModel = new FileDataModel(userPreferencesFile);
      
      UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
      
      UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity, dataModel);
 
      // Create a generic user based recommender with the dataModel, the userNeighborhood and the userSimilarity
      Recommender genericRecommender =  new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
 
      // Recommend 5 items for each user
      for (LongPrimitiveIterator iterator = dataModel.getUserIDs(); iterator.hasNext();)
      {
          long userId = iterator.nextLong();
 
          // Generate a list of 5 recommendations for the user
          List<RecommendedItem> itemRecommendations = genericRecommender.recommend(userId, 5);
          System.out.println("");   
          System.out.println("------------------");
          System.out.format("User Id: %d%n", userId);
          bw.write("----------------------------------------------------");
          bw.write("\n");
          bw.write("User Id:"+userId);
          bw.write("\n");
 
          if (itemRecommendations.isEmpty())
          {
              System.out.println("No recommendations for this user.");
              bw.write("No recommendation for this user");
              bw.write("\n");
          }
          else
          {
              // Display the list of recommendations
              for (RecommendedItem recommendedItem : itemRecommendations)
              {
                                  
                  bw.write("Recommened Item Id :" + String.valueOf(recommendedItem.getItemID()) +", Strength of the preference : "+ recommendedItem.getValue());
                  bw.write("\n");
                  
                  System.out.format("Recommened Item Id : %d , Strength of the preference : %f%n", recommendedItem.getItemID(), recommendedItem.getValue());
              }
         }
      }
  bw.close();}
}
