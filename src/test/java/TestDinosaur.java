import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDinosaur {

    private Paddock paddock;
    private Paddock paddock1;
    private Carnivore carnivore;
    private Herbivore herbivore;

    @Before
    public void setUp() throws Exception {
        paddock = new Paddock("Green Gully", 10);
        paddock1 = new Paddock("Arid Desert", 20);
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
}