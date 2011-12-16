package edu.mit.yingyin.util;
import static org.junit.Assert.*;

import org.junit.Test;


public class RangeSumTest {

  @Test
  public void test() {
    RangeSum rs = new RangeSum();
    rs.setup(new int[] {1, 2, 3, 4, 5});
    assertEquals(6, rs.sum(0, 2));
  }

}
