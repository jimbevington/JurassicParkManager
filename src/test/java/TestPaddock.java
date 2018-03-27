import com.codeclan.jurassicpark.db.models.AlertType;
import com.codeclan.jurassicpark.db.models.Paddock;
import com.codeclan.jurassicpark.db.models.Park;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPaddock {

    private Park park;
    private Paddock paddock;

    @Before
    public void setUp() throws Exception {
        park = new Park("Jurassic");
        paddock = new Paddock("test", 3, park);
    }

    @Test
    public void canGetAlertMessage__NONE() {
        assertEquals("", paddock.getAlertMessage());
    }

    @Test
    public void canGetAlertMessage__ESCAPE() {
        paddock.setAlert(AlertType.ESCAPE);
        assertEquals("ESCAPE!", paddock.getAlertMessage());
    }
}
