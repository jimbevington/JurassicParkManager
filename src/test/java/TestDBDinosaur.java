import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.SpeciesType;
import org.junit.Before;

public class TestDBDinosaur {

    private Paddock paddock;
    private Carnivore carnivore;
    private Herbivore herbivore;

    @Before
    public void setUp() throws Exception {
        carnivore = new Carnivore(SpeciesType.VELOCIRAPTOR, "Gerald", 5, 100, 90, paddock);
    }

//    test starts off secure

//    test starts off not hungry

//    can get Fed
}
