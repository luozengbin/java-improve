@echo off
setlocal enabledelayedexpansion
echo *** process begin at %DATE% %TIME% ***
echo.
set TOOL_BASE=%~dp0\
rem set WORKSPACE=%~dp0\
cd %TOOL_BASE%
call %TOOL_BASE%\get_view_list.cmd
cd %WORKSPACE%
for /f "tokens=*" %%f in (%TOOL_BASE%\PRODUCE_VIEW.LIST) do call %TOOL_BASE%\get_view_script.cmd %%f
echo.
echo *** process end at %DATE% %TIME% ***
endlocal