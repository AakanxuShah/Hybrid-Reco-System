# README

### CONTENTS OF THIS PROJECT
------------------------
**1) Java files :** 
		
	MovieDataConvert.java
	Sorting.java
	Sorting2.java	
	CF_movies.java
	CF_densed.java
	Item.java
	Predict.java
	Merge.java
	UserAccuracy.java

**2) Datasets :** 
	
	u.data -- The full u data set, 100000 ratings by 943 users on 1682 items.
            Each user has rated at least 20 movies.The data is randomly
            ordered. This is a tab separated list of 
	               user id | item id | rating

	movies.csv -- This CSV file is generated manually using MovieDataConvert.java file
		            Space between 3 elements are trimmed and replaced by comma.

	sorted_movies.csv -- This CSV file is generated using Sorting.java file .
			               movies.csv file is sorted by the user id in ascending order.

	item_sim.csv --  This CSV file is generated as the result of the program Item.java
			         This file shows the result of item-item similarity 

	prediction.csv -- This CSV file is generated as the result of the program Predict.java 
		                This file will be used to add the predictions to original dataset

	merged.csv -- This CSV file is generated using the Merge.java. This is the result of 
			       movies.csv and prediction.csv

	densed.csv -- This CSV file will be the densed dataset resulted from the sorting of 
		            the merged.csv . Sorting2.java is used for this sorting .

	
	CF_result_movies.csv -- This CSV file is result of CF_movies.java program .
				          This is the result of Pure Colloborating Filtering algo using 
				          sorted_movies.csv as Input file .

	CF_result_densed.csv -- This CSV file is result of CF_densed.java program .
				        This is the result of User-User CF augmenting by Output of Item-Item CF  using 
				        densed.csv as Input file . 

	
**3) Libraries and dependencies :**

- commons-math3-3.4.1.jar
- 	guava-18.0.jar
- 	mahout-core-0.9.jar
- 	mahout-integration-0.9.jar
- 	mahout-math-0.9.jar
- 	slf4j-api-1.7.10.jar
- 	slf4j-nop-1.7.10.jar





GUIDELINES TO RUN :
------------------------------

- To See the recommendation of the Pure Colloborative Filtering :
  Java File - CF_movies.java

- To Compile - javac CF_movies.java
- To Run - java CF_movies


- To see the recommendation for improved User-user CF augmenting by Output of Item-item CF using densed dataset
  Java File - CF_densed.java

- To Compile - javac CF_densed.java
- To Run - java CF_densed


- To see the User Accuracy using Mean Absolute Error :
  Java File - UserAccuracy.java

- To Compile - javac UserAccuracy.java
- To Run - java UserAccuracy
	

	
	
	
QUICK GUIDE FOR JAVA FILES : 
-----------------------------

1 -  MovieDataConvert.java -- converts u.data to movies.csv 

2 -  Sorting.java -- Sorts the user id in ascending order in movies.csv file and generates sorted_movies.

3 -  CF_movies.java -- Performs pure collaborative filtering on original dataset sorted_movies.csv

4 -  Item.java -- Performs item-item similarity that can be used to add more prediction and to make dataset dense.
		  Input file - sorted_movies.csv        Output file - item_sim.csv

5 -  Predict.java -- Gives prediction raings using input file - item_sim.csv and generates prediction.csv

6 -  Merge.java -- Merge the sorted with movies file sorted_movies.csv and Additional predictions , prediction.csv

7 -  Sorting2.java --Sort the final sorted densed file - merged.csv . Output file - densed.csv

8 -  CF_densed.java - Performs User-User CF augmenting by Output of Item-Item CF  using densed.csv as Input file .
			Output file - CF_result_densed.csv

9 - UserAccuracy.java - Comparison of results using Mean Absolute Error. Gives the MAE for both - pure collaborative 
			filtering and Improved CF (User-User CF augmenting by Output of Item-Item CF)

