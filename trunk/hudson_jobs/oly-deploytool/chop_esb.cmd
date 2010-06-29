@echo off
setlocal enabledelayedexpansion

if "%~1" == "" (
  echo "Usage: %0 {ESB-Rtg|ESB-Soap} [clean]"
  exit /b 1
)

rem call "%~dp0\00_setenv.cmd"

rem --------------------------------------------------
rem   ESB project base directory
rem --------------------------------------------------
if defined WORKSPACE (
  set PROJECT_BASE=%WORKSPACE%\mono_esb_devlOpen\%~1
) else (
  set PROJECT_BASE=%TARGET_DIR%\mono_esb_devlOpen\%~1
)

if not exist "%PROJECT_BASE%" (
  echo Error: file not found %PROJECT_BASE%
  exit /b 1
)

set ANT_OPTS=%ANT_OPTS% "-Dant.home=%ANT_HOME%" -DESB_PLATFORM=weblogic_8
set MODULES_FAILED=
set RESULT=0

if "%~2" == "clean" (
  rem --------------------------------------------------
  rem   clean up outout (all of ESB projects)
  rem --------------------------------------------------
  for /f %%t in ('dir /b /a:d-h %PROJECT_BASE%\*') do (
    if exist "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%" del /f /q "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%"
    if exist "%PROJECT_BASE%\%%~nt\deploylog.txt"          del /f /q "%PROJECT_BASE%\%%~nt\deploylog.txt"
    if exist "%PROJECT_BASE%\%%~nt\*%%~nt.zip"             del /f /q "%PROJECT_BASE%\%%~nt\*%%~nt.zip"
  )
) else if "%~2" == "undeploy" (
  rem --------------------------------------------------
  rem   process undeploy (all of ESB projects)
  rem --------------------------------------------------
  echo *** undeployment begin at %DATE% %TIME% ***
  echo.

  for /f %%t in ('dir /b /a:d-h "%PROJECT_BASE%\*"') do (
    if not "%%~nt" == "" (
      for /f %%G in ('call "%~dp0\get_esb_guid.cmd" "%PROJECT_BASE%\%%~nt"') do (
        "%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath "%ANT_HOME%\lib\ant-launcher.jar;%ANT_HOME%\lib\ant.jar" org.apache.tools.ant.Main %ANT_ARGS% "-Desb.guid.service=%%G" "-Desb.project.dir=%PROJECT_BASE%\%%~nt" -f "%~dp0\build_esb.xml" undeploy

        if errorlevel 1 (
          set /A RESULT = !RESULT! + 1
          set MODULES_FAILED=!MODULES_FAILED! %%~nt
        )
      )
    )
  )

  if defined MODULES_FAILED (
    echo.
    echo *** Modules undeployment failed ... ***
    echo.
    for %%t in (!MODULES_FAILED!) do echo %%~t
  )

  echo.
  echo *** undeployment end at %DATE% %TIME% ***

) else (
  rem --------------------------------------------------
  rem   process deploy (all of ESB projects)
  rem --------------------------------------------------
  echo *** deployment begin at %DATE% %TIME% ***
  echo.

  for /f %%t in ('dir /b /a:d-h "%PROJECT_BASE%\*"') do (
    if not "%%~nt" == "" (
      if not exist "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%" (
        "%JAVA_HOME%\bin\java" %ANT_OPTS% -classpath "%ANT_HOME%\lib\ant-launcher.jar;%ANT_HOME%\lib\ant.jar" org.apache.tools.ant.Main %ANT_ARGS% "-Desb.project.dir=%PROJECT_BASE%\%%~nt" -f "%~dp0\build_esb.xml" deploy

        if not errorlevel 1 (
          echo 1 > "%PROJECT_BASE%\%%~nt\%FILENAME_DEPLOY_SKIP%"
        ) else (
          set /A RESULT = !RESULT! + 1
          set MODULES_FAILED=!MODULES_FAILED! %%~nt
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

exit /b %RESULT%

endlocal
