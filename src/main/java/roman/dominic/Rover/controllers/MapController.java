package roman.dominic.Rover.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roman.dominic.Rover.models.Map;

@RestController
@RequestMapping("/map")
public class MapController {

    @PostMapping
    public ResponseEntity createDefaultMap(){
        Map map = Map.getInstance(10,10);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Map> getMap(){
        Map map = Map.getInstance();
        if(map == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map);
    }
}
