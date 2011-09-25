package edu.mit.yingyin.util;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueens {

  private int size;
  private int[][] board;
  /**
   * 4 diagonal directions.
   */
  private int[] dr = {-1, -1, 1, 1};
  private int[] dc = {-1, 1, 1, -1};
  private ArrayList<int[]> solutions = new ArrayList<int[]>();
  
  public NQueens(int size) {
    this.size = size;
    board = new int[size][size];
    for (int i = 0; i < size; i++)
      Arrays.fill(board[i], 0);
  }
  
  /**
   * An implementation that marks -1 on the board for positions that are not 
   * possible after placing a queen. This is more efficient because subsequent
   * recursive calls only need to check the mark at a particular position 
   * without iteration. It requires more memory however because it needs to 
   * create a new copy of the board at each recursion level so that we can 
   * easily undo the marking.
   * 
   * @return all the solutions.
   */
  public ArrayList<int[]> solveWithMarking() {
    solutions.clear();
    solve1WithMarking(0, board);
    return solutions;
  }

  /**
   * An implementation that does not mark out invalid positions. Subsequence 
   * placement of a queen needs to check conflicts iteratively.
   * 
   * @return all the solutions.
   */
  public ArrayList<int[]> solveWithoutMarking() {
    solutions.clear();
    solve1WithoutMarking(0);
    return solutions;
  }
  
  
  public void printBoard(int[][] tmpBoard) {
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        System.out.print(String.format("%+d ", tmpBoard[r][c]));
      }
      System.out.println();
    }
    System.out.println();
  }

  public int[][] getBoard() {
    return board;
  }

  /**
   * Solves one particular row with marking.
   * @param row
   * @param tmpBoard
   */
  private void solve1WithMarking(int row, int[][] tmpBoard) {
    if (row == size) {
      addSolutions(tmpBoard);
      return;
    }
    
    for (int c = 0; c < size; c++) {
      if (tmpBoard[row][c] == 0) {
        int[][] newBoard = copyBoard(tmpBoard);
        markBoard(row, c, newBoard);
        solve1WithMarking(row + 1, newBoard);
      }
    }
  }
  
  /**
   * Creates a deep copy of the current temporary board.
   * @param tmpBoard
   * @return
   */
  private int[][] copyBoard(int[][] tmpBoard) {
    int[][] newBoard = new int[size][size];
    for (int i = 0; i < size; i++)
      System.arraycopy(tmpBoard[i], 0, newBoard[i], 0, size);
    return newBoard;
  }
  
  
  /**
   * Marks a copy of the board with -1 at positions that are in conflict with 
   * position (row, col). 
   * 
   * @param row
   * @param col
   * @param tmpBoard
   */
  private void markBoard(int row, int col, int[][] tmpBoard) {
    for (int i = 0; i < size; i++) {
      tmpBoard[row][i] = -1;
      tmpBoard[i][col] = -1;
    }
    for (int i = 0; i < 4; i++) {
      int nrow = row;
      int ncol = col;
      while (true) {
        nrow += dr[i];
        ncol +=  dc[i];
        if (nrow >= 0 && nrow < size && ncol >= 0 && ncol < size) 
          tmpBoard[nrow][ncol] = -1;
        else break;
      }
    }
    tmpBoard[row][col] = 1;
  }
  
  private void solve1WithoutMarking(int row) {
    if (row == size) {
      addSolutions(board);
      return;
    }
    
    for (int c = 0; c < size; c++) {
      if (board[row][c] == 0 && isRowColSafe(row, c, board) && 
          isDiagSafe(row, c, board)) {
        board[row][c] = 1;
        solve1WithoutMarking(row + 1);
        board[row][c] = 0;
      }
    }
  }
  
  private boolean isRowColSafe(int row, int col, int[][] b) {
    for (int i = 0; i < size; i++) {
      if (b[row][i] == 1 && i != col || b[i][col] == 1 && i != row)
        return false;
    }
    return true;
  }
  
  private boolean isDiagSafe(int row, int col, int[][] b) {
    for (int i = 0; i < 4; i++) {
      int nrow = row;
      int ncol = col;
      while (true) {
        nrow += dr[i];
        ncol +=  dc[i];
        if (nrow >= 0 && nrow < size && ncol >= 0 && ncol < size) {
          if (b[nrow][ncol] == 1)
            return false;
        } else break;
      }
    }
    return true;
  }
  
  private void addSolutions(int[][] b) {
    int[] sol = new int[size];
    for (int r = 0; r < size; r++ ) {
      for (int c = 0; c < size; c++) {
        if (b[r][c] == 1) {
          sol[r] = c + 1;
          break;
        }
      }
    }
    solutions.add(sol);
  }
}
