package roman.dominic.Rover.services;

import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Obstacle;

public interface ObstacleServiceImpl {
    Obstacle createObstacle(Integer x, Integer y) throws MapNotFoundException, ObstacleConflictException;
    boolean removeObstacle(Integer x, Integer y) throws MapNotFoundException;
}
