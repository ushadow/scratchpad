package edu.mit.yingyin.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>RangeSum</code> allows both range sum queries of an array of 
 * integers and value updates in O(log n) time. 
 * 
 * @author yingyin
 *
 */
public class RangeSum {

  private static class Node {
    public int sum;
    
    /**
     * Number of elements that the sum represents.
     */
    public int size;
    public Node left, right, parent;
    
    Node(int sum, int size) {
      this.sum = sum;
      this.size = size;
    }
    
    /**
     * Creates a <code>Node</code> with <code>left</code> and <code>right</code>
     * children.
     * 
     * @param left left child should never be null.
     * @param right right child can be null.
     */
    Node(Node left, Node right) {
      this.left = left;
      this.right = right;
      sum = left.sum;
      size = left.size;
      if (right != null) {
        sum += right.sum;
        size += right.size;
      }
    }
  }
  
  private Node root;
  private List<Node> elements;
  
 /**
  * Sets the <code>RangeSum</code> with the <code>array</code> for queries.
  * 
  * @param array array of integers for queries.
  */
  public void setup(int[] array) {
    List<Node> nodes = new ArrayList<Node>();
    for (int i = 0; i < array.length; i++) {
      nodes.add(new Node(array[i], 1));
    }
    elements = nodes;
    while (nodes.size() > 1) {
      List<Node> parents = new ArrayList<Node>();
      for (int i = 0; i < nodes.size() / 2; i++) {
        Node left = nodes.get(i * 2);
        Node right = nodes.get(i * 2 + 1);
        Node node = new Node(left, right );
        left.parent = node;
        right.parent = node;
        parents.add(node);
      }
      if (nodes.size() % 2 == 1) {
        Node left = nodes.get(nodes.size() - 1);
        Node node = new Node(left, null);
        parents.add(node);
        left.parent = node;
      }
      nodes = parents;
    }
    
    root = nodes.get(0);
    checkRI();
  }
  
  /**
   * Returns the sum of elements i to j inclusive.
   * 
   * @param i index of the starting element such that i >= 0 and 
   *    i < <code>array</code>.length.
   * @param j index of the ending element such that j >= i and j >=0 and 
   *    j < <code>array</code>.length.
   * @return the sum of the elements from indices i to j.
   */
  public int sum(int i, int j) {
    return sum0(j, root) - sum0(i - 1, root);
  }
  
  /**
   * Updates the value of an element in the <code>array</code>.
   * 
   * @param index the index of the element to be updated.
   * @param value the new value of that element.
   */
  public void update(int index, int value) {
    Node element = elements.get(index);
    element.sum = value;
    Node current = element.parent;
    while (current != null) {
      current.sum = current.left.sum;
      if (current.right != null)
        current.sum += current.right.sum;
      current = current.parent;
    }
    checkRI();
  }
  
  private int sum0(int i, Node node) {
    if (i < 0)
      return 0;
    
    Node current = node;
    while (current.size > i + 1) 
      current = current.left;
    
    if (current.size == i + 1)
      return current.sum;
    
    return current.sum + sum0(i - current.size, current.parent.right);
  }
  
  private void checkRI() {
    assert root.parent == null;
    assert root != null;
    checkSumAndSize(root);
  }
  
  private void checkSumAndSize(Node node) {
    if (node.left == null && node.right == null) { 
      assert node.size == 1;
    } else {
      assert node.left != null;
      int sum = node.left.sum;
      int size = node.left.size;
      if (node.right != null) {
        sum += node.right.sum;
        size += node.right.size;
      }
      assert node.sum == sum;
      assert node.size == size;
      checkSumAndSize(node.left);
      if (node.right != null)
        checkSumAndSize(node.right);
    }
  }
}
