package roman.dominic.Rover.services;

import org.springframework.stereotype.Service;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Obstacle;
import roman.dominic.Rover.models.Rover;
import roman.dominic.Rover.util.MapValidationUtil;

@Service
public class ObstacleService implements ObstacleServiceImpl{

    @Override
    public Obstacle createObstacle(Integer x, Integer y) throws MapNotFoundException, ObstacleConflictException {
        Map map = MapValidationUtil.ensureMapExists();

        Rover rover = Rover.getInstance();
        if(rover != null){
            if((rover.getX() == x) && (rover.getY() == y)){
                throw new ObstacleConflictException("El espacio lo ocupa el rover");
            }
        }

        Obstacle obstacle = new Obstacle(x, y);

        boolean obstacleExists = map.getObstacleList().stream()
                .anyMatch(obs -> obs.getX() == x && obs.getY() == y);

        if (obstacleExists) {
            throw new ObstacleConflictException("Existe un obstáculo en la misma posición");
        }

        map.getObstacleList().add(obstacle);
        return obstacle;
    }

    @Override
    public boolean removeObstacle(Integer x, Integer y) throws MapNotFoundException {
        Map map = MapValidationUtil.ensureMapExists();

        int obstacleIndex = -1;

        for (int i = 0; i < map.getObstacleList().size(); i++) {
            Obstacle obstacle = map.getObstacleList().get(i);
            if (obstacle.getX() == x && obstacle.getY() == y) {
                obstacleIndex = i;
                break;
            }
        }

        if (obstacleIndex != -1) {
            map.getObstacleList().remove(obstacleIndex);
            return true;
        } else {
            return false;
        }
    }
}
