#!/bin/sh
echo '\033[0;36m mvn package\033[0;39m'
mvn package
echo '\033[0;36m java . . .\033[0;39m'
sudo /home/silas/.jdks/openjdk-20.0.2/bin/java -javaagent:/home/silas/.cache/JetBrains/RemoteDev/dist/5b92388135cf0_ideaIU-2023.2.1/lib/idea_rt.jar=38281:/home/silas/.cache/JetBrains/RemoteDev/dist/5b92388135cf0_ideaIU-2023.2.1/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/silas/projects/java/java-htmx/target/classes:/home/silas/.m2/repository/org/xerial/sqlite-jdbc/3.43.0.0/sqlite-jdbc-3.43.0.0.jar org.example.Main
