#!usr/bin/env ruby

require "test/unit"

def select(k, array)
  return nil if k <= 0
  pivot = array[rand(array.count)]
  smaller = array.select { |a| a <= pivot }
  bigger = array.select { |a| a > pivot }
  p "smaller "
  if smaller.count == k
    return smaller.last
  elsif smaller.count > k
    return select k, smaller[0..-2]
  else 
    return select k - smaller.count, bigger
  end
end

class TestSelect < Test::Unit::TestCase
  def test_select
    a = [1]
    assert_equal 1, select(1, a)
    a = [1, 1]
    assert_equal 1, select(1, a)
    a = [1, 3, 5, 6, 1, 10]
    assert_equal 1, select(1, a)
    a = [1, 3, 5, 3, 6, 1, 10]
    assert_equal 3, select(4, a)
  end
end