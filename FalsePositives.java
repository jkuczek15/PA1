import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FalsePositives 
{
	// Array of filters = [BloomFilterFNV, BloomFilterMurmur, BloomFilterRan]
	private static BloomFilter[] filters;
	
	// Dynamic filter
	private static DynamicFilter dynamic;
	
	// List of all strings we have added to the filters
	private static ArrayList<String> list;
	
	// Number of strings to test for false positives
	private static final double numTests = 10000;
	
	// Number of elements we are storing in our filters
	private static final int setSize = 100000;
	
	// Max number of bits per element we are storing
	private static final int bitsPerElement = 4;
	
	/**
	 * Main test experiment for False Positives
	 */
	public static void main(String[] args){
		// We keep setSize, bitsPerElement, and numTests constant for each filter
		// This ensures that we get an accurate false positive rate/performance
		// Initialize four bloom filters for the experiment
		initialize();
		
		// Keep track of the number of false positives for each filter
		int[] falsePositives = new int[4];
 		String random;
		
		double i = numTests;
		do {
			// run the false positive experiment for "numTests" strings
			// generate a random string
			random = randomString();
			
			if(list.contains(random.toLowerCase())){
				// if our list already contains the string, skip it and re-test
				continue;
			}// end if list actually contains this random string
			
			for(int j = 0; j < filters.length; j++){
				if(filters[j].appears(random)){
					falsePositives[j]++;
				}// end if we have a false positive
			}// end for loop over all bloom filters besides dynamic
			
			if(dynamic.appears(random)){
				falsePositives[3]++;
			}// end if random string appears in the dynamic filter
			
			i--;
		}while (i > 0);
		
		// Print the results of the experiment
		System.out.println("False Positive Rates:\n");
		
		double percent;
		for(int j = 0; j < filters.length; j++){
			percent = ((double) falsePositives[j] / numTests) * 100;
			System.out.printf(filters[j].getClass().getName() + ": " + "%.2f%%\n", percent);
		}// end for loop printing out false positive results
		
		percent = ((double) falsePositives[3] / numTests) * 100;
		System.out.printf("DynamicFilter: " + "%.2f%%\n", percent);
	}// end function main
	
	/**
	 * Initializes all Bloom Filters and adds random strings to them
	 */
	private static void initialize()
	{
		// Create our Bloom Filters and list of strings for checking if a string
		// truly exists in the filter
		list = new ArrayList<String>();
		filters = new BloomFilter[3];
		filters[0] = new BloomFilterFNV(setSize, bitsPerElement);
		filters[1] = new BloomFilterMurmur(setSize, bitsPerElement);
		filters[2] = new BloomFilterRan(setSize, bitsPerElement);
		dynamic = new DynamicFilter(bitsPerElement);
		
		String random;
		for(int i = 0; i < setSize; i++){
			// choose a random length for our string
			random = randomString();
			
			for(int j = 0; j < filters.length; j++){
				// add our random string to each filter
				filters[j].add(random);
			}// end for loop over all filters
			
			// add our random string to the dynamic filter and list of strings
			dynamic.add(random);
			list.add(random.toLowerCase());
		}// end for loop adding elements to our filters
		
	}// end function initialize
	
	/**
	 * Generates a random string of length len
	 */
	private static String randomString() {
		// all possible characters for when generating a random string, allow special characters
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()[]-=+_;:'\"<>?/|\\,.";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        // length of the random string
        int len = ThreadLocalRandom.current().nextInt(3, bitsPerElement + 1);
        while (salt.length() < len) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }// end while loop building our string
        
        String saltStr = salt.toString();
        return saltStr;
    }// end function for generating a random string
	
}// end class FalsePositives
