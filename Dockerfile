FROM tomcat:9.0.50
EXPOSE 8080:8080
COPY ./build/libs/ktor-cap12-1.2.war/ /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat/bin
CMD ["catalina.sh", "run"]
