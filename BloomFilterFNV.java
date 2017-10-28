import java.math.BigInteger;

public class BloomFilterFNV extends BloomFilter
{	
	public BloomFilterFNV(int setSize, int bitsPerElement)
	{
		super(setSize, bitsPerElement);
		hashFunctions[0] = new HashFunctionFNV();
	}// end constructor BloomFilterFNV
	
	private class HashFunctionFNV extends HashFunction
	{
		private final BigInteger FNV_INIT = new BigInteger("109951168211");
		private final BigInteger FNV_PRIME = new BigInteger("14695981039346656037");
		private final BigInteger FNV_BIG = new BigInteger("9223372036854775807");
		
	    public int hash(String s)
	    {
	    	return hash(s.getBytes());
	    }// end hash function
	    
	    private int hash(byte[] k) 
	    {
	        long h = FNV_INIT.longValue();
	        int len = k.length;
	        for(int i = 0; i < len; i++) {
	            h ^= k[i];
	            h = mod(h * FNV_PRIME.longValue(), FNV_BIG.longValue());
	        }// end for loop over all bytes
	        return mod(h, size);
	    }// end function hashFNV

	}// end class HashFunctionFNV
	
}// end class BloomFilterFNV
