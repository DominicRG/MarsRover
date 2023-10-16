package roman.dominic.Rover.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Obstacle;
import roman.dominic.Rover.services.IObstacleService;

@RestController
@RequestMapping("/obstacle")
public class ObstacleController {

    IObstacleService obstacleService;

    @Autowired
    public ObstacleController(IObstacleService obstacleService) {
        this.obstacleService = obstacleService;
    }

    @PostMapping("/{x}/{y}")
    public ResponseEntity createObstacle(@PathVariable Integer x, @PathVariable Integer y){
        if((x<0 || x>9) && (y<0 || y>9)){
            return ResponseEntity.badRequest().build();
        }

        Obstacle obstacle = null;
        try {
            obstacle = obstacleService.createObstacle(x, y);
        } catch (MapNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (ObstacleConflictException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
        return ResponseEntity.status(201).body(obstacle);
    }

    @DeleteMapping("/{x}/{y}")
    public ResponseEntity removeObstacle(@PathVariable Integer x, @PathVariable Integer y){
        boolean removedObstacle = false;
        try {
            removedObstacle = obstacleService.removeObstacle(x, y);
        } catch (MapNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

        if(!removedObstacle){
            return ResponseEntity.status(404).body("No existe un obstáculo en esa posición");
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
