FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /lds
WORKDIR /lds
COPY ./ ./
RUN ls -la
RUN mvn -B -f pom.xml clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /lds/lds-service/target/lds-service-*.jar lds-service.jar
EXPOSE 40200
ENTRYPOINT ["java", "-jar", "lds-service.jar"]
