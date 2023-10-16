package roman.dominic.Rover.services;

import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.models.Rover;

import java.util.Optional;

public interface IRoverService {
    Rover createRover(Integer x, Integer y, Direction direction) throws MapNotFoundException, ObstacleConflictException;
    Optional<Rover> getRover() throws MapNotFoundException;
    String commands(String input) throws MapNotFoundException;
}
