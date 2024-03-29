import java.util.concurrent.ThreadLocalRandom;

public class HashFunctionRan extends HashFunction
{
	// create a Random HashFunction for BloomFilterRan
	int a, b, p;
	
	public HashFunctionRan(int range)
	{
		this.p = getPrime(range);
		this.a = ThreadLocalRandom.current().nextInt(0, p);
		this.b = ThreadLocalRandom.current().nextInt(0, p);
	}// end constructor for HashFunction
	
	public int hash(String s)
	{
		return hash(s.hashCode());
	}// end function for hashing a string
	
	private int hash(int x)
	{
		return mod((a*x) + b, p);
	}// end function for hashing an integer
	
	private int getPrime(int n)
	{
		// return the first positive prime of at least size n
		boolean found = false;
		
		while(!found){
			// while loop until we find a prime >= n
			if(isPrime(n)){
				// found a prime
				found = true;
			}else{
				// did not find prime
				if(n == 1 || n % 2 == 0){
					n = n + 1;
				}else{
					n = n + 2;
				}// end if we have an even number
				
			}// end if this is a prime
			
		}// end while we haven't found a prime
		
		return n;
	}// end function getPrime
	
	private boolean isPrime(int num)
	{
	    if ( num > 2 && num % 2 == 0 ) {
	        return false;
	    }// end if number > 2 and even
	    int top = (int) Math.sqrt(num) + 1;
	    for(int i = 3; i < top; i+=2){
	        if(num % i == 0){
	            return false;
	        }// end if we found a divisor, not a prime
	    }// end for loop checking if prime
	    return true; 
	}// end function isPrime
	
}// end class HashFunctionRan
