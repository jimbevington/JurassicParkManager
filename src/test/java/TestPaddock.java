import com.codeclan.jurassicpark.db.models.AlertType;
import com.codeclan.jurassicpark.db.models.Paddock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPaddock {

    private Paddock paddock;

    @Before
    public void setUp() throws Exception {
        paddock = new Paddock("test", 3);
    }

    @Test
    public void canGetAlertMessage__NONE() {
        assertEquals("", paddock.alertMessage());
    }

    @Test
    public void canGetAlertMessage__ESCAPE() {
        paddock.setAlert(AlertType.ESCAPE);
        assertEquals("ESCAPE", paddock.alertMessage());
    }
}
