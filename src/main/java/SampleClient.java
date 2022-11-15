import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SampleClient {

    public static void main(String[] theArgs) {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));

        // Search for Patient resources
        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("SMITH"))
                .returnBundle(Bundle.class)
                .execute();
        List<Bundle.BundleEntryComponent> entries = response.getEntry();
        List<Patient> patients = new ArrayList<>();

        entries.forEach(it -> {
            patients.add((Patient)it.getResource());
        });

        List<Patient> sortedPatients = patients.stream().sorted(Comparator.comparing(p -> p.getName().get(0).getGivenAsSingleString())).collect(Collectors.toList());

        System.out.println();
        System.out.println("*********************************************************************************");
        System.out.println();

        sortedPatients.forEach( it -> System.out.println(it.getName().get(0).getNameAsSingleString() + " " + it.getBirthDate()));
    }

}
