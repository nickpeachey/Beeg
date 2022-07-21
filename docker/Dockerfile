FROM maven:3-openjdk-17-slim as build

WORKDIR /build

COPY pom.xml pom.xml
COPY Domain/pom.xml Domain/pom.xml
COPY BeegApi/pom.xml BeegApi/pom.xml
COPY Services/pom.xml Services/pom.xml
COPY Application/pom.xml Application/pom.xml

RUN mvn -q -ntp -B -pl Domain -am dependency:go-offline
COPY Domain Domain

RUN mvn -q -B -pl Domain install

RUN mvn -q -ntp -B -pl Services -am dependency:go-offline
COPY Services Services

RUN mvn -q -B -pl Services install

RUN mvn -q -ntp -B -pl Application -am dependency:go-offline
COPY Application Application

RUN mvn -q -B -pl Application install

RUN mvn -q -B -pl BeegApi -am dependency:go-offline

COPY BeegApi BeegApi

RUN mvn -q -ntp -B -pl Domain,BeegApi,Services,Application package

RUN mkdir -p /jar-layers
WORKDIR /jar-layers

RUN java -Djarmode=layertools -jar /build/BeegApi/target/*.jar extract

#FROM adoptopenjdk/openjdk17:centos-jre
FROM maven:3-openjdk-17-slim
RUN mkdir -p /app

WORKDIR /app

COPY --from=build /jar-layers/dependencies/ ./
COPY --from=build /jar-layers/spring-boot-loader/ ./
COPY --from=build /jar-layers/snapshot-dependencies/ ./
COPY --from=build /jar-layers/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]