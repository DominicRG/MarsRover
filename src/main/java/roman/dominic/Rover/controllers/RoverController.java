package roman.dominic.Rover.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Rover;
import roman.dominic.Rover.services.RoverServiceImpl;

@RestController
@RequestMapping("/rover")
public class RoverController {

    RoverServiceImpl roverService;

    @Autowired
    public RoverController(RoverServiceImpl roverService) {
        this.roverService = roverService;
    }

    @PostMapping("/{x}/{y}/{direction}")
    public ResponseEntity createRover(@PathVariable Integer x, @PathVariable Integer y,
                                      @PathVariable Direction direction){
        if((x<0 || x>9) && (y<0 || y>9)){
            return ResponseEntity.badRequest().build();
        }

        Rover rover = null;
        try {
            rover = roverService.createRover(x, y, direction);
        } catch (MapNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (ObstacleConflictException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
        return ResponseEntity.status(201).body(rover);
    }

    @GetMapping
    public ResponseEntity getRover(){
        try {
            if(roverService.getRover().isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(roverService.getRover().get());
        } catch (MapNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/{input}")
    public ResponseEntity<Object> commands(@PathVariable String input){
        String response = null;
        try {
            response = roverService.commands(input);
        } catch (MapNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
