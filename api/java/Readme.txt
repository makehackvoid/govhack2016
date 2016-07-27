MHV GovHack2016 Java API
Requires JDK 1.7 and Maven 3.3x to compile.

To build the API, run "mvn clean package" in this directory.
The built artefact will be located at ./target/govhack2016-java-api.jar

The application is configured with the "mhvapp.properties" file located in this
directory. Copy the file into the same location as the jar file.

To run the Java API, use the following command-line:
java -jar govhack2016-java-api.jar

To test that the application is running correctly, open a browser to the 
configured host/port, e.g. http://localhost/ using the default settings.