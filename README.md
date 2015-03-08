#Tutorial for Spring Rest with Mongo DB (not using XML configuration)

Tested with:

* Java 7
* Tomcat 7.0.59
* Please see _build.gradle_ for the other dependencies

### To run using the tomcat-plugin:
> ./gradlew tomcatRun

To do a quick test if the app is running:

* <http://localhost:8080/hswm/hello>

##### Note for IntelliJ IDEA user: if it looks like the Lombok annotations are not working, check the following in the preferences:

* Compiler > Annotation Processors > check ''Enable annotation processing'
* Lombok plugin > check 'Enable Lombok support for this project'


##### See Swagger documentation for endpoints

* <http://localhost:8080/sdoc.jsp> (doesn't work 100% yet, have to put in this api-docs url: <http://localhost:8080/hswm/api-docs>
