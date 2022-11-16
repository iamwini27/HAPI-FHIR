import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class SampleClientTest {

    @Test
    void test1() throws FileNotFoundException {
        List<Patient> patientList = new SampleClient().fetchPatientDetail();
        assert patientList.size() > 0;
    }

}
