package edu.mit.yingyin.util;

import java.util.Arrays;

/**
 * Random number generator based on Knuth's GB_FLIP, the module used by 
 * GraphBase programs to generate random numbers. Based on the lagged Fibbonacci
 * generator a_n = (a_{n-24} - a_{n-55}) mond m, GB_FLIP provides values on the
 * range zero inclusive to 2^31 exclusive, has a period of 2^85 - 2^30, and the 
 * low-order bits of the generated numbers are just as random as the high-order
 * bits. @see <a href="http://tex.loria.fr/sgb/gb_flip.pdf">Knuth's original 
 * version of the program</a>.
 * 
 * @author yingyin
 *
 */
public class Random {
  /**
   * The index of the next pseudo-random value in randArray to be used.
   */
  private int index = 0;
  /**
   * Pseudo-random values.
   */
  private long[] randArray = new long[56];
  
  public Random() {
    Arrays.fill(randArray, -1);
  }
  
  /**
   * @return the next random number.
   */
	public long next() {
	  long val;
		if (randArray[index] >= 0) {
		  val = randArray[index];
		  index--;
		} else {
		  val = flipCycle();
		}
		return val;
	}
	
	/**
	 * Returns a uniform integer between 0 and n - 1, inclusive.
	 * @param n a positive integer less than 2^31.
	 * @return
	 */
	public long next(long n) {
	  long r = 0x80000000L - (0x80000000L % n);
	  long t = r;
	  while (r >= t)
	    r = next();
	  return r % n;
	}
	
	/**
	 * Initialize the random number generator with a seed.
	 * @param seed
	 */
	public void setSeed(long seed) {
		// Strips off the sign.
	  long prev = modDiff(seed, 0);
	  seed = prev;
	  randArray[55] = prev;
	  long next = 1L;
	  for (int i = 21; i > 0; i = (i + 21) % 55) {
	    randArray[i] = next;
	    next = modDiff(prev, next);
	    if ((seed & 1L) > 0)
	      seed = 0x40000000L + (seed >> 1);
	    else seed >>= 1; // Cyclic shift right 1.
	    next = modDiff(next, seed);
	    prev = randArray[i];
	  }
	  
	  for (int i = 0; i < 5; i++)
	    flipCycle();
	}

	/**
	 * Computes 55 more pseudo-random numbers.
	 * @return the next pseudo-random number.
	 */
	private long flipCycle() {
	  for (int i = 1; i < 56; i++) {
	    randArray[i] = modDiff(randArray[i], randArray[(i + 30) % 55 + 1]);
	  }
	  index = 54;
	  return randArray[55];
	}
	
	/**
	 * Computes difference modulo 2^31.
	 * @param x
	 * @param y
	 * @return
	 */
	private long modDiff(long x, long y) {
	  return (x - y) & 0x7fffffffL;
	}
}
