mkdir target
mkdir target/edu.school21.printer
javac -d target/edu.school21.printer src/java/edu.school21.printer/app/*.java src/java/edu.school21.printer/logic/*.java
java -classpath ./target/edu.school21.printer edu.school21.printer.app.Main "." "0" "/Users/fbarbera/Day04._JAR-0/it.bmp"
