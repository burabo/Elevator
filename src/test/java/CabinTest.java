import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CabinTest {

    Cabin cabin;
    Motor motor;
    Portas doors;
    Botoneira buttons;

    @BeforeEach
    void setup(){
        Boolean doorOpenButton = true;
        Semaphore doorSem = new Semaphore(1);
        doors = new Portas("Portas", doorSem, doorOpenButton);
        cabin = new Cabin(doors, doorSem);
        motor = new Motor("Hey", cabin, 5000);

    }

    @Test
    void determineNextFloorTest(){
        cabin.currentFloor=0;
        cabin.pressedFloors.add(5);
        cabin.pressedFloors.add(2);

        cabin.determineNextFloor();
        assertEquals(cabin.nextFloor, 2);
    }
}
