import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
import java.lang.Math;
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


    private WordSearch( int rows, int cols, String fileName, int randSeed, boolean ans) {
      data = new char[rows][cols];
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      seed = randSeed;
      randgen = new Random(seed);
      clear();
      readFile(fileName);
      addAllWords();
    }

    private void readFile(String fileName) {
      File filein = new File(fileName);
      try {
       Scanner scn = new Scanner(filein);
       while(scn.hasNext()){
        String line = scn.next();
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


    private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement){
      if (row + rowIncrement * word.length() > data.length || col + colIncrement * word.length() > data[row].length) return false;
      if (row + rowIncrement * word.length() < 0 || col + colIncrement * word.length() < 0) return false;
      if (rowIncrement == 0 && colIncrement == 0) return false;
     for (int i = 0; i < word.length(); i++) {
       if (data[row + rowIncrement * i][col + colIncrement * i] != word.charAt(i) && data[row + rowIncrement * i][col + colIncrement * i] != '_') return false;
      }
      for (int i = 0; i < word.length(); i++) {
        data[row + rowIncrement * i][col + colIncrement * i] = word.charAt(i);
       }
       wordsToAdd.remove(word);
       wordsAdded.add(word);
      return true;
    }
    /*[rowIncrement,colIncrement] examples:

     *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)

     *[ 1,0] would add downwards because (row+1), with no col change

     *[ 0,-1] would add towards the left because (col - 1), with no row change

     */

    public static void main(String[]args){
      if (args.length < 3) {
        System.out.println("Syntax: row col filename");
      }
      if (args.length == 3) {
        int randSeed = (int)Math.random()*10000;
        new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], randSeed, true);
      }
      if (args.length == 4) {
        new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
      }
    }

    private void addAllWords(){
      int i = 10000;
      while (i > 0 && wordsToAdd.size() > 0){
        String targetWord = wordsToAdd.get(Math.abs(randgen.nextInt()%wordsToAdd.size()));
        int rowInc = randgen.nextInt()%2;
        int colInc = randgen.nextInt()%2;
        int row = Math.abs(randgen.nextInt()%data.length);
        int col = Math.abs(randgen.nextInt()%data[row].length);
        System.out.println("" + targetWord);
        if (!addWord(targetWord, row, col, rowInc, colInc)) i--;
        else i = 10000;
      }
      System.out.println(this);
    }

    private void fillBlanks(){

    }


}
