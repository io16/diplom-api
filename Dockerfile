FROM maven:3.5.3-jdk-10-slim AS build

# cache dependencies downloading
COPY pom.xml /src/
COPY util/pom.xml /src/util/
COPY core/pom.xml /src/core/
COPY db/pom.xml /src/db/
COPY rest/pom.xml /src/rest/
COPY jwt/pom.xml /src/jwt/
COPY main/pom.xml /src/main/
WORKDIR /src
RUN mvn dependency:go-offline test

# build
COPY . /src
RUN mvn package

# TODO: extract jar and copy to runtime image: 1) do not spend time for unpacking, and 2) avoid troubles with resource file reading while in a jar


FROM openjdk:10-jre-slim AS runtime

COPY --from=build /src/main/target/*-fat.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
