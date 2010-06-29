@echo off
setlocal enabledelayedexpansion
set JAVA_HOME=c:\progra~1\java\jdk1.6.0_18

if "%~1" == "" (
  echo "Usage: %0 {JSONLog | CwsLog | CwsErrorLog | BPELLog} [取得したいローテーションファイルの数/tail行数]"
  exit /b 1
)
set CLASSPATH=.;%JAVA_HOME%\lib\rt.jar
set CLASSPATH=%CLASSPATH%;%JAVA_HOME%\lib\tools.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\ant.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\ant-commons-net.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\ant-launcher.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\ant-contrib-1.0b3.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\ant-jsch.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\commons-net-2.0.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\commons-net-ftp-2.0.jar
set CLASSPATH=%CLASSPATH%;%~dp0\lib\jsch-0.1.42.jar

if "%1" == "BPELLog" (
	if "%2" == "" (
		%JAVA_HOME%\bin\java -classpath %CLASSPATH% org.apache.tools.ant.Main %1 -Dworkspace.dir="%WORKSPACE%" -Dtarget.line.num=5000	
	)else (
		%JAVA_HOME%\bin\java -classpath %CLASSPATH% org.apache.tools.ant.Main %1 -Dworkspace.dir="%WORKSPACE%" -Dtarget.line.num=%2%
	)
)else (
	if "%~2" == "" (
		%JAVA_HOME%\bin\java -classpath %CLASSPATH% org.apache.tools.ant.Main %1 -Dworkspace.dir="%WORKSPACE%" -Dfile.extention=.log
	)else (
		for /L %%i in (1,1,%2) do %JAVA_HOME%\bin\java -classpath %CLASSPATH% org.apache.tools.ant.Main %1 -Dworkspace.dir=%WORKSPACE% -Dfile.extention=.log.%%i
	)
)
rem PAUSE
exit /b %RESULT%
endlocal