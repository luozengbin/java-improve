SET LINESIZE      50
SET PAGESIZE      100
COL OBJECT_NAME   FORMAT A50
SET HEADING OFF
SET FEEDBACK OFF
SPOOL PRODUCE_VIEW.LIST
SELECT OBJECT_NAME FROM USER_OBJECTS WHERE OBJECT_TYPE = 'VIEW';
SPOOL OFF
QUIT