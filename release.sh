#!/bin/bash
set -e

if [ $# -ne 2 ]; then
  echo "Usage: $0 <new_version> <jig_version>"
  exit 1
fi

NEW_VERSION=$1
JIG_VERSION=$2

echo "Updating versions in pom.xml..."
echo "New version: $NEW_VERSION"
echo "JIG version: $JIG_VERSION"

# Update project version
# XMLの構造を前提とした簡易的な置換を行っています
sed -i '' "4s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom.xml
# Update jig-core dependency version
# 行番号を指定して置換（pom.xmlの構造が変わると壊れる可能性があるため注意）
sed -i '' "24s|<version>.*</version>|<version>${JIG_VERSION}</version>|" pom.xml

echo "Running mvn clean deploy..."
./mvnw clean deploy -DskipTests

echo "Release complete!"
