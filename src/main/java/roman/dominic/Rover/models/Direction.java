package roman.dominic.Rover.models;

public enum Direction {
    N(1), S(-1), E(1), W(-1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
