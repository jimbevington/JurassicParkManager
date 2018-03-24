import com.codeclan.jurassicpark.db.models.*;
import org.junit.Before;

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
        paddock1 = new Paddock("Green Gully", 5);
        paddock2 = new Paddock("Misty Mountains", 5);
        carnivore1 = new Carnivore(SpeciesType.TREX, "Fluffy", 30, 70, paddock1);
        carnivore2 = new Carnivore(SpeciesType.VELOCIRAPTOR, "Blue", 15, 70, paddock2);
        herbivore1 = new Herbivore(SpeciesType.BRACHIOSAURUS, "Betty", 20, 50, paddock1);
        herbivore2 = new Herbivore(SpeciesType.TRICERATOPS, "Robin", 20, 50, paddock1);
        park = new Park("Jurassic park");
        paddock1.getDinosaurs().add(carnivore1);
        paddock2.getDinosaurs().add(carnivore2);
        park.getPaddocks().add(paddock1);
        park.getPaddocks().add(paddock2);
    }
}
