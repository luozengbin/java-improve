@echo off
setlocal enabledelayedexpansion

rem call "%~dp0\00_setenv.cmd"

if defined WORKSPACE (
  set PROJECT_BASE=%WORKSPACE%\mono_xpath_devlOpen
) else (
  set PROJECT_BASE=%TARGET_DIR%\mono_xpath_devlOpen
)

if not exist "%PROJECT_BASE%" (
  echo Error: file not found %PROJECT_BASE%
  exit /b 1
)

rem --------------------------------------------------
rem   process deploy (all of XPath projects)
rem --------------------------------------------------
echo *** deployment begin at %DATE% %TIME% ***
echo.
call "%ANT_HOME%\bin\ant" "-Dxpath.project.dir=%PROJECT_BASE%" -f "%~dp0\build_xpath.xml" %*
echo.
echo *** deployment end at %DATE% %TIME% ***

endlocal
