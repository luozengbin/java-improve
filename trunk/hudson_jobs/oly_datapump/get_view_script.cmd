@echo off
setlocal enabledelayedexpansion
copy %TOOL_BASE%\get_view_script.sql %TOOL_BASE%\tmp_work.sql
rpl -q "$VIEW_NAME$" %1 "%TOOL_BASE%\tmp_work.sql"
sqlplus produce/pr0duce@swld01.olympus.co.jp/swl.olympus.co.jp @%TOOL_BASE%\tmp_work.sql
rm -rf %TOOL_BASE%\tmp_work.sql
endlocal