mkdir target
mkdir target/resources
cp src/resources/it.bmp target/resources
javac -d target/ src/java/edu.school21.printer/app/*.java src/java/edu.school21.printer/logic/*.java
jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target/ .
cd target
java -jar images-to-chars-printer.jar "." "0"
