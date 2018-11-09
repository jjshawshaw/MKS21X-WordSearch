public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols){
      data = new char[rows][cols];
      clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[0].length; y++) {
          data[x][y] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String out = "";
      for (int x = 0; x < data.length; x++) {
        for (int y = 0; y < data[0].length; y++) {
          out += data[x][y] + " ";
        }
        out += "\n";
      }
    return out;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      if (data[row].length < col + word.length()) return false;
      for (int i = 0; i < word.length(); i++) {
        if (data[row][i] != word.charAt(i) && data[row][col + i] != '_') return false;
      }
      for (int i = 0; i < word.length(); i++) {
        data[row][col +i] = word.charAt(i);
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      if (data.length < row + word.length()) return false;
      for (int i = 0; i < word.length(); i++) {
        if (data[row + i][col] != word.charAt(i) && data[row + i][col] != '_') return false;
      }
      for (int i = 0; i < word.length(); i++) {
        data[row + i][col] = word.charAt(i);
      }
      return true;
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from top left to bottom right, must fit on the WordGrid,
  *and must have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  *or there are overlapping letters that do not match, then false is returned.
  */
  public boolean addWordDiagonal(String word,int row, int col){
    if (row + word.length() > data.length || col + word.length() > data[row].length) return false;
   for (int i = 0; i < word.length(); i++) {
     if (data[row + i][col + i] != word.charAt(i) && data[row + i][col + i] != '_') return false;
    }
    for (int i = 0; i < word.length(); i++) {
      data[row + i][col + i] = word.charAt(i);
     }
    return true;
  }


   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added in the direction rowIncrement,colIncrement

     *Words must have a corresponding letter to match any letters that it overlaps.

     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.

     *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
     *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction

     *@return true when: the word is added successfully.
     *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
     *        OR there are overlapping letters that do not match
     */
    public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
      if (row + rowIncrement * word.length() > data.length || col + colIncrement * word.length() > data[row].length) return false;
     for (int i = 0; i < word.length(); i++) {
       if (data[row + rowIncrement * i][col + colIncrement * i] != word.charAt(i) && data[row + rowIncrement * i][col + colIncrement * i] != '_') return false;
      }
      for (int i = 0; i < word.length(); i++) {
        data[row + rowIncrement * i][col + colIncrement * i] = word.charAt(i);
       }
      return true;
    }

    /*[rowIncrement,colIncrement] examples:

     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)

     *[ 1,0] would add downwards because (row+1), with no col change

     *[ 0,-1] would add towards the left because (col - 1), with no row change

     */


}
