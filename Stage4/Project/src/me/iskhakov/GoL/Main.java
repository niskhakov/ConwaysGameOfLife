package me.iskhakov.GoL;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ndim = scanner.nextInt();
        long seed = scanner.nextLong();
        int gen = scanner.nextInt();

        EvolutionThread evolution = new EvolutionThread("Evolution thread", ndim, gen, seed);
    }
}
