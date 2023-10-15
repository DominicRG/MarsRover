package roman.dominic.Rover.util;

import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.models.Map;

public class MapValidationUtil {
    public static Map ensureMapExists() throws MapNotFoundException {
        Map map = Map.getInstance();
        if (map == null) {
            throw new MapNotFoundException();
        }
        return map;
    }
}
