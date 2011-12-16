package edu.mit.yingyin.util;
import static org.junit.Assert.*;

import org.junit.Test;


public class RangeSumTest {

  @Test
  public void testSimple() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {1, 2, 3, 4, 5});
    assertEquals(6, rs.sum(0, 2));
    assertEquals(1, rs.sum(0, 0));
    assertEquals(5, rs.sum(4, 4));
    assertEquals(15, rs.sum(0, 4));
    assertEquals(7, rs.sum(2, 3));
    assertEquals(12, rs.sum(2, 4));
  }

  @Test
  public void testOne() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {0});
    assertEquals(0, rs.sum(0, 0));
  }
  
  @Test
  public void testPowerOfTwoElements() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {1, 1, 3, 1});
    assertEquals(5, rs.sum(0, 2));
    assertEquals(1, rs.sum(0, 0));
    assertEquals(1, rs.sum(3, 3));
    assertEquals(6, rs.sum(0, 3));
    assertEquals(4, rs.sum(2, 3));
    assertEquals(5, rs.sum(1, 3));
  }
  
  @Test
  public void testUpdate() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {1, 2, 3, 4, 5});
    rs.update(0, 6);
    assertEquals(6, rs.sum(0, 0));
    assertEquals(20, rs.sum(0, 4));
    assertEquals(7, rs.sum(2, 3));
  }
  
  @Test 
  public void tesetUpdateOneElement() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {0});
    rs.update(0, 6);
    assertEquals(6, rs.sum(0, 0));
  }
}
