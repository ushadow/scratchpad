package edu.mit.yingyin.util;

public class SimpleRegEx {
  
  /**
   * Searches for re anywhere in text.
   * @param re
   * @param text
   * @return
   */
  public static boolean match(String re, String text) {
    if (!re.isEmpty() && re.charAt(0) == '^')
      return matchHere(re, 1, text, 0);
    int i = 0;
    do {
      if (matchHere(re, 0, text, i))
        return true;
    } while (i++ < text.length());
    return false;
  }
  
  /**
   * Searches for re from the start index in text.
   * @param re
   * @param reStart
   * @param text
   * @param textStart
   * @return
   */
  private static boolean matchHere(String re, int reStart, String text, 
                                  int textStart) {
    if (reStart >= re.length())
      return true;
    if (reStart + 1 < re.length() && re.charAt(reStart + 1) == '*')
      return matchStar(re.charAt(reStart), re, reStart + 2, text, textStart);
    if (reStart == re.length() - 1 && re.charAt(reStart) == '$')
      return textStart >= text.length();
    if (textStart < text.length() && 
        (re.charAt(reStart) == '.' || 
         re.charAt(reStart) == text.charAt(textStart)))
      return matchHere(re, reStart + 1, text, textStart + 1);
    return false;
  }
  
  /**
   * Searches for c*re[reStart : ] at textStart index of text.
   * @param c
   * @param re
   * @param reStart
   * @param text
   * @param textStart
   * @return
   */
  private static boolean matchStar(char c, String re, int reStart, String text,
                                   int textStart) {
    int i = textStart;
    do {
      if (matchHere(re, reStart, text, i))
        return true;
    } while (i < text.length() && (text.charAt(i++) == c || c == '.'));
    return false;
  }

}
