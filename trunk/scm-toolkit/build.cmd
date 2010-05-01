@ECHO OFF

set HOME="C:\mywork\google-code\java-improve\trunk\scm-toolkit"

set JAVA_HOME="C:\Program Files\Java\jdk1.6.0_18"

set CLASSPATH=.;%JAVA_HOME%\lib\rt.jar
set CLASSPATH=%CLASSPATH%;%JAVA_HOME%\lib\tools.jar

set CLASSPATH=%CLASSPATH%;%HOME%\lib\ant.jar
set CLASSPATH=%CLASSPATH%;%HOME%\lib\ant-launcher.jar;

SET PATH=C:\notes;%PATH%
cd %HOME%
%JAVA_HOME%\bin\java -classpath %CLASSPATH% org.apache.tools.ant.Main -buildfile build.xml