#!/bin/bash
set -e

if [ $# -ne 2 ]; then
  echo "Usage: $0 <new_version> <jig_version>"
  exit 1
fi

NEW_VERSION=$1
JIG_VERSION=$2

echo "Updating versions in pom.xml..."
sed -i '' "4s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom.xml
sed -i '' "24s|<version>.*</version>|<version>${JIG_VERSION}</version>|" pom.xml

echo "Updating versions in pom-irof.xml..."
sed -i '' "5s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom-irof.xml
sed -i '' "17s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom-irof.xml

echo "Running mvn clean deploy for pom.xml..."
./mvnw clean deploy -DskipTests

echo "Running mvn clean deploy for pom-irof.xml..."
./mvnw -f pom-irof.xml clean deploy -DskipTests

echo "Release complete!"
