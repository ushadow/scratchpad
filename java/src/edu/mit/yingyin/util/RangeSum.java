package edu.mit.yingyin.util;

import java.util.ArrayList;
import java.util.List;

public class RangeSum {

  public static class Node {
    public int sum, size;
    public Node left, right, parent;
    
    Node(int sum, int size) {
      this.sum = sum;
      this.size = size;
    }
    
    /**
     * 
     * @param left is never null.
     * @param right can be null.
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
  
  public void setup(int[] array) {
    List<Node> nodes = new ArrayList<Node>();
    for (int i = 0; i < array.length; i++) {
      nodes.add(new Node(array[i], 1));
    }
    while (nodes.size() > 1) {
      List<Node> parents = new ArrayList<Node>();
      for (int i = 0; i < nodes.size() / 2; i++) {
        Node node = new Node(nodes.get(i * 2), nodes.get(i * 2 + 1));
        nodes.get(i * 2).parent = node;
        nodes.get(i * 2 + 1).parent = node;
        parents.add(node);
      }
      if (nodes.size() % 2 == 1) {
        Node node = new Node(nodes.get(nodes.size() - 1), null);
        parents.add(node);
        nodes.get(nodes.size() - 1).parent = node;
      }
      nodes = parents;
    }
    
    root = nodes.get(0);
  }
  
  public int sum(int i, int j) {
    return sum0(j, root) - sum0(i - 1, root);
  }
  
  public int sum0(int i, Node node) {
    if (i <= 0)
      return 0;
    
    Node current = node;
    while (current.size > i) 
      current = current.left;
    
    if (current.size == i)
      return current.sum;
    
    return current.sum + sum0(i - current.size, current.parent.right);
  }
}
