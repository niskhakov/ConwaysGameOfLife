package me.iskhakov.GoL.S2;

import java.util.Random;

class World {
    private Cell[][] world;

    public World(int dims) {
        // If each row contains different amount of cells, consider revising `getNumberOfColumns()` and all calls
        world = new Cell[dims][dims];
    }

    public World(int dims, long seed) {
        this(dims);
        populateWorld(seed);
    }

    protected void populateWorld(long seed) {
        Random random = new Random(seed);
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                world[i][j] = new Cell(random.nextBoolean() ? Cell.State.ALIVE : Cell.State.DEAD);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < world.length; i++ ) {
            for(int j = 0; j < world[i].length; j++) {
                sb.append(world[i][j].isAlive() ? "O" : " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public Cell get(int i, int j) {
        return world[i][j];
    }

    public void set(int i, int j, Cell value) {
        world[i][j] = value;
    }

    public int getNumberOfRows() {
        return world.length;
    }

    public int getNumberOfColumns() {
        return world[0].length;
    }

    public int getAliveNumber() {
        int counter = 0;
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[i].length; j++) {
                if(world[i][j].isAlive()) {
                    counter++;
                }
            }
        }
        return counter;
    }

}
