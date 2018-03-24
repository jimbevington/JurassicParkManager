import com.codeclan.jurassicpark.db.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPark {

    private Park park;
    private Paddock paddock1;
    private Paddock paddock2;
    private Carnivore carnivore1;
    private Carnivore carnivore2;
    private Herbivore herbivore1;
    private Herbivore herbivore2;


    @Before
    public void setUp() throws Exception {
        Park park = new Park("Jurassic Park");
        paddock1 = new Paddock("Green Gully", 5, park);
        paddock2 = new Paddock("Misty Mountains", 5, park);
        carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70, paddock1);
        carnivore2 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Blue", 15, 70, paddock2);
        herbivore1 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Betty", 20, 50, paddock1);
        herbivore2 = new Herbivore(SpeciesType.TRICERATOPS, "Robin", 20, 50, paddock1);
        park = new Park("Jurassic park");
    }

    @Test
    public void testParkHasPaddocks() {
        assertEquals(2, park.getPaddocks().size());
    }

    @Test
    public void testCanMoveDino() {
    }
}
