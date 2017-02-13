import java.io.*;
 
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
 
public class UserAccuracy {
 
  public static void main(String[] args) throws Exception {
  
	  // Create a data source from the densed final CSV file
      File userPreferencesFile1 = new File("data/densed.csv");
      DataModel dataModel1 = new FileDataModel(userPreferencesFile1);
       
      RecommenderEvaluator recommenderEvaluator1 = new AverageAbsoluteDifferenceRecommenderEvaluator();    
           
      RecommenderBuilder recommenderBuilder1 = new RecommenderBuilder() {
          @Override
          public Recommender buildRecommender(DataModel dataModel1) throws TasteException {
              UserSimilarity userSimilarity1 = new PearsonCorrelationSimilarity(dataModel1);
              UserNeighborhood userNeighborhood1 = new NearestNUserNeighborhood(10, userSimilarity1, dataModel1);
 
              // Return a new instance of a generic user based recommender with the dataModel, the userNeighborhood and the userSimilarity
              return new GenericUserBasedRecommender(dataModel1, userNeighborhood1, userSimilarity1);
          }
      };
           
      // Build a model with 80% training percentage 
      
      double score1 = recommenderEvaluator1.evaluate(recommenderBuilder1, null, dataModel1, 0.80, 1.0);
      System.out.println("----------------------------------------------");
      System.out.println("");
      System.out.format("The Recommender Evaluation Score for Augmented Densed U-I Matrix is : %f%n", score1);
      System.out.println("");
      System.out.println("");
      
	  
	  
	  
	  // Create a data source from the original CSV file
      File userPreferencesFile = new File("data/movies.csv");
      DataModel dataModel = new FileDataModel(userPreferencesFile);
       
      RecommenderEvaluator recommenderEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();    
           
      RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
          @Override
          public Recommender buildRecommender(DataModel dataModel) throws TasteException {
              UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
              UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity, dataModel);
 
              // Return a new instance of a generic user based recommender with the dataModel, the userNeighborhood and the userSimilarity
              return new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
          }
      };
           
      // Build a model with 80% training percentage 
      
      double score = recommenderEvaluator.evaluate(recommenderBuilder, null, dataModel, 0.80, 1.0);
      System.out.println("----------------------------------------------");
      System.out.println("");
      System.out.format("The Recommender Evaluation Score for original U-I matrix is : %f%n", score);
      System.out.println("");
      System.out.println("");
      
      
  
  }
}
