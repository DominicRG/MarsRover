package roman.dominic.Rover.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roman.dominic.Rover.exceptions.MapNotFoundException;
import roman.dominic.Rover.exceptions.ObstacleConflictException;
import roman.dominic.Rover.models.Direction;
import roman.dominic.Rover.services.IRoverService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class) Esto es con JUnit 4
class RoverControllerTest {

    @Mock
    private IRoverService roverService;
    @InjectMocks
    private RoverController roverController;

    @DisplayName("Test para ingresar un input")
    @Test
    void commands() throws MapNotFoundException {
        String input = "MRMM";
        roverController.commands(input);
        verify(roverService, times(1)).commands(input);
    }

    @DisplayName("Test para obtener el Rover")
    @Test
    void getRover() throws MapNotFoundException {
        roverController.getRover();
        verify(roverService, times(1)).getRover();
    }

    @DisplayName("Test para crear un Rover en una ubicacion personalizada")
    @Test
    void createRover() throws MapNotFoundException, ObstacleConflictException {
        roverController.createRover(0,0, Direction.N);
        verify(roverService, times(1)).createRover(0,0,Direction.N);
    }
}