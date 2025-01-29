FROM amazoncorretto:21

WORKDIR /risk-engine

COPY gradle /risk-engine/gradle
COPY gradlew /risk-engine/gradlew
COPY build.gradle /risk-engine/build.gradle
COPY settings.gradle /risk-engine/settings.gradle
COPY src /risk-engine/src

RUN ./gradlew clean build

COPY build/libs/riskengine-0.0.1.jar /risk-engine/risk-engine.jar

ENTRYPOINT ["java", "-jar", "/risk-engine/risk-engine.jar"]

EXPOSE 8080
