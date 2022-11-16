# HAPI FHIR Playground: Basic

This project is a skeleton project for using [HAPI FHIR](https://hapifhir.io) to access a public [FHIR](http://hl7.org/fhir/) server hosted at [http://hapi.fhir.org/baseR4](http://hapi.fhir.org/baseR4).

### Getting Started:

* [ ] Take a few minutes to familiarize yourself with the [FHIR Standard](http://hl7.org/fhir/) for health data exchange. In particular you might want to read the [Executive Summary](http://hl7.org/fhir/summary.html) and the [Developer Introduction](http://hl7.org/fhir/overview-dev.html)

* [ ] Try clicking on the link below. It is a FHIR *Search* operation used to look for patients with the name "Smith". (This is a publically accessible test server used by people all over the world, so we don't control what data is on it. Sometimes you may find unexpected or weird data there.) 

  http://hapi.fhir.org/baseR4/Patient?family=SMITH
  
* [ ] Create your own GitHub project and copy the contents of this repository into your own project (please don't fork this repository)

* [ ] Locate the class `SampleClient` and run it. This class runs the same search shown above.

* [ ] **Please, do not fork this repo.** Create your own private GitHub repository to do your work in.

### Basic Tasks:

* [ ] Modify `SampleClient` so that it prints the first and last name, and birth date of each Patient to the screen

* [ ] Sort the output so that the results are ordered by the patient's first name

* [ ] Commit your work

### Intermediate Tasks:

* [ ] Create a text file containing 20 different last names

* [ ] Modify 'SampleClient' so that instead of searching for patients with last name 'SMITH',
      it reads in the contents of this file and for each last name queries for patients with that last name

* [ ] Print the average response time for these 20 searches by implementing an IClientInterceptor that uses
      the requestStopWatch to determine the response time of each request.

* [ ] Run this loop three times, printing the average response time for each loop.  The first two times the loop should
      run as described above.  The third time the loop of 20 searches is run, the searches should be performed with
      caching disabled.

* [ ] If there is enough time between runs, you should expect to see loop 2 with a shorter average response time than loop 1 and 3.

* [ ] Please include unit tests for your work

* [ ] Commit your work

### Output

///////////////////////////////// WELCOME ////////////////////////////////////////////////


Hello User, Please select the option (1 or 2)

1: fetch the details for patient with last name SMITH
2: fetch the details for all the patient having last name mentioned in the file
2
Please wait while we process your request.....

23:23:54.355 INFO  ca.uhn.fhir.util.VersionUtil - HAPI FHIR version 6.2.0 - Rev 3f46aa141a
23:23:54.368 INFO  ca.uhn.fhir.context.FhirContext - Creating new FHIR context for FHIR version [R4]
23:23:54.984 INFO  ca.uhn.fhir.util.XmlUtil - Unable to determine StAX implementation: java.xml/META-INF/MANIFEST.MF not found

*********************************************************

Running iteration number 1 :

Time taken for SMITH --> 2191ms
Time taken for MESSI --> 1047ms
Time taken for RONALDO --> 1501ms
Time taken for ANDERSON --> 1249ms
Time taken for CENA --> 726ms
Time taken for BRETT --> 993ms
Time taken for JOHNSON --> 793ms
Time taken for BATISTA --> 1167ms
Time taken for MILLER --> 2040ms
Time taken for OLSON --> 1573ms
Time taken for MORRIS --> 842ms
Time taken for SCOTT --> 1745ms
Time taken for GREEN --> 2254ms
Time taken for BING --> 1504ms
Time taken for GELLER --> 2379ms
Time taken for TAYLOR --> 1831ms
Time taken for RICHARD --> 1427ms
Time taken for GARRY --> 1100ms
Time taken for FOX --> 1044ms
Time taken for FRANCIS --> 1451ms

Average time taken in iteration number 1 --> 1442ms

---------------------------------------------------------------------------------

Running iteration number 2 :

Time taken for SMITH --> 862ms
Time taken for MESSI --> 384ms
Time taken for RONALDO --> 705ms
Time taken for ANDERSON --> 714ms
Time taken for CENA --> 662ms
Time taken for BRETT --> 347ms
Time taken for JOHNSON --> 733ms
Time taken for BATISTA --> 309ms
Time taken for MILLER --> 763ms
Time taken for OLSON --> 959ms
Time taken for MORRIS --> 425ms
Time taken for SCOTT --> 864ms
Time taken for GREEN --> 834ms
Time taken for BING --> 588ms
Time taken for GELLER --> 1099ms
Time taken for TAYLOR --> 767ms
Time taken for RICHARD --> 732ms
Time taken for GARRY --> 406ms
Time taken for FOX --> 371ms
Time taken for FRANCIS --> 418ms

Average time taken in iteration number 2 --> 647ms

---------------------------------------------------------------------------------

Running iteration number 3 :

Time taken for SMITH --> 1255ms
Time taken for MESSI --> 1358ms
Time taken for RONALDO --> 1261ms
Time taken for ANDERSON --> 711ms
Time taken for CENA --> 675ms
Time taken for BRETT --> 839ms
Time taken for JOHNSON --> 749ms
Time taken for BATISTA --> 1197ms
Time taken for MILLER --> 1232ms
Time taken for OLSON --> 1900ms
Time taken for MORRIS --> 827ms
Time taken for SCOTT --> 2039ms
Time taken for GREEN --> 1291ms
Time taken for BING --> 1244ms
Time taken for GELLER --> 1581ms
Time taken for TAYLOR --> 1801ms
Time taken for RICHARD --> 1483ms
Time taken for GARRY --> 1176ms
Time taken for FOX --> 1036ms
Time taken for FRANCIS --> 1415ms

Average time taken in iteration number 3 --> 1253ms

---------------------------------------------------------------------------------



************************* Let's try again!! ***************************


Hello User, Please select the option (1 or 2)

1: fetch the details for patient with last name SMITH
2: fetch the details for all the patient having last name mentioned in the file

