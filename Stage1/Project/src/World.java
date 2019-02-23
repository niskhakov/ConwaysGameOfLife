import java.util.Scanner;
import java.util.Random;

public class World {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Dimension
        int s = scanner.nextInt(); // Seed

        Random random = new Random(s);
        boolean[][] world = new boolean[n][n];


        // World population
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                world[i][j] = random.nextBoolean();
            }
        }

        // World printing
        for(int i = 0; i < n; i++ ) {
            for(int j = 0; j < n; j++) {
                System.out.print(world[i][j] ? "O" : " ");
            }
            System.out.println();
        }

    }
}
