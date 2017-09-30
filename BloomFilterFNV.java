
public class BloomFilterFNV extends BloomFilter
{
	public BloomFilterFNV(int setSize, int bitsPerElement)
	{
		super(setSize, bitsPerElement);		
	}// end constructor BloomFilterFNV
	
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
		return super.filterSize();
	}// end function filterSize
	
	/* Returns the number of elements added to the filter. */
	public int dataSize()
	{
		return 0;
	}// end function dataSize
	
	/* Returns the number of hash function used */
	public int numHashes()
	{
		return super.numHashes();
	}// end function numHashes
	
	public void test()
	{
		System.out.println(filterSize());
		System.out.println(numHashes());
	}
	
}// end class BloomFilterFNV
