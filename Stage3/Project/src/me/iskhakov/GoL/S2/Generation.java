package me.iskhakov.GoL.S2;

class Generation {
    private World world;
    private String name;

    Generation(World world) {
        this.world = world;
    }

    Generation(World world, String name) {
        this(world);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public World getWorld() {
        return world;
    }

    /**
     * Returns the neighbours of the cell in array in a clockwise order starting from west
     * 0 - w
     * 1 - nw
     * 2 - n
     * 3 - ne
     * 4 - e
     * 5 - se
     * 6 - s
     * 7 - sw
     * @param i
     * @param j
     * @return array of (i, j) cell's neighbours
     */
    protected Cell[] getNeighbours(int i, int j) {
        // There are some possible optimizations of this code, but I don't do them to keep code clean
        int rows = world.getNumberOfRows();
        int cols = world.getNumberOfColumns();
        Cell[] neighbours = new Cell[8];
        int jminus = (j - 1) >= 0 ? j - 1 : cols-1;
        int jplus = (j+1) >= cols ? 0 : j + 1 ;
        int iminus = (i - 1) >= 0 ? i - 1: rows - 1;
        int iplus = (i+1) >= rows ? 0 : i+1;
        Cell w =  world.get(i, jminus);
        Cell nw = world.get(iminus, jminus);
        Cell n =  world.get(iminus, j);
        Cell ne = world.get(iminus, jplus);
        Cell e =  world.get(i,jplus);
        Cell se = world.get(iplus, jplus);
        Cell s =  world.get(iplus, j);
        Cell sw = world.get(iplus, jminus);
        neighbours[0] = w;
        neighbours[1] = nw;
        neighbours[2] = n;
        neighbours[3] = ne;
        neighbours[4] = e;
        neighbours[5] = se;
        neighbours[6] = s;
        neighbours[7] = sw;
        return neighbours;
    }

    protected int getNumberOfAliveNeighbours(Cell[] neighbours) {
        int count = 0;
        for(Cell cell : neighbours) {
            if(cell.isAlive())
                count++;
        }

        return count;
    }

    Generation nextGeneration(String name) {
        // Math min is used in case of future reusing code when rows != cols
        return nextGeneration(name, new World(Math.min(world.getNumberOfColumns(), world.getNumberOfRows())));
    }

    Generation nextGeneration(String name, World existingWorld) {
        int rows = world.getNumberOfRows();
        int cols = world.getNumberOfColumns();
        // Math min is used in case of future reusing code when rows != cols
        int dims = Math.min(rows, cols); // This is stupid, but let it be
        if(Math.min(existingWorld.getNumberOfRows(), existingWorld.getNumberOfColumns()) != dims) {
            return null;
        }

        Generation nextGen = new Generation(existingWorld, name);

        int neighbours = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                neighbours = getNumberOfAliveNeighbours(getNeighbours(i, j));
                Cell.State nextState = null;
                if((neighbours == 2 || neighbours == 3) && world.get(i, j).isAlive()) {
                    nextState = Cell.State.ALIVE;
                } else if (!world.get(i,j).isAlive() && neighbours == 3) {
                    nextState = Cell.State.ALIVE;
                } else {
                    nextState = Cell.State.DEAD;
                }

                nextGen.world.set(i, j, new Cell(nextState));
            }
        }

        return nextGen;
    }

}
