		
		
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sorting2 {
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader reader = new BufferedReader(new FileReader("data/merged.csv"));
    	
    	Map<String, List<String>> map = new TreeMap<String, List<String>>();
    	
    	String line = reader.readLine();//read header
    	
    	while ((line = reader.readLine()) != null) {
    	
    		String key = getField(line);
    		
    		List<String> l = map.get(key);
    		
    		if (l == null) {
    			l = new LinkedList<String>();			// sort the data by User ID
    			map.put(key, l);
    		}
    		l.add(line);

    	}
    	reader.close();
    	
    	FileWriter writer = new FileWriter("data/densed.csv");
    	//writer.write("UserID, MoviesId, Ratings\n");
    	for (List<String> list : map.values()) {
    		for (String val : list) {
    			writer.write(val);
    			writer.write("\n");
    		}
    	}
    	writer.close();
    }

    private static String getField(String line) {
    	return line.split(",")[0];// extract value you want to sort on
    }
}



