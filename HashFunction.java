
public abstract class HashFunction 
{
   /* 
	* All hash functions should be able to hash a byte array
	*/
	public abstract int hash(String s);
	
	/* 
	* Mod result will occasionally be negative, this function 
	* corrects the issue and casts long to int (required for BitSet)
	*/
	public static int mod(long x, long y)
	{
		long result = x % y;
	    if (result < 0){
	        result += y;
	    }// end if result is negative
	    Long l = new Long(result);
	    return l.intValue();
	}// end function mod
	
}// end interface HashFunction
