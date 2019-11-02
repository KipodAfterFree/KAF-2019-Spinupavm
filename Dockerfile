FROM openjdk:11-slim-buster
EXPOSE 3457
COPY flag.txt /flag.txt
COPY errorlog/cmake-build-debug/errorlog /errorlog
COPY build/libs/Spinupavm-1.0-runnable.jar /app.jar
CMD exec java -jar /app.jar