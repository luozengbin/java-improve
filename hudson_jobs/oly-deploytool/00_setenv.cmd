set LOG_DIR=%~dp0\log
set TARGET_DIR=.\mono_pj
set ANT_OPTS=-Xmx256m
set FILENAME_DEPLOY_SKIP=_deploy_skip.txt

rem --------------------------------------------------
rem   Uncomment if you have GNU win32 package
rem     http://gnuwin32.sourceforge.net/
rem --------------------------------------------------
rem set GNUWIN32_HOME=c:\progra~1\gnuwin32
rem if defined GNUWIN32_HOME set PATH=%GNUWIN32_HOME%\bin;%PATH%

rem --------------------------------------------------
rem   Uncomment if you have MinGW package
rem     http://www.mingw.org/
rem --------------------------------------------------
rem set MINGW_HOME=c:\progra~1\mingw
rem if defined MINGW_HOME set PATH=%MINGW_HOME%\bin;%PATH%

rem --------------------------------------------------
rem   Uncomment if you have Cygwin package
rem     http://www.cygwin.com/
rem --------------------------------------------------
rem set CYGWIN_HOME=c:\progra~1\cygwin
rem if defined CYGWIN_HOME set PATH=%CYGWIN_HOME%\bin;%PATH%

rem --------------------------------------------------
rem   Java Environment (jdk6)
rem --------------------------------------------------
set ANT_HOME=C:\mywork\toolkit\jdevstudio10134\ant
set JDEVEL_HOME=C:\mywork\toolkit\jdevstudio10134
set JDK6_HOME=c:\progra~1\java\jdk1.6.0_18
set JDK5_HOME=c:\progra~1\java\1.5.0_22

set JAVA_HOME=%JDK6_HOME%
set BPEL_HOME=%JDEVEL_HOME%\integration\bpel
set PATH=%~dp0\bin;%PATH%;lib\libsvnjavahl-1.dll
set CLASSPATH=%CLASSPATH%;lib\libsvnjavahl-1.dll

rem --------------------------------------------------
rem   get current date and time
rem --------------------------------------------------
set _T1=%DATE:/=%_%TIME::=%
set _T2=%_T1:~0,15%
set TODAY=%_T2: =0%

nbtstat -R 2>&1 > nul
