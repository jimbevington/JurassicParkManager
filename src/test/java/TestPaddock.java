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
        herbivore1 = new Herbivore(SpeciesType.TRICERATOPS, "Herbie", 12, 70);
    }

    @Test
    public void canAddDinosaurs() {
        paddock1.addDino(herbivore1);
        assertEquals(1, paddock1.getDinosaurs().size());
    }

    @Test
    public void canRemoveDinosaurs() {
        paddock1.addDino(herbivore1);
        paddock1.removeDino(herbivore1);
        assertEquals(0, paddock1.getDinosaurs().size());
    }
}
