# Use official Maven image with Eclipse Temurin JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy project configuration and dependency file
COPY pom.xml .

# Copy source code and resources
COPY src ./src
COPY testCase_TC_001.xml .
COPY cross_browser_tc_001.xml .
COPY grouping.xml .
COPY Test_Extent_report.xml .
COPY selenium_grid.xml .
COPY testData ./testData

# Default command to execute tests using your TestNG suite
CMD ["mvn", "clean", "test", "-Dsurefire.suiteXmlFiles=testCase_TC_001.xml"]