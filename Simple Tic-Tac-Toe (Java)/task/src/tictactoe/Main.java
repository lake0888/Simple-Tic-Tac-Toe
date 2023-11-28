package tictactoe;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        char[][] matrix = new char[3][3];
        initTicTacToe(matrix);
        printStage(matrix);
        fight(matrix);
    }

    public static void initTicTacToe(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = ' ';
            }
        }
    }

    public static void printStage(char[][] matrix) {
        printDashes();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");

            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println("|");
        }
        printDashes();
    }

    public static void printDashes() {
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void fight(char[][] matrix) {
        while (!isFinished(matrix)) {
            //Move X
            move(matrix, 'X');
            if (isFinished(matrix)) {
                printResult(matrix);
                break;
            }
            //Move 0
            move(matrix, 'O');
            if (isFinished(matrix)) printResult(matrix);
        }
    }

    public static boolean isFinished(char[][] matrix) {
        boolean[] finished = currentState(matrix);
        boolean xThreeInRow = finished[0], oThreeInRow = finished[1], hasEmptyCells = finished[2], difference = finished[3];
        boolean isThreeInRow = xThreeInRow | oThreeInRow;
        return isThreeInRow || !hasEmptyCells || difference;
    }

    public static boolean[] currentState(char[][] matrix) {
        int[] contSymbols = contSymbolInMatrix(matrix);
        boolean hasEmptyCells = contSymbols[0] != 0;

        int i = 0;
        boolean xThreeInRow = false, oThreeInRow = false;
        while (i < matrix.length) {
            if (!xThreeInRow)
                xThreeInRow = hasThreeInRow(matrix, i, 'X');
            if (!oThreeInRow)
                oThreeInRow = hasThreeInRow(matrix, i, 'O');
            i++;
        }
        boolean difference = Math.abs(contSymbols[1] - contSymbols[2]) > 1;
        return new boolean[]{ xThreeInRow, oThreeInRow, hasEmptyCells, difference};
    }

    public static int[] contSymbolInMatrix(char[][] matrix) {
        int contSpaces = 0, contX = 0, contO = 0;
        for (char[] chars : matrix) {
            for (char current : chars) {
                if (current == 'X') contX++;
                else if (current == 'O') contO++;
                else contSpaces++;
            }
        }
        return new int[]{contSpaces, contX, contO};
    }


    public static boolean hasThreeInRow(char[][] matrix, int index, char symbol) {
        int contInRow = 0, contInColumn = 0, contDFLeft = 0, contDFRight = 0, j = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[index][i] == symbol) contInRow++;
            if (matrix[i][index] == symbol) contInColumn++;
            if (matrix[i][i] == symbol) contDFLeft++;
            if (matrix[i][j--] == symbol) contDFRight++;
        }

        return contInRow == 3 || contInColumn == 3 || contDFLeft == 3 || contDFRight == 3;
    }

    public static void printResult(char[][] matrix) {
        boolean[] finished = currentState(matrix);
        boolean xThreeInRow = finished[0], oThreeInRow = finished[1], hasEmptyCells = finished[2], difference = finished[3];
        boolean isThreeInRow = xThreeInRow | oThreeInRow;
        if (!isThreeInRow && !hasEmptyCells) System.out.print("Draw");
        else if ((xThreeInRow && oThreeInRow) || difference) System.out.print("Impossible");
        else if (xThreeInRow) System.out.print("X wins");
        else System.out.print("O wins");
    }




    //Stage 4/5
    public static void move(char[][] matrix, char symbol) {
        boolean flag = true;
        while (flag) {
            try {
                int x = Integer.parseInt(scanner.next());
                int y = Integer.parseInt(scanner.next());

                if (x < 1 || x > 3 || y < 1 || y > 3)
                    System.out.println("Coordinates should be from 1 to 3!");
                else {
                    if (matrix[x - 1][y - 1] == ' ') {
                        matrix[x - 1][y - 1] = symbol;
                        flag = false;
                        printStage(matrix);
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
                continue;
            }
        }
    }
}
