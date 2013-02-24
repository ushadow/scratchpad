#!usr/bin/env ruby

require 'test/unit'

def min_depth(tree_node)
  return -1 unless tree_node 
  1 + [min_depth(tree_node.left), min_depth(tree_node.right)].min
end

def max_depth(tree_node)
  return -1 unless tree_node
  1 + [max_depth(tree_node.left), max_depth(tree_node.right)].max
end
  
def balanced?(tree_node)
  return true, -1, -1 unless tree_node
  balanced, min_left, max_left = balanced?(tree_node.left)
  balanced, min_right, max_right = balanced?(tree_node.right)
  min = [min_left, min_right].min + 1
  max = [max_left, max_right].max + 1
  balanced = (max - min) <= 1
  return balanced, min, max
end

class TreeNode
  attr_accessor :left, :right
end

class TestBalancedTree < Test::Unit::TestCase
  def single_node_tree()
    TreeNode.new
  end

  def balanced_tree()
    root = TreeNode.new
    root.left = TreeNode.new
    root.right = TreeNode.new
    root
  end
  
  def balanced_tree2()
    root = TreeNode.new
    root.left = TreeNode.new
    root
  end

  def unbalanced_tree()
    root = TreeNode.new
    root.left = TreeNode.new
    root.left.left = TreeNode.new
    root
  end

  def test_balanced
    root = single_node_tree
    balanced, = balanced?(root)
    assert balanced
    
    root = balanced_tree
    balanced, = balanced?(root)
    assert balanced
    
    root = balanced_tree2
    balanced, = balanced?(root)
    assert balanced
   
    root = unbalanced_tree
    balanced, = balanced?(root)
    assert !balanced 
  end
end
    

