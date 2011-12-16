package edu.mit.yingyin.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ArrayDuplicatesTest {
  private static final long SEED = 100000L;
  private static final int SIZE = 1000000;
  private int[] array;
  private int duplicate;
  
  @Before
  public void setUp() {
    java.util.Random rand = new java.util.Random(SEED);
    array = new int[SIZE];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }
    for (int i = 0; i < array.length / 2; i++) {
      int tmp = array[i];
      int j = rand.nextInt(SIZE);
      array[i] = array[j];
      array[j] = tmp;
    }
    while (true) {
      int index1 = rand.nextInt(SIZE);
      int index2 = rand.nextInt(SIZE);
      if (index1 == index2)
        continue;
      duplicate = array[index1];
      array[index2] = duplicate;
      break;
    }
  }
  
  @Test
  public void testFindOneDuplicateWithSorting() {
    assertEquals(duplicate, ArrayDuplicates.findOneDuplicateWithSorting(array));
  }
}
