#!/bin/bash
set -e

if [ $# -ne 1 ]; then
  echo "Usage: $0 <new_version>"
  exit 1
fi

NEW_VERSION=$1
JIG_VERSION=$1

echo "Updating versions in pom.xml..."
sed -i '' "6s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom.xml
sed -i '' "25s|<version>.*</version>|<version>${JIG_VERSION}</version>|" pom.xml

echo "Updating versions in pom-irof.xml..."
sed -i '' "6s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom-irof.xml
sed -i '' "17s|<version>.*</version>|<version>${NEW_VERSION}</version>|" pom-irof.xml

