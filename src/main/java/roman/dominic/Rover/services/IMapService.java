package roman.dominic.Rover.services;

import roman.dominic.Rover.models.Map;

import java.util.Optional;

public interface IMapService {
    Map createDefaultMap();
    Optional<Map> getMap();
}
