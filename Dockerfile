FROM openjdk:11-slim-buster
EXPOSE 8000
COPY flag.txt /flag.txt
COPY errorlog.sh /bin/errorlog
COPY build/libs/Spinupavm-1.0-runnable.jar /app.jar
RUN chmod 777 /bin/errorlog
RUN chmod 555 /flag.txt
RUN chmod 555 /app.jar
RUN useradd nopermissions
CMD su nopermissions -c "exec java -jar /app.jar"