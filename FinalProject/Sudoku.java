package FinalProject;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sudoku {
    public byte[][] board;
    public byte[][] indLookup = new byte[80][3];
    public boolean[][] used_nums_row;
    public boolean[][] used_nums_col;
    public boolean[][] used_nums_cell;

    public int setBoard(byte[][] c) {
        board = c;
        used_nums_row = new boolean[10][9];
        used_nums_col = new boolean[10][9];
        used_nums_cell = new boolean[10][9];
        initialSet();
        if (solve((byte) 0)) {
            System.out.println(toString());
            return ((board[0][0] << 6) + (board[0][0] << 5) + (board[0][0] << 2))
                    + ((board[0][1] << 3) + (board[0][1] << 1)) + board[0][2];
        }
        return 0;
    }

    public void setBoardPosition(int row, int col, int cell, int i, boolean val) {
        used_nums_row[i][row] = val;
        used_nums_col[i][col] = val;
        used_nums_cell[i][cell] = val;
    }

    public void initialSet() {
        int ind = 0;
        while (ind < 81) {
            int row = ind / 9;
            int col = ind % 9;
            int cell = row <= 2 ? (col <= 2 ? 0 : col <= 5 ? 1 : 2)
                    : row <= 5 ? (col <= 2 ? 3 : col <= 5 ? 4 : 5) : (col <= 2 ? 6 : col <= 5 ? 7 : 8);
            if (board[row][col] != (byte) 0) {
                used_nums_row[board[row][col]][row] = true;
                used_nums_col[board[row][col]][col] = true;
                used_nums_cell[board[row][col]][cell] = true;
            }
            //indLookup[ind] = new byte[]{(byte)row, (byte)col, (byte)cell}
            ind++;
        }
    }

    public boolean solve(byte ind) {
        while (ind < 81) {
            if (board[indLookup[ind][0]][indLookup[ind][1]] != (byte) 0) {
                ind++;
                continue;
            }
            for (byte i = 1; i < 10; i++) {
                if (!used_nums_row[i][indLookup[ind][0]] && !used_nums_col[i][indLookup[ind][1]] && !used_nums_cell[i][indLookup[ind][2]]) {
                    board[indLookup[ind][0]][indLookup[ind][1]] = i;
                    used_nums_row[i][indLookup[ind][0]] = true;
                    used_nums_col[i][indLookup[ind][1]] = true;
                    used_nums_cell[i][indLookup[ind][2]] = true;
                    if (solve((byte) (ind + 1))) {
                        return true;
                    }
                    used_nums_row[i][indLookup[ind][0]] = false;
                    used_nums_col[i][indLookup[ind][1]] = false;
                    used_nums_cell[i][indLookup[ind][2]] = false;
                }
            }
            board[indLookup[ind][0]][indLookup[ind][1]] = (byte) 0;
            return false;
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

        //File file = new File("./FinalProject/sudoku_tests.txt");
        File file = new File("./FinalProject/Demo/hardest.txt");


        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            byte[][] arr = new byte[9][9];
            bf.readLine(); // Get rid of first line
            String lineS = bf.readLine();
            byte[] line;

            Sudoku sud = new Sudoku();
            int sum = 0;

            int counter = 0;
            while (lineS != null) {
                line = lineS.getBytes();
                if (counter == 9) {
                    counter = -1;
                    //System.out.println(new String(line));
                    sum += sud.setBoard(arr);
                } else {
                    for (int i = 0; i < 9; i++) {
                        arr[counter][i] = (byte) (line[i] - 48);
                        // System.out.println(arr[counter][i]);
                    }
                }
                lineS = bf.readLine();
                counter++;
            }
            sum += sud.setBoard(arr);
            System.out.println(sum);
            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}