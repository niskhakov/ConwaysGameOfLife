package me.iskhakov.GoL;
class Cell {

    public enum State {
        ALIVE,
        DEAD;
    }

    @Override
    public String toString() {
        return state.toString();
    }

    private State state;

    Cell() {
        this.state = State.DEAD;
    }

    Cell(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    boolean isAlive() {
        return state.equals(State.ALIVE);
    }

    boolean isDead() {
        return  state.equals(State.DEAD);
    }
}