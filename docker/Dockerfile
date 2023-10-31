FROM bellsoft/liberica-openjdk-alpine-musl:17
COPY /target/library-*.jar /library/library.jar
ENTRYPOINT ["java", "-jar", "/library/library.jar"]