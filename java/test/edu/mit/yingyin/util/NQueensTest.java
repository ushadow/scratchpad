package edu.mit.yingyin.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class NQueensTest {
  
  private static final int[][] Queens5Sol = {{2, 4, 1, 3, 5},
                                             {3, 1, 4, 2, 5},
                                             {1, 3, 5, 2, 4},
                                             {2, 5, 3, 1, 4},
                                             {5, 2, 4, 1, 3}, 
                                             {1, 4, 2, 5, 3},
                                             {5, 3, 1, 4, 2},
                                             {4, 1, 3, 5, 2},
                                             {4, 2, 5, 3, 1},
                                             {3, 5, 2, 4, 1}};

  @Test
  public void testInitialization() {
    NQueens problem = new NQueens(8);
    int[][] board = problem.getBoard();
    for (int[] row : board)
      for (int v : row)
        assertEquals(0, v);
  }
  
  @Test
  public void testSolveWithMarking() {
    NQueens problem = new NQueens(8);
    assertEquals(92, problem.solveWithMarking().size());
    problem = new NQueens(5);
    ArrayList<int[]> sols = problem.solveWithMarking();
    assertEquals(10, sols.size());
    assertTrue(checkSols(sols));
  }
  
  @Test
  public void testSolveWithOutMarking() {
    NQueens problem = new NQueens(8);
    assertEquals(92, problem.solveWithoutMarking().size());
    problem = new NQueens(5);
    ArrayList<int[]> sols = problem.solveWithoutMarking();
    assertEquals(10, sols.size());
    assertTrue(checkSols(sols));
  }
  
  private boolean checkSols(ArrayList<int[]> sols) {
    for (int[] sol : sols) {
      boolean found = false;
      for (int[] expected : Queens5Sol) {
        if (Arrays.equals(sol, expected)) {
          found = true;
          break;
        }
      }
      if (found == false)
        return false;
    }
    return true;  
  }
}
