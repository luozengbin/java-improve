@echo off
setlocal enabledelayedexpansion

if "%~1" == "" (
  echo "Usage: %0 {BPEL-Cmp|BPEL-Req} [clean]"
  exit /b 1
)

rem call "%~dp0\00_setenv.cmd"

rem --------------------------------------------------
rem   BPEL project base directory
rem --------------------------------------------------
if defined WORKSPACE (
  rem set PROJECT_BASE=%WORKSPACE%\mono_bpel_devlOpen\%~1
  set PROJECT_BASE=C:\mywork\bpel_esb_modify\
) else (
  set PROJECT_BASE=%TARGET_DIR%\mono_bpel_devlOpen\%~1
)

rem --------------------------------------------------
rem   java classpath environment
rem --------------------------------------------------
set BASE_OB_CLASSPATH=%JAVA_HOME%\lib\tools.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\connector15.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\orabpel-common.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\orabpel-thirdparty.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\orabpel-exts.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\orabpel.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\orabpel-ant.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%ANT_HOME%\lib\ant-launcher.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%ANT_HOME%\lib\ant.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\oracle_http_client.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\lib\xmlparserv2.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\lib\dms.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\rdbms\jlib\aqapi.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\webservices\lib\orawsdl.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\bpm-infra.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\integration\lib\bpm-services.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\jlib\jewt4.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\jlib\share.jar
set BASE_OB_CLASSPATH=%BASE_OB_CLASSPATH%;%JDEVEL_HOME%\jlib\regexp.jar

set JAVA_CLASSPATH=%JAVA_HOME%\jre\lib\rt.jar

set BOOT_LIBS=%JDEVEL_HOME%\integration\lib\orabpel-boot.jar
set BOOT_LIBS=%BOOT_LIBS%;%JDEVEL_HOME%\integration\lib\connector15.jar

set OBDK_CLASSPATH=%JAVA_CLASSPATH%
set OBDK_CLASSPATH=%OBDK_CLASSPATH%;%BASE_OB_CLASSPATH%
set OBDK_CLASSPATH=%OBDK_CLASSPATH%;%~dp0\lib\j2ee.jar

set OB_CLASSPATH=%BASE_OB_CLASSPATH%

set CLIENT_CLASSPATH=%JDEVEL_HOME%\j2ee\home\lib\adminclient.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\lib\javax77.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\lib\oc4j-unsupported-api.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\lib\jmxri.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\lib\oc4j-internal.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\oc4jclient.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\rmic.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\j2ee\home\oc4j.jar
set CLIENT_CLASSPATH=%CLIENT_CLASSPATH%;%JDEVEL_HOME%\lib\xml.jar

set OBANT_CLASSPATH=%OBANT_CLASSPATH%;%JDEVEL_HOME%\adfp\lib\xalan-j_2_7_0\serializer.jar
set OBANT_CLASSPATH=%OBANT_CLASSPATH%;%JDEVEL_HOME%\adfp\lib\xalan-j_2_7_0\xalan.jar
set OBANT_CLASSPATH=%OBANT_CLASSPATH%;%OBDK_CLASSPATH%
set OBANT_CLASSPATH=%OBANT_CLASSPATH%;%CLIENT_CLASSPATH%
set OBANT_CLASSPATH=%OBANT_CLASSPATH%;%~dp0\lib\jp.co.olympus.soa.mono.bpel.xpath.jar

set ANT_ARGS="-Dhome=%BPEL_HOME%" "-Dbpel.home=%BPEL_HOME%" "-Dant-orabpel.dir=%BPEL_HOME%\utilities" -Dapps=.
set ANT_OPTS=-Xms128m -Xmx512m "-Xbootclasspath/p:%BOOT_LIBS%" "-Dhome=%BPEL_HOME%"

rem --------------------------------------------------
rem   Go on deployment
rem --------------------------------------------------
if not exist "%PROJECT_BASE%" (
  echo Error: file not found %PROJECT_BASE%
  exit /b 2
)

set MODULES_POSTPONE=
set MODULES_FAILED=
set RESULT=0

if "%~2" == "clean" (
  rem --------------------------------------------------
  rem   clean up output (all of BPEL projects)
  rem --------------------------------------------------
  for /f %%t in ('dir /b /a:d-h %PROJECT_BASE%\*') do (
    if exist "%PROJECT_BASE%\%%~nt\output"                 rmdir /s /q "%PROJECT_BASE%\%%~nt\output"
    if exist "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%" del /f /q "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%"
  )
) else (
  rem --------------------------------------------------
  rem   prepare (all of BPEL projects)
  rem --------------------------------------------------
  for /f %%t in ('dir /b /a:d-h %PROJECT_BASE%\*') do (
    if exist "%PROJECT_BASE%\%%~nt\output"                 rmdir /s /q "%PROJECT_BASE%\%%~nt\output"
    if exist "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%" del /f /q "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%"
  )
  rem --------------------------------------------------
  rem   process deploy (all of BPEL projects)
  rem --------------------------------------------------
  echo *** deployment begin at %DATE% %TIME% ***
  echo.

  for /f %%t in ('dir /b /a:d-h %PROJECT_BASE%\*') do (
    set _SKIP_=

    if not "%%~nt" == "" (
      if not exist "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%" (
        rem if "%%~nt" == "UpdateDeliSpecCompBPELV1" (
        rem   if not exist "%PROJECT_BASE%\UpdateWindchillAuthCompBPELV1\%FILENAME_DEPLOY_SKIP%" (
        rem     set _SKIP_=1
        rem     set MODULES_POSTPONE=!MODULES_POSTPONE! %%~nt
        rem   )
        rem)

        if not defined _SKIP_ (
          "%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath "%OBANT_CLASSPATH%" "-Dant.home=%ANT_HOME%" org.apache.tools.ant.Main %ANT_ARGS% "-Dbpel.project.dir=%PROJECT_BASE%\%%~nt" -f "%~dp0\build_bpel.xml" %2 %3 %4 %5 %6 %7 %8 %9

          if not errorlevel 1 (
            echo 1 > "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%"
          ) else (
            set /A RESULT = !RESULT! + 1
            set MODULES_FAILED=!MODULES_FAILED! %%~nt
          )
        )
      )
    )
  )

  if defined MODULES_POSTPONE (
    for %%t in (!MODULES_POSTPONE!) do (
      if "%%~t" == "UpdateDeliSpecCompBPELV1" (
        if exist "%PROJECT_BASE%\UpdateWindchillAuthCompBPELV1\%FILENAME_DEPLOY_SKIP%" (
          "%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath "%OBANT_CLASSPATH%" "-Dant.home=%ANT_HOME%" org.apache.tools.ant.Main %ANT_ARGS% "-Dbpel.project.dir=%PROJECT_BASE%\%%~t" -f "%~dp0\build_bpel.xml" %2 %3 %4 %5 %6 %7 %8 %9

          if not errorlevel 1 (
            echo 1 > "%PROJECT_BASE%\%%~t\%FILENAME_DEPLOY_SKIP%"
          ) else (
            set /A RESULT = !RESULT! + 1
            set MODULES_FAILED=!MODULES_FAILED! %%~t
          )
        )
      )
    )
  )

  if defined MODULES_FAILED (
    echo.
    echo *** Modules deployment failed ... ***
    echo.
    for %%t in (!MODULES_FAILED!) do echo %%~t
  )

  echo.
  echo *** deployment end at %DATE% %TIME% ***
)

exit /b !RESULT!

endlocal
