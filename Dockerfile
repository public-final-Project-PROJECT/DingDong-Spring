FROM eclipse-temurin:17
RUN mkdir /opt/app
COPY build/libs/lastdance_s-0.0.1-SNAPSHOT.jar /opt/app/dingdong_app.jar
CMD ["java", "-jar", "/opt/app/dingdong_app.jar"]
EXPOSE 3013