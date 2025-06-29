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
                {1, 2, 3, 4}, //h: 24
                {5, 6, 7, 8}, //h: 1680
                {9, 1, 2, 3}, //h: 54
                {4, 5, 6, 7} //h: 840
            }; //v1: 180, v2:60, v3:252, v4: 672 
            //ddr: 84, ddl: 112

            long maxProductGrid = findLargestProduct(testgrid);
            System.out.println("Largest product in grid: " + maxProductGrid);

            long maxProductGrid2 = findLargestProduct(grid);
            System.out.println("Largest product in grid: " + maxProductGrid2);

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

    private static long findLargestProduct(int[][]grid){
        long maxProduct = 0;

        for(int row = 0; row < grid.length; row++){
            for(int column = 0; column < grid[row].length; column++){
                
                //Horizontal
                if (column + 3 < grid[row].length){
                    long product = (long) grid[row][column] * grid[row][column+1] * grid[row][column+2] * grid[row][column+3];
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }

                //Vertical
                if (row + 3 < grid.length) {
                    long product = (long) grid[row][column] * grid[row+1][column] * grid[row+2][column] * grid[row+3][column];
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }

                //Diagonal down right
                if (row + 3 < grid.length && column + 3 < grid[row].length) {
                    long product = (long) grid[row][column] * grid[row+1][column+1] * grid[row+2][column+2] * grid[row+3][column+3];
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }

                //Diagnol down left
                if (row + 3 < grid.length && column - 3 >= grid[row].length) {
                    long product = (long) grid[row][column] * grid[row+1][column-1] * grid[row+2][column-2] * grid[row+3][column-3];
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }
            }
        }
                return maxProduct;
    }
}
