import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.CacheControlDirective;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SampleClient {

    static final String URL = "http://hapi.fhir.org/baseR4";

    public static void main(String[] theArgs) throws FileNotFoundException {
        new SampleClient().runTask();
    }

    public void runTask() throws FileNotFoundException {
        Scanner userInputScanner = new Scanner(System.in);

        System.out.println();
        System.out.println();

        System.out.println("///////////////////////////////// WELCOME ////////////////////////////////////////////////");

        System.out.println();
        System.out.println();

        while(true) {
            System.out.println("Hello User, Please select the option (1 or 2)");
            System.out.println();
            System.out.println("1: fetch the details for patient with last name SMITH");
            System.out.println("2: fetch the details for all the patient having last name mentioned in the file");

            int choice = userInputScanner.nextInt();

            System.out.println("Please wait while we process your request.....");
            System.out.println();

            switch(choice) {
                case 1:
                    // Basic task --> Search for Patient resources for a single patient 'SMITH'
                    fetchPatientDetail();
                    break;

                case 2:
                    // Intermediate task --> read from the file and print the details
                    calculateAverageResponseTime();
                    break;

                default:
                    System.out.println("Please try again!!");

            }

            System.out.println();
            System.out.println();

            System.out.println("************************* Let's try again!! ***************************");

            System.out.println();
            System.out.println();
        }
    }


    public IGenericClient getClient() {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient(URL);
        client.registerInterceptor(new Interceptor());
        return client;
    }

    public List<Patient> fetchPatientDetail() {

        IGenericClient client = getClient();

        List<Patient> patients = new ArrayList<>();
        List<Bundle.BundleEntryComponent> entries;
        List<Patient> sortedPatients;

        Bundle response = client
                .search()
                .forResource("Patient")
                .where(Patient.FAMILY.matches().value("SMITH"))
                .returnBundle(Bundle.class)
                .execute();
        entries = response.getEntry();

        entries.forEach(it -> {
            patients.add((Patient)it.getResource());
        });

        sortedPatients = patients.stream().sorted(Comparator.comparing(p -> p.getName().get(0).getGivenAsSingleString())).collect(Collectors.toList());

        System.out.println();
        System.out.println("*********************************************************************************");
        System.out.println("      Here are the details for patient");
        System.out.println("*********************************************************************************");
        System.out.println();

        sortedPatients.forEach( it -> System.out.println(it.getName().get(0).getNameAsSingleString() + " " + it.getBirthDate()));
        return sortedPatients;
    }

    public void calculateAverageResponseTime() throws FileNotFoundException {

        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient(URL);
        client.registerInterceptor(new Interceptor());

        Scanner sc = new Scanner(new File("C:\\Users\\91980\\Desktop\\HAPI-FHIR\\src\\main\\resources\\names.txt"));
        List<String> lastNames = new ArrayList<>();

        while (sc.hasNext()) {
            lastNames.add(sc.nextLine());
        }

        System.out.println();
        System.out.println("*********************************************************");
        System.out.println();

        for(int i = 0; i < 3; i++) {
            int iteration = i + 1;
            System.out.println("Running iteration number " + iteration + " :");
            System.out.println();
            int totalTime = 0;
            for (String name : lastNames) {
                if(i == 2) {
                    client
                            .search()
                            .forResource("Patient")
                            .cacheControl(CacheControlDirective.noCache())
                            .where(Patient.FAMILY.matches().value(name))
                            .returnBundle(Bundle.class)
                            .execute();
                }
                else {
                    client
                            .search()
                            .forResource("Patient")
                            .where(Patient.FAMILY.matches().value(name))
                            .returnBundle(Bundle.class)
                            .execute();
                };
                System.out.println("Time taken for " + name + " --> " + Interceptor.time + "ms");

                totalTime += Integer.parseInt(Interceptor.time);
                Interceptor.time = "0";
            }
            System.out.println();
            System.out.println("Average time taken in iteration number " + iteration + " --> " + totalTime / 20 + "ms");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println();
        }
    }
}