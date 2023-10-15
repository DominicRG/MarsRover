package roman.dominic.Rover.exceptions;

public class MapNotFoundException extends Exception{
    public MapNotFoundException() {
        super("No existe un mapa aún");
    }
}
