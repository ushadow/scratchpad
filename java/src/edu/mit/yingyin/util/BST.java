package edu.mit.yingyin.util;

public class BST {

  private static class Node {
    public int value, size;
    public Node left, right, parent;

    public Node(int value) {
      this.value = value;
      size = 1; // Number of nodes in the subtree rooted at this node.
    }

    public Node(int value, Node parent) {
      this(value);
      this.parent = parent;
    }

    /**
     * If less is true, returns the number of nodes smaller than the value in
     * the subtree rooted at this node.
     * 
     * @param value
     * @param less
     * @return
     */
    public int insert(int value, boolean less) {
      if (this.value == value) {
        return rank(less);
      }
      if (this.value < value) {
        int rank = 0;
        if (right == null) {
          right = new Node(value, this);
          updateSize(right);
        } else {
          rank = right.insert(value, less);
        }
        if (less)
          return rank(less) + 1 + rank;
        else
          return rank;
      }
      int rank = 0;
      if (left == null) {
        left = new Node(value, this);
        updateSize(left);
      } else {
        rank = left.insert(value, less);
      }
      if (less)
        return rank;
      else
        return rank(less) + 1 + rank;
    }

    // Rank of this node in the subtree rooted at this node.
    private int rank(boolean less) {
      if (less && left != null)
        return left.size;
      else if (!less && right != null)
        return right.size;
      else
        return 0;
    }

    private void updateSize(Node n) {
      while (n.parent != null) {
        n.parent.size += 1;
        n = n.parent;
      }
    }

    public void checkRI() {
      int expectedSize = 1;
      if (left != null) {
        left.checkRI();
        if (left.value >= value)
          throw new RuntimeException("Left value is not less than this value.");
        expectedSize += left.size;
      }
      if (right != null) {
        right.checkRI();
        if (right.value <= value)
          throw new RuntimeException(
              "Right value is not greater than this value.");
        expectedSize += right.size;
      }
      if (size != expectedSize)
        throw new RuntimeException("The size of the subtree is not correct.");
    }
  }

  private Node root;

  // If less is true, returns the number of nodes with value smaller than the
  // inserted value;
  // otherwise, returns the number of nodes with value greater than the inserted
  // value.
  public int insert(int value, boolean less) {
    if (root == null) {
      root = new Node(value);
      return root.size - 1;
    }
    return root.insert(value, less);
  }

  public void checkRI() {
    if (root != null)
      root.checkRI();
  }
}
