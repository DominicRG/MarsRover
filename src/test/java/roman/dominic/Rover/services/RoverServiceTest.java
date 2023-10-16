package roman.dominic.Rover.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.models.Map;
import roman.dominic.Rover.models.Rover;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class RoverServiceTest {

    @Mock
    private Map map; // Mock de la dependencia Map
    @InjectMocks
    private RoverService roverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Configura el mock con un estado inicial simulado
        this.map = Map.getInstance(10,10);
        Rover rover = Rover.getInstance(0, 0, Direction.N);
        this.map.setRover(rover);
    }

    @DisplayName("Test para el ingreso de comandos")
    @Test
    void commands() throws MapNotFoundException {
        String input = "MRMM";

        // Ejecuta el método commands del servicio
        String result = roverService.commands(input);

        // Verifica el resultado esperado
        assertEquals("2:1:E", result); // Ajusta esto según el resultado esperado para tu caso
    }

    @DisplayName("Test para obtener el Rover")
    @Test
    void getRover() throws MapNotFoundException {
        Optional<Rover> rover = roverService.getRover();
        assertNotEquals(null, rover);
    }

    @DisplayName("Test para crear el Rover")
    @Test
    void createRover() throws MapNotFoundException, ObstacleConflictException {
        Rover expectedRover = map.getRover();
        Rover rover = roverService.createRover(10,10,Direction.N);
        assertEquals(expectedRover, rover);
    }
}