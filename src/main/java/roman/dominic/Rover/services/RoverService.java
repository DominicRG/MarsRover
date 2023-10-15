package roman.dominic.Rover.services;

import org.springframework.stereotype.Service;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Rover;
import roman.dominic.Rover.util.MapValidationUtil;

import java.util.Optional;

@Service
public class RoverService implements RoverServiceImpl{
    @Override
    public Rover createRover(Integer x, Integer y, Direction direction) throws MapNotFoundException, ObstacleConflictException {
        Map map = MapValidationUtil.ensureMapExists();

        boolean obstacleExists = map.getObstacleList().stream()
                .anyMatch(obs -> obs.getX() == x && obs.getY() == y);

        if (obstacleExists) {
            throw new ObstacleConflictException("Existe un obstáculo en la misma posición");
        }

        Rover rover = Rover.getInstance(x, y, direction);
        map.setRover(rover);
        return rover;
    }

    @Override
    public Optional<Rover> getRover() throws MapNotFoundException {
        Map map = Map.getInstance();
        if(map == null){
            throw new MapNotFoundException();
        }

        return Optional.ofNullable(Rover.getInstance());
    }

    @Override
    public String commands(String input) throws MapNotFoundException {
        Map map = MapValidationUtil.ensureMapExists();

        input = input.toUpperCase();
        char[] caracteres = input.toCharArray();
        String response = null;

        for (char letra : caracteres) {
            if(letra!= 'M'){
                virar(map, letra);
            } else {
                response = avanzar(map);
            }
        }
        return response;
    }

    private String virar(Map map, char letra) {
        if(letra == 'R'){
            switch (map.getRover().getDirection()){
                case N -> map.getRover().setDirection(Direction.E);
                case E -> map.getRover().setDirection(Direction.S);
                case S -> map.getRover().setDirection(Direction.W);
                case W -> map.getRover().setDirection(Direction.N);
            }
        }
        if(letra == 'L'){
            switch (map.getRover().getDirection()){
                case N -> map.getRover().setDirection(Direction.W);
                case E -> map.getRover().setDirection(Direction.N);
                case S -> map.getRover().setDirection(Direction.E);
                case W -> map.getRover().setDirection(Direction.S);
            }
        }

        return map.getRover().getX() + ":" + map.getRover().getY() + ":" + map.getRover().getDirection();
    }

    private String avanzar(Map map) {
        final int[] moverX = {map.getRover().getX()};
        final int[] moverY = {map.getRover().getY()};

        switch (map.getRover().getDirection()){
            case E, W -> {
                moverX[0] = map.getRover().getX() + map.getRover().getDirection().getValue();
                moverY[0] = map.getRover().getY();
            }
            case N, S -> {
                moverY[0] = map.getRover().getY() + map.getRover().getDirection().getValue();
                moverX[0] = map.getRover().getX();
            }
        }

        boolean obstacleExists = map.getObstacleList().stream()
                .anyMatch(obs -> obs.getX() == moverX[0] && obs.getY() == moverY[0]);

        if (obstacleExists) {
            System.out.println("Existe un obstaculo");
            return "o:" + map.getRover().getX() + ":" + map.getRover().getY() + ":" + map.getRover().getDirection();

        } else {
            if(moverX[0] >= 10) moverX[0] -= 10;
            if(moverX[0] <= -1) moverX[0] += 10;

            if(moverY[0] >= 10) moverY[0] -= 10;
            if(moverY[0] <= -1) moverY[0] += 10;

            map.getRover().setX(moverX[0]);
            map.getRover().setY(moverY[0]);

            return map.getRover().getX() + ":" + map.getRover().getY() + ":" + map.getRover().getDirection();
        }
    }
}