import com.codeclan.jurassicpark.db.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDinosaur {

    private Park park;
    private Paddock paddock;
    private Paddock paddock1;
    private Carnivore carnivore;
    private Herbivore herbivore;

    @Before
    public void setUp() throws Exception {
        park = new Park("Jurassic Park");
        paddock = new Paddock("Green Gully", 10, park);
        paddock1 = new Paddock("Arid Desert", 20, park);
        carnivore = new Carnivore(SpeciesType.VELOCIRAPTOR, "Gerald", 5, 90, paddock);
        herbivore = new Herbivore(SpeciesType.TRICERATOPS, "Horatio", 8, 80, paddock1);
    }

//    can get Fed

    @Test
    public void canGetFed() {
        carnivore.setHunger(0);
        carnivore.getFed();
        assertEquals(0, carnivore.getHunger());
    }


//    can test for other tings
    @Test
    public void testCanReturnSpecies() {
        assertEquals("velociraptor", carnivore.getSpecies().toString().toLowerCase());
    }

    @Test
    public void testCanIncreaseHunger() {
        assertEquals(1, carnivore.increaseHunger());
    }
}