import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GridSolver {
    public static void main(String[] args) {
        try{
            int[][]grid = readGrid("grid.txt");
            System.out.println("Grid loaded with dimensions: " + grid.length + "x" + grid[0].length);

            Result mainResult = findLargestProduct(grid);
            System.out.println("\nLargest product in grid: " + mainResult.maxProduct);
            System.out.println("Numbers: " + Arrays.toString(mainResult.bestNumbers));
            System.out.println("Position: row " + mainResult.bestRow + ", column " + mainResult.bestColumn);
            System.out.println("Direction: " + mainResult.bestDirection);
  

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

    private static Result findLargestProduct(int[][]grid) {
        long maxProduct = 0;
        int[] bestNumbers = new int[4];
        int bestRow = -1, bestColumn = -1;
        String bestDirection = "";

        for(int row = 0; row < grid.length; row++) {
            for(int column = 0; column < grid[row].length; column++) {
                
                //Horizontal
                if (column + 3 < grid[row].length) {
                    long product = (long) grid[row][column] * grid[row][column+1] * grid[row][column+2] * grid[row][column+3];
                    if (product > maxProduct) {
                        maxProduct = product;
                        bestNumbers[0] = grid[row][column];
                        bestNumbers[1] = grid[row][column+1]; 
                        bestNumbers[2] = grid[row][column+2];
                        bestNumbers[3] = grid[row][column+3];
                        bestRow = row;
                        bestColumn = column;
                        bestDirection = "Horizontal";
                    }
                }

                //Vertical
                if (row + 3 < grid.length) {
                    long product = (long) grid[row][column] * grid[row+1][column] * grid[row+2][column] * grid[row+3][column];
                    if (product > maxProduct) {
                        maxProduct = product;
                        bestNumbers[0] = grid[row][column];
                        bestNumbers[1] = grid[row+1][column];
                        bestNumbers[2] = grid[row+2][column];
                        bestNumbers[3] = grid[row+3][column];
                        bestRow = row;
                        bestColumn = column;
                        bestDirection = "Vertical";
                    }
                }

                //Diagonal down right
                if (row + 3 < grid.length && column + 3 < grid[row].length) {
                    long product = (long) grid[row][column] * grid[row+1][column+1] * grid[row+2][column+2] * grid[row+3][column+3];
                    if (product > maxProduct) {
                        maxProduct = product;
                        bestNumbers[0] = grid[row][column];
                        bestNumbers[1] = grid[row+1][column+1];
                        bestNumbers[2] = grid[row+2][column+2];
                        bestNumbers[3] = grid[row+3][column+3];
                        bestRow = row;
                        bestColumn = column;
                        bestDirection = "Diagonal down right";
                    }
                }

                //Diagnol down left
                if (row + 3 < grid.length && column - 3 >= 0) {
                    long product = (long) grid[row][column] * grid[row+1][column-1] * grid[row+2][column-2] * grid[row+3][column-3];
                    if (product > maxProduct) {
                        maxProduct = product;
                        bestNumbers[0] = grid[row][column];
                        bestNumbers[1] = grid[row+1][column-1];
                        bestNumbers[2] = grid[row+2][column-2];
                        bestNumbers[3] = grid[row+3][column-3];
                        bestRow = row;
                        bestColumn = column;
                        bestDirection = "Diagonal down left";
                    }
                }
            }
        }
        return new Result(maxProduct, bestNumbers, bestRow, bestColumn, bestDirection);
    }

    private static class Result {
        long maxProduct;
        int[] bestNumbers;
        int bestRow, bestColumn;
        String bestDirection;
        
        Result(long maxProduct, int[] bestNumbers, int bestRow, int bestColumn, String bestDirection) {
            this.maxProduct = maxProduct;
            this.bestNumbers = bestNumbers; 
            this.bestColumn = bestColumn;
            this.bestDirection = bestDirection;
            this.bestRow = bestRow;
        }
    }
}
