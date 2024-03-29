FROM eclipse-temurin:17-jdk as builder
WORKDIR /app
COPY . /app/.
RUN ./gradlew clean build

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/*.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/app/*.jar"]