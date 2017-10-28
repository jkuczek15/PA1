
public class BloomFilterRan extends BloomFilter
{
	public BloomFilterRan(int setSize, int bitsPerElement)
	{
		super(setSize, bitsPerElement);
		hashFunctions = new HashFunctionRan[numHashes];
		
		for(int i = 0; i < numHashes; i++){
			hashFunctions[i] = new HashFunctionRan(size);
		}// end for loop creating hash functions
		
	}// end constructor BloomFilterRan
	
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
			key = hashFunctions[i].hash(s);
			filter.set(HashFunction.mod(key, size));
		}// end for loop over all hashFunctions
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
			key = hashFunctions[i].hash(s);
			if(!filter.get(HashFunction.mod(key, size))){
				// after hashing, one of the filter bits = 0
				return false;
			}// end if filter bit is 0
		}// end for loop over all hashFunctions
		
		return true;
	}// end function appears
	
}// end class BloomFilterRan
