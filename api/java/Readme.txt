----------------------------
MHV GovHack2016 Java web API
----------------------------

Requires JDK 1.7 and Maven 3.3x to compile.

To build the API, run "mvn clean package" in this directory.
The built artefact will be located at ./target/govhack2016-java-api.jar

The application is configured with the "mhvapp.properties" file located in this
directory. Copy the file into the same location as the jar file.

To run the Java API, use the following command-line:
java -jar govhack2016-java-api.jar

To test that the application is running correctly, open a browser to the 
configured host/port, e.g. http://localhost/ using the default settings.

--------
API URLs
--------

Retrieve the details of all parking lots:
	http://localhost/lots

Retrieve the details of parking lot 204:
	http://localhost/lots?lot=204

Retrieve all time-based events within a time range specified in epoch format:
	http://localhost/events?from=1459432811000&to=1459432911000

Retrieves the closest time-based event to the given time in epoch format:
Parking events are excluded.
	http://localhost/api/java/events?closest=609969609796
