package edu.mit.yingyin.util;

import java.util.Arrays;

public class ArrayDuplicates {
  /**
   * Finds a duplicate value in an integer array. 
   * 
   * @param array
   * @return one duplicate value or the minimum integer value if there is no
   *    duplicate.
   */
  public static int findOneDuplicateWithSorting(int[] array) {
    int result = Integer.MIN_VALUE;
    if (array.length <= 1)
      return result;
    
    Arrays.sort(array);
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] == array[i])
        return array[i];
    }
    return result;
  }
}
