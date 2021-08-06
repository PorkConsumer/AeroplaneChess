package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {
    Airplane p1;
    Airplane p2;
    Airplane p3;
    Airplane p4;

    @BeforeEach
    public void setUpPlane() {
        p1 = new Airplane("red", 3);
        p2 = new Airplane("yellow", 1);
        p3 = new Airplane("green", 4);
        p4 = new Airplane("blue", 2);
    }

    @Test
    public void TestAirplaneConstructor() {
        assertTrue(p1.getColor().equals("red"));
        assertEquals(3, p1.getPlaneNumber());
        assertTrue(p1.getStatus().equals("waiting"));
        assertEquals(0, p1.getStepsTaken());
        assertNull(p1.getPosition());
    }

//    @Test
//    public void TestMoveWaitingRedPlane() {
//        p1.move();
//    }



}
