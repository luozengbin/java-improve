@echo off
setlocal

echo init environment
set WORK_DIR=C:\mywork\toolkit\oly_scm_support
set SENDMAIL=%WORK_DIR%
set TARGET_DIR=C:\mywork\toolkit\oly_svn_bpel_esb
set DATE_AND_TIME=%DATE:/=%_%TIME::=%
set _TODAY=%DATE_AND_TIME:~0,15%
set TODAY=%_TODAY: =0%
set path=C:\Perl\bin;%path%
set CLASSPATH=%SENDMAIL%\lib\commons-codec-1.3.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\commons-collections-3.2.1.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\commons-lang-2.5.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\commons-logging-1.1.1.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\dsn.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\imap.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\log4j-1.2.15.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\mailapi.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\pop3.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\smtp.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\velocity-1.5.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\scm-sendmail.jar;%CLASSPATH%
set CLASSPATH=%SENDMAIL%\lib\scm-validation.jar;%CLASSPATH%

echo cleaning...
rm -rf velocity.log
rm -rvf %WORK_DIR%\result
rm -rvf %WORK_DIR%\work
mkdir %WORK_DIR%\result
mkdir %WORK_DIR%\work

echo update source from repository
pushd "%TARGET_DIR%"
svn update --username nec009 --password pronec009 
rm -vrf BPEL-CMP\01buildant
rm -vrf BPEL-REQ\01buildant
rm -vrf datamodel\01buildant
mv -v datamodel\.svn\text-base\build_*.xml.svn-base %WORK_DIR%\work
rm -vrf datamodel\build_*.xml
rm -vrf ESB-RTG\01buildant
rm -vrf ESB-SOAP\01buildant
rm -vrf heartbeat-BPEL-Watch
rm -vrf heartbeat-ESB-Watch
echo grep illegal url in %TARGET_DIR%
%WORK_DIR%\grep -rniE "(\/10\.[0-9]+\.[0-9]+\.[0-9]+[^\.]|qnes|\.nec\.co\.jp|:7777|:8888|swld01:|soad01:|soat|soap01|soae01|mct0|mcp0|soad01\.is\.olympus\.global:|CustomWS-NECUT)" * > "%WORK_DIR%\result\%TODAY%_oly_check_illegal_url.txt
mv -v %WORK_DIR%\work\build_*.xml.svn-base datamodel\.svn\text-base
popd

echo grep neglect source file in %TARGET_DIR%
pushd %WORK_DIR%
rem for xsl mapper files
perl chk_bpel_xsl.pl %TARGET_DIR% > result\%TODAY%_oly_check_list_bpel.txt

rem for xsl common xml mapper files
perl chk_lookup_xml.pl %TARGET_DIR% > result\%TODAY%_oly_check_list_datamodel.txt
rpl -q "C:\mywork\toolkit\oly_svn_bpel_esb" "" result\%TODAY%_oly_check_list_bpel.txt

rem check illegal preferredPort in bpel.xml files
echo validation preferredPort defination on bpel.xml
perl chk_preferredPort.pl %TARGET_DIR% > work\bpel_xml_list.txt
touch result\%TODAY%_oly_check_preferredPort.txt
java -cp %CLASSPATH% scm.validation.PreferredPortValidator work\bpel_xml_list.txt result\%TODAY%_oly_check_preferredPort.txt %SENDMAIL%\config.properties
rpl -q "C:\mywork\toolkit\oly_svn_bpel_esb" "" result\%TODAY%_oly_check_preferredPort.txt

rem check DefaultSystem_IESoap.esbsvc
echo validation operation defination on DefaultSystem_IESoap.esbsvc
touch result\%TODAY%_oly_check_IESoapESB.txt
java -cp %CLASSPATH% scm.validation.IESoapValidator %TARGET_DIR%\datamodel\soa\InterfaceComponents\ServiceLibrary\Mono\SimpleTaskDispatcher.wsdl %TARGET_DIR%\ESB-SOAP\IESoapESB\DefaultSystem_IESoap.esbsvc result\%TODAY%_oly_check_IESoapESB.txt

echo sending mail with java client
java -cp %CLASSPATH% scm.alert.Main %SENDMAIL%\config.properties illegal_url result\%TODAY%_oly_check_illegal_url.txt
java -cp %CLASSPATH% scm.alert.Main %SENDMAIL%\config.properties commit_neglect result\%TODAY%_oly_check_list_bpel.txt,result\%TODAY%_oly_check_list_datamodel.txt
java -cp %CLASSPATH% scm.alert.Main %SENDMAIL%\config.properties illegal_preferredPort result\%TODAY%_oly_check_preferredPort.txt
java -cp %CLASSPATH% scm.alert.Main %SENDMAIL%\config.properties illegal_esbsvc result\%TODAY%_oly_check_IESoapESB.txt

popd
endlocal