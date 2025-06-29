import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GridSolver {
    public static void main(String[] args){
        try{
            int[][]grid = readGrid("grid.txt");
            System.out.println("Grid dimensions: " + grid.length + "x" + grid[0].length);
            System.out.println("First element: " + grid[0][0]);
            System.out.println("Last element: " + grid[19][19]);
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    private static int[][] readGrid(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] grid = new int[20][20];

        for (int row = 0; row < 20; row++) {
            for (int column = 0; column < 20; column++) {
                grid[row][column] = scanner.nextInt();
            }
        }
        scanner.close();
        return grid;
    }
}
