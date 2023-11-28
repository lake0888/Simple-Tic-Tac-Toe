import java.util.Scanner;

class ArrayOperations {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] twoDimArray = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                twoDimArray[i][j] = scanner.nextInt();
            }
        }

        printCorners(twoDimArray);
    }
    public static void printCorners(int[][] twoDimArray) {
        // write your code here
        int top_left = twoDimArray[0][0];
        int index = twoDimArray[0].length - 1;
        int top_right = twoDimArray[0][index];

        index = twoDimArray.length - 1;
        int bottom_left = twoDimArray[index][0];
        int lastIndex = twoDimArray[index].length - 1;
        int bottom_right = twoDimArray[index][lastIndex];
        String corners = String.format("%d %d%n%d %d", top_left, top_right, bottom_left, bottom_right);
        System.out.print(corners);
    }
}