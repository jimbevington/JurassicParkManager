import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPaddock {

    private Paddock paddock1;
    private Paddock paddock2;
    private Herbivore herbivore1;
    private Herbivore herbivore2;

    @Before
    public void setUp() throws Exception {
        paddock1 = new Paddock("The Aviary", 25);
        paddock2 = new Paddock("Dinosaur Dungeon", 6);
        herbivore1 = new Herbivore(SpeciesType.TRICERATOPS, "Herbie", 12, 70);
        herbivore2 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Bruce", 15, 50);
    }

    @Test
    public void canAddDinosaurs() {
        paddock1.addDino(herbivore1);
        assertEquals(1, paddock1.getDinosaurs().size());
    }
}
