１、build.propertiesファイルにログを見るエディタのパスを指定する
	例：editor.path=c:/progra~1/EmEditor/EmEditor.exe
	
２、ReloadLog.batにJAVA_HOMEを設定する
	例：set JAVA_HOME=c:\progra~1\java\jdk1.6.0_13

３、実行
	ReloadLog.bat {JSONLog | CwsLog | BPELLog} [取得したいローテーションファイルの数/tail行数]

	BPELログを見る
		例1：BPELログ取得し(2000行)、エディタで開く
			C:\mywork>ReloadLog.bat　BPELLog 2000

		例1：BPELログ取得し(5000行)、エディタで開く
			C:\mywork>ReloadLog.bat　BPELLog

	JSONログを見る
		例1：カレントJSONログ取得し、エディタで開く
			C:\mywork>ReloadLog.bat　JSONLog

		例2：古いJSONログを取得する、3世帯を取る
			C:\mywork>ReloadLog.bat　JSONLog 5
	CWSログを見る
		例1：カレントJSONログ取得し、エディタで開く
			C:\mywork>ReloadLog.bat　CwsLog

		例2：古いJSONログを取得する、3世帯を取る
			C:\mywork>ReloadLog.bat　CwsLog 3