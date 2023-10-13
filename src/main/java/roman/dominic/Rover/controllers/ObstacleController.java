package roman.dominic.Rover.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Obstacle;
import roman.dominic.Rover.models.Rover;

@RestController
@RequestMapping("/obstacle")
public class ObstacleController {

    @PostMapping("/{x}/{y}")
    public ResponseEntity createObstacle(@PathVariable Integer x, @PathVariable Integer y){
        if((x<0 || x>9) && (y<0 || y>9)){
            return ResponseEntity.badRequest().build();
        }

        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.status(404).body("No existe un mapa aun");
        }

        Rover rover = Rover.getInstance();
        if(rover != null){
            if((rover.getX() == x) && (rover.getY() == y)){
                return ResponseEntity.status(409).body("El espacio lo ocupa el rover");
            }
        }

        Obstacle obstacle = new Obstacle(x, y);

        boolean obstacleExists = map.getObstacleList().stream()
                .anyMatch(obs -> obs.getX() == x && obs.getY() == y);

        if (obstacleExists) {
            return ResponseEntity.status(409).body("Existe un obstáculo en la misma posición");
        }

        map.getObstacleList().add(obstacle);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{x}/{y}")
    public ResponseEntity removeObstacle(@PathVariable Integer x, @PathVariable Integer y){
        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.status(404).body("No existe un mapa aun");
        }

        int obstacleIndex = -1; // Inicializamos con un valor que indica que no se encontró el obstáculo

        for (int i = 0; i < map.getObstacleList().size(); i++) {
            Obstacle obstacle = map.getObstacleList().get(i);
            if (obstacle.getX() == x && obstacle.getY() == y) {
                obstacleIndex = i; // Se encontró el obstáculo, guarda el índice
                break; // Termina el bucle
            }
        }

        if (obstacleIndex != -1) {
            // Se encontró un obstáculo, elimínalo y devuelve una respuesta exitosa
            map.getObstacleList().remove(obstacleIndex);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("No existe un obstáculo en esa posición");
        }
    }
}
