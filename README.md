#Tutorial for Spring Rest with Mongo DB (not using XML configuration)

Tested with:

* Java 7
* Tomcat 7.0.59

### To run using the tomcat-plugin:
> ./gradlew tomcatRun

To do a quick test if the app is running:

* <http://localhost:8080/hswm/hello>

##### Note for IntelliJ IDEA user: if it looks like the Lombok annotations are not working, check the following in the preferences:

* Compiler > Annotation Processors > check ''Enable annotation processing'
* Lombok plugin > check 'Enable Lombok support for this project'