package edu.mit.yingyin.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomTest {

  @Test
  public void test() {
    Random rand = new Random();
    rand.setSeed(-314159L);
    assertEquals(119318998, rand.next());
    for (int i = 0; i < 133; i++){
      rand.next();
    }
    assertEquals(748103812, rand.next(0x55555555L));
  }

}
