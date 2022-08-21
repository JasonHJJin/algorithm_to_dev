package FinalProject.Jason;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Sudoku {
    public byte[][] board;
    
    public int setBoard(byte[][] c) {
        board = c;
        if (solve((byte)0)) {
            //System.out.println(toString());
            return getAnswer();
        }
        return 0;
    }

    public int getAnswer() {
        return Integer.parseInt("" + board[0][0] + board[0][1] + board[0][2]);
    }

    public boolean solve(byte ind) {
        while (ind < 81) {
            int row = ind / 9;
            int col = ind % 9;
            if (board[row][col] != 0) {
                ind++;
                continue;
            }
            for (byte n = 1; n <= 9; n++) {
                if (safe_num(n, row, col)) {
                    board[row][col] = n;
                    if (solve((byte)(ind + 1))) {
                        return true;
                    }
                }
            }
            board[row][col] = (byte) 0;
            return false;
        }
        return true;
    }

    public boolean safe_num(byte ch, int row, int col) {
        // Check if any values in the row or column are equal
        int r = (row / 3) * 3;
        int c = (col / 3) * 3;
        for (int i = 0; i < board.length; i++) {
            // Check if same row
            if (board[row][i] == ch) {
                return false;
            }
            // Check if same column
            if (board[i][col] == ch) {
                return false;
            }
            // Check if same cell
            if (board[r + (i / 3)][c+(i%3)] == ch) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        // Create the top line
        String mid_cell_double = "\u2550\u2550\u2550";
        String inner_top_cell = mid_cell_double + "\u2564" + mid_cell_double + "\u2564" + mid_cell_double;
        String top_line = "\u2554" + inner_top_cell + "\u2566" + inner_top_cell + "\u2566" + inner_top_cell + "\u2557"
                + "\n";

        // Create the middle line for non cell lines
        String mid_cell_single = "\u2500\u2500\u2500";
        String inner_mid_cell = mid_cell_single + "\u253C" + mid_cell_single + "\u253C" + mid_cell_single;
        String mid_line_non_third = "\u255F" + inner_mid_cell + "\u256B" + inner_mid_cell + "\u256B" + inner_mid_cell
                + "\u2562" + "\n";

        // Create the middle line for cell lines
        String inner_mid_cell_third = mid_cell_double + "\u256A" + mid_cell_double + "\u256A" + mid_cell_double;
        String mid_line_third = "\u2560" + inner_mid_cell_third + "\u256C" + inner_mid_cell_third + "\u256C"
                + inner_mid_cell_third + "\u2563" + "\n";

        String finalString = top_line;
        for (int i = 0; i < board.length; i++) {
            // Add a middle line after each line
            if (i % 3 == 0 && i != 0) {
                finalString += "\u2551\n" + mid_line_third;
            } else if (i != 0) {
                finalString += "\u2551\n" + mid_line_non_third;
            }

            for (int j = 0; j < board[0].length; j++) {
                if (j % 3 == 0) {
                    finalString += "\u2551 " + board[i][j] + " ";
                } else {
                    finalString += "\u2502 " + board[i][j] + " ";
                }
            }
        }
        // Add the final line
        String mid_bottom_cell = mid_cell_double + "\u2567" + mid_cell_double + "\u2567" + mid_cell_double;
        finalString += "\u2551\n\u255A" + mid_bottom_cell + "\u2569" + mid_bottom_cell + "\u2569" + mid_bottom_cell
                + "\u255D";
        return finalString;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
        File file = new File("./FinalProject/sudoku_tests.txt");

        try(Scanner sc = new Scanner(new FileReader(file))) {
            byte[][] arr = new byte[9][9];
            //sc.nextLine(); // Get rid of first line
            String lineS = sc.nextLine();
            lineS = sc.nextLine();
            byte[] line;
            Sudoku sud = new Sudoku();
            int sum = 0;

            int counter = 0;
            while (sc.hasNextLine()) {
                line = lineS.getBytes();
                if (counter == 9) {
                    counter=-1;
                    //System.out.println(new String(line));
                    sum += sud.setBoard(arr);
                }
                else {
                    for (int i = 0; i < 9; i++) {
                        arr[counter][i] = (byte) (line[i] - 48);
                    }
                }
                lineS = sc.nextLine();
                counter++;
            }
            line = lineS.getBytes();
            for (int i = 0; i < 9; i++) {
                arr[counter][i] = (byte)(line[i] - 48);
            }
            sum += sud.setBoard(arr);
            System.out.println(sum);
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}