@echo off
setlocal enabledelayedexpansion
sqlplus produce/pr0duce@swld01.olympus.co.jp/swl.olympus.co.jp @%TOOL_BASE%\get_view_list.sql
rpl -q " " "" %TOOL_BASE%\PRODUCE_VIEW.LIST
endlocal