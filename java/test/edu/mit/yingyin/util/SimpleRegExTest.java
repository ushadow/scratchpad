package edu.mit.yingyin.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class SimpleRegExTest {
  
  @Test
  public void testRegEx() {
    assertTrue(SimpleRegEx.match("a", "a"));
    assertFalse(SimpleRegEx.match("a", "b"));
    assertTrue(SimpleRegEx.match("a", "ab"));
    assertTrue(SimpleRegEx.match("a", "ba"));
    assertFalse(SimpleRegEx.match("ab", "ba"));
    assertTrue(SimpleRegEx.match("ab", "xab"));
    assertTrue(SimpleRegEx.match("ab", "aab"));
    assertFalse(SimpleRegEx.match("a.c", "ac"));
    assertFalse(SimpleRegEx.match("a.c", "xac"));
    assertTrue(SimpleRegEx.match("a.c", "xabcx"));
    assertTrue(SimpleRegEx.match("^ab", "ab"));
    assertFalse(SimpleRegEx.match("^ab", "ba"));
    assertFalse(SimpleRegEx.match("^ab", "aab"));
    assertTrue(SimpleRegEx.match("^ab", "abc"));
    assertTrue(SimpleRegEx.match("ab$", "ab"));
    assertFalse(SimpleRegEx.match("ab$", "ba"));
    assertTrue(SimpleRegEx.match("ab$", "aab"));
    assertFalse(SimpleRegEx.match("ab$", "bac"));
    assertTrue(SimpleRegEx.match("^ab$", "ab"));
    assertFalse(SimpleRegEx.match("^ab$", "ba"));
    assertFalse(SimpleRegEx.match("^ab$", "abc"));
    assertFalse(SimpleRegEx.match("^ab$", "aba"));
    assertTrue(SimpleRegEx.match("a.*c", "ac"));
    assertTrue(SimpleRegEx.match("a.*c", "abc"));
    assertTrue(SimpleRegEx.match("a.*c", "abbc"));
    assertFalse(SimpleRegEx.match("a.*c", "cbba"));
    assertFalse(SimpleRegEx.match("aa*", "x"));
    assertTrue(SimpleRegEx.match("aa*", "a"));
    assertTrue(SimpleRegEx.match("aa*", "aa"));
    assertTrue(SimpleRegEx.match("a*a*a*", "a"));
    assertTrue(SimpleRegEx.match("a*a*a", "aaa"));
    assertFalse(SimpleRegEx.match("a*a*a", "xxxxx"));
    
  }

}
