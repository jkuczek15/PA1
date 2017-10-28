import java.util.BitSet;

public class BloomFilter 
{
	// Size of the Bloom Filter
	public int size;
	
	// Number of elements added to the Bloom Filter
	public int dataSize;
	
	// Number of hash functions
	public int numHashes;
	
	// Main data structure for storing the Bloom Filter
	public BitSet filter;
	
	// Set of hash functions for storing elements in the filter
	public HashFunction[] hashFunctions;
	
	public BloomFilter(int setSize, int bitsPerElement)
	{
		size = setSize * bitsPerElement;
		numHashes = (int) Math.round(Math.log(2) * (size / setSize));
		filter = new BitSet(size);
		hashFunctions = new HashFunction[numHashes];
		dataSize = 0;
	}// end constructor BloomFilter
	
	/**
	 * Adds the string s to the filter. 
	 * This method should be case-insensitive. 
	 */
	public void add(String s)
	{
		int key;
		s = s.toLowerCase();
		
		if(!appears(s)){
			dataSize++;
		}// end if string not in filter, increase the data size
		
		for(int i = 0; i < numHashes; i++){
			// adding i to our string before we hash will ensure 
			// we have a unique hash value each iteration
			s = s + i;
			key = hashFunctions[0].hash(s);
			filter.set(key);
		}// end for each loop adding an element to the filter
		
	}// end function add
	
	/**
	 * Returns true if s appears in the filter, otherwise returns false 
	 * This method should be case-insensitive. 
	 */
	public boolean appears(String s)
	{
		int key;
		s = s.toLowerCase();
		
		for(int i = 0; i < numHashes; i++){
			// adding i to our string before we hash will ensure 
			// we have a unique hash value each iteration
			s = s + i;
			key = hashFunctions[0].hash(s);
			if(!filter.get(key)){
				// after hashing, one of the filter bits = 0
				return false;
			}// end if filter bit is 0
		}// end for each loop adding an element to the filter
		return true;
	}// end function appears
	
	/**
	 * Returns the size of the filter (size of the table)
	 */
	public int filterSize()
	{
		return size;
	}// end function filterSize
	
	/**
	 * Returns the number of elements added to the filter
	 */
	public int dataSize()
	{
		return dataSize;
	}// end function dataSize
	
	/**
	 * Returns the number of hash functions used
	 */
	public int numHashes()
	{
		return numHashes;
	}// end function numHashes
	
}// end class BloomFilter
