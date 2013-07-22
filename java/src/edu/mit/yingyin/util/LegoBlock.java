package edu.mit.yingyin.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LegoBlock {
  private static int DIV = 1000000007;
  
  private static int[][] memo, solid;
  private static int[] width, height;
  private static Map<Integer, Integer> heightWidthMap = new HashMap<Integer, Integer>();
   
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    width = new int[T];
    height = new int[T];
    int maxW = 0, maxH = 0;
    
    for (int i = 0; i < T; i++) {
      height[i] = sc.nextInt() - 1; // height
      width[i] = sc.nextInt() - 1; // width
      maxW = Math.max(width[i], maxW);
      maxH = Math.max(height[i], maxH);
      if (!heightWidthMap.containsKey(height[i]))
        heightWidthMap.put(height[i], width[i]);
      else
        heightWidthMap.put(height[i], 
                           Math.max(width[i], heightWidthMap.get(height[i])));
    }
    
    createMemo(maxW, maxH);
    for (int i = 0; i < T; i++) {
      System.out.println(solid[height[i]][width[i]]);
    }
    
  }
  
  public static void createMemo(int maxW, int maxH) {
    memo = new int[maxH + 1][maxW + 1];
    solid = new int[maxH + 1][maxW + 1];
    
    for (int[] a : memo)
      Arrays.fill(a, 0);
    
    memo[0][0] = 1;
    memo[0][1] = 2;
    memo[0][2] = 4;
    memo[0][3] = 8;
    
    for (int i = 4; i <= maxW; i++) {
      memo[0][i] = mod(memo[0][i] + memo[0][i - 1]);
      memo[0][i] = mod(memo[0][i] + memo[0][i - 2]);
      memo[0][i] = mod(memo[0][i] + memo[0][i - 3]);
      memo[0][i] = mod(memo[0][i] + memo[0][i - 4]);
    }
    
    for (int i = 0; i <= maxW; i++) {
      long prod = memo[0][i];
      for (int j = 1; j <= maxH; j++) {
        prod = mod(prod * memo[0][i]);
        memo[j][i] = (int) prod;
      }
    }
    
    for (int h : height) {
      for (int j = 0; j <= heightWidthMap.get(h); j++) {
        if (j == 0 || (h == 0 && j < 4)) {
          solid[h][j] = 1;
        } else if (h == 0 && j >= 4){
          solid[h][j] = 0;
        } else {
          int temp = memo[h][j];
          for (int k = 0; k < j; k++) {
            int temp2 = mod((long) (solid[h][k]) * (long) (memo[h][j - k - 1]));
            if (temp2 > temp)
              temp += DIV - temp2;
            else
              temp -= temp2;
          }
          solid[h][j] = temp;
        }
          
      }
    }
  }
  
  public static int mod(long a) {
    if (a > DIV)
      a = a % DIV;
    return (int) a;
  }
}
