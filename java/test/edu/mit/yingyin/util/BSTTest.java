package edu.mit.yingyin.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BSTTest {
  @Test
  public void testBSTLess() {
    BST bst = new BST();
    int rank = bst.insert(1, true);
    assertEquals(0, rank);
    rank = bst.insert(2, true);
    assertEquals(1, rank);
    rank = bst.insert(0, true);
    assertEquals(0, rank);
    rank = bst.insert(6, true);
    assertEquals(3, rank);
    rank = bst.insert(5, true);
    assertEquals(3, rank);
    rank = bst.insert(6, true);
    bst.checkRI();
    assertEquals(4, rank);
  }
  
  @Test
  public void testBSTGreater() {
    BST bst = new BST();
    boolean less = false;
    int rank = bst.insert(1, less);
    assertEquals(0, rank);
    rank = bst.insert(2, less);
    assertEquals(0, rank);
    rank = bst.insert(0, less);
    assertEquals(2, rank);
    rank = bst.insert(6, less);
    assertEquals(0, rank);
    rank = bst.insert(5, less);
    assertEquals(1, rank);
    rank = bst.insert(6, less);
    assertEquals(0, rank);
  }
  
  @Test
  public void testRI() {
    int[] input = {42, 87, 3, 27, 29, 40, 12, 3, 69, 9, 57, 60, 33, 99, 78, 16,
        35, 97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28,
        91, 94, 57, 1, 53, 8, 44, 68, 90, 24, 96, 30, 22, 66, 49, 24};
    BST bst = new BST();
    boolean less = true;
    for (int a : input) {
      bst.insert(a, less);
      bst.checkRI();
    }
  }
}
