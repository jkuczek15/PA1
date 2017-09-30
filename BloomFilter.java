import java.util.BitSet;

public class BloomFilter 
{
	private int size;
	private int numHashes;
	private BitSet filter;
	
	public BloomFilter(int setSize, int bitsPerElement)
	{
		size = setSize * bitsPerElement;
		numHashes = (int) Math.round(Math.log(2) * (size / setSize));
		filter = new BitSet(size);
	}// end constructor BloomFilter
	
	/* Adds the string s to the filter. 
	 * This method should be case-insensitive. */
	public void add(String s)
	{
		
	}// end function add
	
	/* Returns true if s appears in the filter; otherwise returns false. 
	 * This method must be case-insensitive */
	public boolean appears(String s)
	{
		return true;
	}// end function appears
	
	/* Returns the size of the filter (the size of the table). */
	public int filterSize()
	{
		return size;
	}// end function filterSize
	
	/* Returns the number of elements added to the filter. */
	public int dataSize()
	{
		return 0;
	}// end function dataSize
	
	/* Returns the number of hash function used */
	public int numHashes()
	{
		return numHashes;
	}// end function numHashes
	
}// end class BloomFilter
