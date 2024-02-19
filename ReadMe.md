# Cron Expression Parser

This is a simple command-line application to parse cron schedule expressions and expand them to show the times at which they will run.

## Usage

The application accepts a single cron expression as an argument:

```
java CronParseApplication
"*/15 0 1,15 * 1-5 /usr/bin/script"
```

Example output:

```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/script
```

## Cron Expression Format

The application supports parsing cron schedule expressions with the following fields:

- Minute - 0-59 or */increment or "," value list separator or - range or /	step values
- Hour - 0-23 or */increment or "," value list separator or - range or /	step values
- Day of month - 1-31 or */increment or "," value list separator or - range or /	step values
- Month - 1-12 or */increment or "," value list separator or - range or /	step values
- Day of week - 0-6 or */increment or "," value list separator or - range or /	step values
- Command - script/command to run

## Implementation

The application is implemented in Java. The key components are:

- CronParserApplication - Main class that parses the input and prints output
- CronSchedule - Model class that represents a parsed cron schedule
- CronParseService - Parser service that splits and parses each field

The CronParseService uses regular expressions and logic to handle increments, ranges, lists, wildcards etc. JUnit tests are included to validate parsing and output.

## Compiling the Application

## Compile and Package Using Maven

To compile and package the application using Maven, navigate to the project root directory and run:

```
mvn clean compile
mvn package
```

This will compile the Java files, run any tests, and package the compiled classes into a JAR file within the target directory like below :

Building jar: target/CronParser-1.0-SNAPSHOT.jar

Get the JAR file name.

## Running Application

Running the application using JAR

After packaging with Maven,  run the application using the following command:

```
java -jar <put-jar-file-name>.jar 
<enter expression> 

Example : 
java -jar target/CronParser-1.0-SNAPSHOT.jar
"*/15 0 1,15 * 1-5 /usr/bin/script"
```


## Compile Using Java Command
If maven is not available, compile the files manually using the javac command.

First, 

Create the target/classes directory in root directory if it doesn't exist:
```
mkdir -p target/classes
```
Compile the .java files:
```
javac -d target/classes src/main/java/org/example/*.java src/main/java/org/example/model/*.java src/main/java/org/example/service/*.java
```

Then  run the application as mentioned earlier using the -cp (classpath) option:
```
java -cp target/classes org.example.CronParseApplication 
<enter the expression>   

Example for expression : "*/15 0 1,15 * 1-5 /usr/bin/script"

