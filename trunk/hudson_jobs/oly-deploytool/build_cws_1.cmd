@echo off
setlocal enabledelayedexpansion

call "%~dp0\00_setenv.cmd"

set DEPLOY_LOG=%LOG_DIR%\%TODAY%_cws_1.log
if not exist "%LOG_DIR%" md "%LOG_DIR%"

if defined WORKSPACE (
  set PROJECT_BASE=%WORKSPACE%\cws
) else (
  set PROJECT_BASE=%TARGET_DIR%\cws
)


rem --------------------------------------------------
rem   process deploy (all of CustomWS projects)
rem --------------------------------------------------
echo *** deployment begin at %DATE% %TIME% ***
echo.
call "%ANT_HOME%\bin\ant" "-Dcustomws.project.dir=%PROJECT_BASE%" -f "%~dp0\build_cws_1.xml" %*
echo.
echo *** deployment end at %DATE% %TIME% ***

endlocal
