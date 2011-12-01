#!usr/bin/env ruby

require "test/unit"

# Selects the kth smallest item in an array counting from 0; that is, the 
# smallest item for which k of the items in the set are smaller.
#
# @param [Fixnum] k 
# @param [Array] array the array from which we want to find the kth smallest 
#     item.
def select(k, array)
  return nil if k < 0 || k >= array.count
  pivot = array[rand array.count]
  smaller = array.select { |a| a < pivot } # O(n)
  bigger = array.select { |a| a >= pivot } # O(n)
  if smaller.count == k
    return bigger.first
  elsif smaller.count > k
    return select k, smaller
  else 
    bigger.delete_at bigger.index(pivot)
    return select k - smaller.count - 1, bigger 
  end
end

class TestSelect < Test::Unit::TestCase
  def test_select
    a = [1]
    assert_equal 1, select(0, a)
    a = [1, 1]
    assert_equal 1, select(1, a)
    a = [1, 3, 5, 6, 1, 10]
    assert_equal 3, select(1, a)
    a = [1, 3, 5, 3, 6, 1, 10]
    assert_equal 5, select(4, a)
  end
end