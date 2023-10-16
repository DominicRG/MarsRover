package roman.dominic.Rover.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.services.IMapService;

@RestController
@RequestMapping("/map")
public class MapController {

    IMapService mapService;
    @Autowired
    public MapController(IMapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<Map> createDefaultMap(){
        Map map = mapService.createDefaultMap();
        return ResponseEntity.status(201).body(map);
    }

    @GetMapping
    public ResponseEntity<Map> getMap(){
        if(mapService.getMap().isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapService.getMap().get());
    }
}
