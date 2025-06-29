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

            int[][] testgrid = {
                {1, 2, 3, 4}, //24
                {5, 6, 7, 8}, //1680
                {9, 1, 2, 3}, //54
                {4, 5, 6, 7} //840
            };

             //Test horisontal produkter med test grid
            long maxHorizontal = findMaxHorizontal(testgrid);
            System.out.println("Largest horizontal product: " + maxHorizontal);

            //Test horisontal produkter
            long maxHorizontal2 = findMaxHorizontal(grid);
            System.out.println("Largest horizontal product: " + maxHorizontal2);

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

    private static long findMaxHorizontal(int[][] grid){
        long maxProduct = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column + 3 < grid[row].length; column++) { // siste posisjon må være innenfor gridet
                long product = (long) grid[row][column] * grid[row][column+1] * grid[row][column+2] * grid[row][column+3];
                if (product > maxProduct) {
                    maxProduct = product;
                }
            }
        }

        return maxProduct;

    }
}
