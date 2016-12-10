FROM java:8
copy javaagent /usr/src/appdynamics/javaagent
COPY poc-web-environment.properties /usr/src/poc-web-environment.properties
COPY microservice-demo-web-0.0.1-SNAPSHOT.jar /usr/src/microservice-demo-web-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src
CMD java -javaagent:"/usr/src/appdynamics/javaagent/javaagent.jar" -Dconfig.home="/usr/src" -Dappdynamics.agent.applicationName="poc-web-service" -Dappdynamics.agent.tierName="poc-web-service-tier" -Dappdynamics.agent.nodeName="poc-web-service-node" -jar microservice-demo-web-0.0.1-SNAPSHOT.jar