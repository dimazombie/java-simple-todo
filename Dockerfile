FROM alpine/git
WORKDIR /app
RUN git clone https://github.com/joffrua/java-simple-todo.git

FROM maven:3.6.3-openjdk-14-slim
WORKDIR /app
COPY --from=0 /app/java-simple-todo /app
RUN mvn install

FROM node:14-alpine
WORKDIR /app/webapp
COPY --from=1 /app/webapp /app/webapp
RUN npm i webapp

FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
WORKDIR /app
COPY --from=1 /app/target/java-simple-todo-1.0-SNAPSHOT-jar-with-dependencies.jar /app
COPY --from=2 /app/webapp /app/webapp
CMD ["java", "-jar", "/app/java-simple-todo-1.0-SNAPSHOT-jar-with-dependencies.jar"]