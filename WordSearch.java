import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;
    //the random seed used to produce this WordSearch
    private int seed;
    //a random Object to unify your random calls
    private Random randgen;
    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;
    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;


    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */


    public WordSearch( int rows, int cols, String fileName) {
      data = new char[rows][cols];
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      clear();
      readFile(fileName);
    }
    public WordSearch( int rows, int cols, String fileName, int randSeed) {
      data = new char[rows][cols];
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      clear();
      readFile(fileName);
    }

    private void readFile(String fileName) {
      File filein = new File(fileName);
      try {
       Scanner scn = new Scanner(filein);
       while(scn.hasNext()){
        String line = scn.nextLine();
        wordsToAdd.add(line);
      }
       scn.close();
     }
     catch (FileNotFoundException e) {
       System.out.println("not a valid file");
     }
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
        out += "|";
        for (int y = 0; y < data[0].length; y++) {
          out += data[x][y];
          if (y < data[0].length - 1) out += " ";
        }
        out += "|\n";
      }
      out += "Words: ";
      for (int i = 0; i < wordsAdded.size(); i++){
        out += wordsAdded.get(i);
      }
    return out;
    }


    public boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement){
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
