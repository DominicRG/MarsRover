package roman.dominic.Rover.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Rover;

@RestController
@RequestMapping("/rover")
public class RoverController {

    @PostMapping("/{x}/{y}/{direction}")
    public ResponseEntity createRover(@PathVariable Integer x, @PathVariable Integer y,
                                      @PathVariable Direction direction){
        if((x<0 || x>9) && (y<0 || y>9)){
            return ResponseEntity.badRequest().build();
        }

        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.status(404).body("No existe un mapa aun");
        }

        boolean obstacleExists = map.getObstacleList().stream()
                .anyMatch(obs -> obs.getX() == x && obs.getY() == y);

        if (obstacleExists) {
            return ResponseEntity.status(409).body("Existe un obstáculo en la misma posición");
        }

        Rover rover = Rover.getInstance(x, y, direction);
        map.setRover(rover);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getRover(){
        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.status(404).body("No existe un mapa aun");
        }

        Rover rover = Rover.getInstance();
        if(rover == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rover);
    }

    @PostMapping("/{input}")
    public ResponseEntity<Object> commands(@PathVariable String input){
        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.status(404).body("No existe un mapa aun");
        }

        input.toUpperCase();
        char[] caracteres = input.toCharArray();
        String response = null;

        for (char letra : caracteres) {
            if(letra!= 'M'){
                virar(map, letra);
            } else {
                response = avanzar(map, response);
            }
        }

        return ResponseEntity.ok(response);
    }

    private String avanzar(Map map, String response) {
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
}
