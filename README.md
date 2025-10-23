# AoC-java

This project contains Java translations of my [Advent of Code solutions in JavaScript](https://github.com/IronBlood/AoC-js/), with the goal of exploring modern Java features.

## Prerequisites

- JDK 21
- Maven
- Input files (e.g., `src/main/resources/y2016_d01_input.txt`)

## Usage

```bash
# Scaffold files for a specific day
mvn -q exec:java -DskipTests -Dexec.mainClass="Main" -Dexec.args="2016-01 s"

# Run unit tests for a specific day
mvn test -Dtest=y2016.d01.SolutionTest

# Run unit all unit tests without MD5 hashing related
SKIP_MD5=1 mvn test

# Run a solution
mvn -q exec:java -DskipTests -Dexec.mainClass="Main" -Dexec.args="2016-01"
```

## License

MIT
