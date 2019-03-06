package me.iskhakov.GoL;

import java.io.IOException;

public class EvolutionThread extends Thread {
    private int ndim;
    private int gen;
    private long seed;

    public EvolutionThread(String name, int ndim, int gen, long seed) {
        super(name);
        this.ndim = ndim;
        this.gen = gen;
        this.seed = seed;
    }

    @Override
    public void run() {
        World world = new World(ndim, seed);

        // Preserve worlds to not use memory a lot.
        Generation nextGen = new Generation(world, "Generation: #0");
        World nextWorld = new World(ndim);
        World temp;


        for(int i = 0; i < gen; i++) {
            if(!isInterrupted()) {

            }
            // Cross-platform screen cleanscreen
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            } catch (IOException | InterruptedException e) {}

            // Generation evolution
            nextGen = nextGen.nextGeneration("Generation: #" + (i+1), nextWorld);

            // Printing infos
            System.out.println(nextGen.getName());
            //System.out.println("Alive: " + nextGen.getWorld().getAliveNumber()); // -- slow operation
            int alive =  nextGen.getPrecalculatedAlive();
            System.out.println("Alive: " + alive);
            System.out.println(nextGen.getWorld());
            if(alive == 0) {
                System.out.println("Population died on " + (i+1) + "th step.");
                break;
            }

            // Swap worlds to save memory
            temp = world;
            world = nextWorld;
            nextWorld = temp;

            // Preserve user's eyes =)
            try {
                Thread.sleep(200);
            } catch(InterruptedException e) {}
        }
    }
}
