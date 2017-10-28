import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BloomJoin 
{
	String r1;
	DynamicFilter f;
	HashMap <String, String> relation2;
	
	public BloomJoin(String r1, String r2) throws IOException
	{
		f = new DynamicFilter(8);
		this.r1 = r1;
		relation2 = new HashMap<String, String>();
		
		// Open the file for relation 2
		FileInputStream fstream = new FileInputStream(r2);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;

		// Read File Line By Line
		while ((line = br.readLine()) != null) {
		  // Add the first attribute to a Bloom Filter
		  String[] tokens = line.split("\\s+");
		  f.add(tokens[0]);
		  relation2.put(tokens[0], tokens[1]);
		}// end while loop adding elements from r2 to filter

		//Close the input stream
		br.close();
	}// end constructor BloomJoin
	
	public void join(String r3) throws IOException
	{
		// Open the file for relation 1
		FileInputStream fstream = new FileInputStream(r1);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String output = "";
		
		String line;
		// Read File Line By Line
		while ((line = br.readLine()) != null)   {
			// Split the line into columns
			String[] tokens = line.split("\\s+");
			
			// Join attribute is the 1st attribute
			if(f.appears(tokens[0])){
				output += tokens[1] + " " + tokens[0] + " " + relation2.get(tokens[0]) + "\n";
			}// end if this attribute appears in relation 2
		}// end while loop over relation 1
		
		//Close the input stream
		br.close();
		
		// write our string to r3
	    BufferedWriter writer = new BufferedWriter(new FileWriter(r3));
	    writer.write(output);
	    writer.close();
	}// compute the join of r1 and r2 and write result to r3
	
}// end class BloomJoin
