import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        int arr[][] = new int [9][9];
        Scanner in = new Scanner(System.in);
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                arr[i][j] = in.nextInt();
            }
        }
        SudokuSolver sudoku = new SudokuSolver(arr);
        sudoku.solve();
        sudoku.print();
    }
}
