FROM openjdk:17-jdk-slim as builder
WORKDIR /app
COPY . /app/.
RUN ./gradlew clean build -x test

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/*.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/app/*.jar"]