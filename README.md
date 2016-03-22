# AsyncDebug
Servlets testing async dispatch after cross-context forward.
The AsyncDebug1 module contains a servlet that performs an async dispatch to a JSP within its own servlet context.
The AsyncDebug2 module contains a servlet that performs a request dispatcher forward to the AsyncDebug1 servlet.


# Instructions

1. build both modules AsyncDebug1 and AsyncDebug2 with 'mvn install'.
2. Deploy on Tomcat by copying the ./AsyncDebug1/target/Asyncdebug1.war and ./AsyncDebug2/target/AsyncDebug2.war files into the Tomcat webapps directory.
3. browse to "http://localhost:8080/AsyncDebug1/Debug" to access the AsyncDebug1 servlet directly.
4. browse to "http://localhost:8080/AsyncDebug2/Debug" to cause a cross-context forward from AsyncDebug2 to AsyncDebug1.


