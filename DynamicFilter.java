import java.util.ArrayList;

public class DynamicFilter
{
	static int setSize = 1000;
	int dataSize;
	int bitsPerElement;
	
	// Array list of Random Bloom Filters
	ArrayList<BloomFilterRan> filters = new ArrayList<BloomFilterRan>();
	
	public DynamicFilter(int bitsPerElement)
	{
		this.bitsPerElement = bitsPerElement;	
		filters.add(new BloomFilterRan(setSize, bitsPerElement));
	}// end constructor DynamicFilter
	
	/**
	 * Adds the string s to the filter. 
	 * This method should be case-insensitive. 
	 */
	public void add(String s)
	{
		s = s.toLowerCase();
		
		if(!appears(s)){
			dataSize++;
		}// end if string not in filter, increase the data size
		
		if(dataSize >= setSize){
			setSize *= 2;
			filters.add(new BloomFilterRan(setSize, bitsPerElement));
		}// end if we need to increase the size of the filter
		
		for(int i = 0; i < filters.size(); i++){
			filters.get(i).add(s);
		}// end for loop over all bloom filters
		
	}// end function add
	
	/**
	 * Returns true if s appears in the filter, otherwise returns false 
	 * This method should be case-insensitive. 
	 */
	public boolean appears(String s)
	{
		s = s.toLowerCase();
		
		for(int i = 0; i < filters.size(); i++){
			// for loop checking each filter
			if(!filters.get(i).appears(s)){
				return false;
			}// end if this string not found in the filter

		}// end for loop over all bloom filters
		
		return true;
	}// end function appears
	
	/**
	 * Returns the size of the filter (size of the table)
	 */
	public int filterSize()
	{
		int size = 0;
		for(int i = 0; i < filters.size(); i++){
			size += filters.get(i).filterSize();
		}// end for loop over all filters
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
		int hashes = 0;
		for(int i = 0; i < filters.size(); i++){
			hashes += filters.get(i).numHashes();
		}// end for loop over all filters
		return hashes;
	}// end function numHashes
	
}// end class DynamicFilter
