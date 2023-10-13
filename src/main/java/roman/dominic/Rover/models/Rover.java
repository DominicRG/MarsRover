package roman.dominic.Rover.models;

import lombok.Getter;
import lombok.Setter;

public class Rover {
    private static Rover instance;
    @Getter
    @Setter
    private Integer x;
    @Getter
    @Setter
    private Integer y;
    @Getter
    @Setter
    private Direction direction;

    private Rover() {
    }

    public static Rover getInstance(int x, int y, Direction direction) {
        if(instance == null){
            instance = new Rover();
            instance.setX(x);
            instance.setY(y);
            instance.setDirection(direction);
        }

        return instance;
    }
}
