�P�Abuild.properties�t�@�C���Ƀ��O������G�f�B�^�̃p�X���w�肷��
	��Feditor.path=c:/progra~1/EmEditor/EmEditor.exe
	
�Q�AReloadLog.bat��JAVA_HOME��ݒ肷��
	��Fset JAVA_HOME=c:\progra~1\java\jdk1.6.0_13

�R�A���s
	ReloadLog.bat {JSONLog | CwsLog | BPELLog} [�擾���������[�e�[�V�����t�@�C���̐�/tail�s��]

	BPEL���O������
		��1�FBPEL���O�擾��(2000�s)�A�G�f�B�^�ŊJ��
			C:\mywork>ReloadLog.bat�@BPELLog 2000

		��1�FBPEL���O�擾��(5000�s)�A�G�f�B�^�ŊJ��
			C:\mywork>ReloadLog.bat�@BPELLog

	JSON���O������
		��1�F�J�����gJSON���O�擾���A�G�f�B�^�ŊJ��
			C:\mywork>ReloadLog.bat�@JSONLog

		��2�F�Â�JSON���O���擾����A3���т����
			C:\mywork>ReloadLog.bat�@JSONLog 5
	CWS���O������
		��1�F�J�����gJSON���O�擾���A�G�f�B�^�ŊJ��
			C:\mywork>ReloadLog.bat�@CwsLog

		��2�F�Â�JSON���O���擾����A3���т����
			C:\mywork>ReloadLog.bat�@CwsLog 3