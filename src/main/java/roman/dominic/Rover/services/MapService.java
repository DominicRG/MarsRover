package roman.dominic.Rover.services;

import org.springframework.stereotype.Service;
import roman.dominic.Rover.models.Map;

import java.util.Optional;

@Service
public class MapService implements MapServiceImpl{
    @Override
    public Map createDefaultMap() {
        return Map.getInstance(10,10);
    }

    @Override
    public Optional<Map> getMap() {
        return Optional.ofNullable(Map.getInstance());
    }
}
