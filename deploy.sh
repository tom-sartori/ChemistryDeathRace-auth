mvn clean install -DskipTests
sam deploy -t target/sam.jvm.yaml -g
