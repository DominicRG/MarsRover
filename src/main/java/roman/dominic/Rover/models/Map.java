package roman.dominic.Rover.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private static Map instance;
    @Getter
    @Setter
    private Rover rover;
    @Getter
    @Setter
    private List<Obstacle> obstacleList;
    @Getter
    @Setter
    private Integer sizeX;
    @Getter
    @Setter
    private Integer sizeY;

    private Map() {
    }

    public static Map getInstance(int sizeX, int sizeY) {
        if (instance == null) {
            instance = new Map();
            instance.setRover(null);
            instance.setObstacleList(new ArrayList<>());
            instance.setSizeX(sizeX);
            instance.setSizeY(sizeY);
        }
        return instance;
    }
}
