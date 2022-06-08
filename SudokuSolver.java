import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SudokuSolver {
  
  private static final int GRID_SIZE = 9;
  private static final int BOX_SIZE = 3;
  
  public static void main(String[] args) {
    
    //Hardcoded board if user doesn't input
    int[][] board = {
          {6, 0, 9, 0, 4, 0, 0, 0, 1},
          {7, 1, 0, 5, 0, 9, 6, 0, 0},
          {0, 5, 0, 0, 0, 0, 0, 0, 0},
          {2, 0, 7, 0, 8, 0, 0, 9, 0},
          {0, 0, 0, 0, 6, 0, 0, 2, 4},
          {0, 6, 0, 9, 0, 0, 0, 0, 8},
          {0, 0, 8, 0, 0, 0, 3, 0, 0},
          {0, 0, 0, 4, 0, 0, 0, 0, 7},
          {0, 0, 0, 0, 5, 0, 0, 0, 0} 
        };
    Scanner in = new Scanner(System.in);

    System.out.println("Do you want to input a sudoku board? (y/n)");
    if(Character.toLowerCase(in.nextLine().charAt(0)) == 'y')
      fillBoard(board, in);
     
    System.out.println("\nHere is your board");
    printBoard(board);
    
    if (solveBoard(board)) {
      System.out.println("\nSudoku solved");
    }
    else {
      System.out.println("\nUnable to solve");
      return;
    }


    printBoard(board);
    
  }
  
  
  private static void printBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      if (row % BOX_SIZE == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRID_SIZE; column++) {
        if (column % BOX_SIZE == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }


  private static boolean isInRow(int[][] board, int number, int row) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[row][i] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isInColumn(int[][] board, int number, int column) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[i][column] == number) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isInBox(int[][] board, int number, int row, int column) {
    int localBoxRow = row - row % BOX_SIZE;
    int localBoxColumn = column - column % BOX_SIZE;
    
    for (int i = localBoxRow; i < localBoxRow + BOX_SIZE; i++) {
      for (int j = localBoxColumn; j < localBoxColumn + BOX_SIZE; j++) {
        if (board[i][j] == number) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
    return !isInRow(board, number, row) &&
        !isInColumn(board, number, column) &&
        !isInBox(board, number, row, column);
  }
  
  private static boolean solveBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      for (int column = 0; column < GRID_SIZE; column++) {
        if (board[row][column] == 0) {
          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
            if (isValidPlacement(board, numberToTry, row, column)) {
              board[row][column] = numberToTry;
              
              if (solveBoard(board)) {
                return true;
              }
              else {
                board[row][column] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  public static void fillBoard(int[][] board, Scanner in){  
    String hold;

    for(int row = 0; row < GRID_SIZE; row++){ 
      System.out.printf("Enter sudoku row %d\n", row + 1);
      hold = in.nextLine().replaceAll("\\s+", "");
      System.out.printf("hold is %s\n", hold);
      for(int col = 0 ; col < GRID_SIZE; col++){  
        board[row][col] = Character.getNumericValue(hold.charAt(col));
      } 
    } 
  }

}