package me.iskhakov.GoL.S2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ndim = scanner.nextInt();
        long seed = scanner.nextLong();
        int gen = scanner.nextInt();

        World world = new World(ndim, seed);

        // Preserve worlds to not use memory a lot.
        Generation nextGen = new Generation(world, "Generation: #0");
        World nextWorld = new World(ndim);
        World temp;


        for(int i = 0; i < gen; i++) {
            nextGen = nextGen.nextGeneration("Generation: " + (i+1), nextWorld);
            // Swap worlds
            temp = world;
            world = nextWorld;
            nextWorld = temp;
        }

        System.out.println(nextGen.getWorld());
    }
}
