(24Jan2010 QNES������ �L)

�r���h&�f�v���C�p�X�N���v�g�̎g����


1. �K�v�ȃt�@�C���A�\�t�g�E�F�A

  * JDK6.0
  * Jdeveloper10g (10.1.3.4.0�A�T�[�o�Ɠ���o�[�W����)
  * Apache Ant 1.6.5 or later
  * oly-deploytool-bin-YYYYMMDD_HHMM.zip
  * oly-deploytool-dep-YYYYMMDD_HHMM.zip

2. ���ݒ�

  ���ɍ��킹�Đݒ����ύX����B
    00_setenv.cmd       <-- jdk, jdeveloper, ant�Ȃ�
    build.properties    <-- �T�[�o���Ȃ�

3. �\�[�X�R�[�h����

  SVN����\�[�X��export���Ď��o��
  �z��f�B���N�g���\���́A�ȉ��̂Ƃ���B

    mono_pj
       |
       +--- mono_bpel_devlOpen
       +--- mono_cws_devlOpen
       +--- mono_esb_devlOpen
       +--- mono_json_devlOpen
       +--- mono_xpath_devlOpen

  �f�B���N�g���\�����قȂ�ꍇ�́A�o�b�`�t�@�C���̎w��
  PROJECT_BASE��ύX���邩�A�V���Ɋ��ϐ�WORKSPACE��
  ��`����΂悢�B
  (�������AWORKSPACE�̓r���h�Ώۖ��ɕύX����K�v����)

4. �r���h����уf�v���C

  4.1 CustomWS

   �g����: build_cws [package [deploy|undeploy]]

    WAR�t�@�C�����쐬�������ꍇ
      DOS> build_cws package

    �f�v���C�������ꍇ
      DOS> build_cws undeploy deploy

  4.2 Json

   �g����: build_json [package [deploy|undeploy]]

    WAR�t�@�C�����쐬�������ꍇ
      DOS> build_json package

    �f�v���C�������ꍇ
      DOS> build_json undeploy deploy

  4.3 Xpath

   �g����: build_xpath [package]

    JAR�t�@�C�����쐬�������ꍇ
      DOS> build_xpath package

  4.4 ESB

   �g����: build_esb {esb-soap|esb-rtg} [clean|deploy|undeploy]

    ESB-Soap���f�v���C�������ꍇ
      DOS> build_esb esb-soap

      �f�v���C����������ESB�́A�ăf�v���C���Ȃ��悤�ɉ��H
      ���Ă��邽�߁A��Ԃ��N���A����������clean�I�v�V����
      �ŏ���������΂悢�B

    ESB-Rtg���A���f�v���C�������ꍇ
      DOS> build_esb esb-rtg undeploy

  4.5 BPEL

   �g����: build_bpel {bpel-cmp|bpel-req} [clean|deploy]

    BPEL-Cmp���f�v���C�������ꍇ
      DOS> build_bpel bpel-cmp

      �f�v���C����������ESB�́A�ăf�v���C���Ȃ��悤�ɉ��H
      ���Ă��邽�߁A��Ԃ��N���A����������clean�I�v�V����
      �ŏ���������΂悢�B

5. ���O�o��

  log�f�B���N�g���Ɏ��s���̃��O���̎悷��B
  �K�v�ɉ����ē��e�m�F�̂��ƁB

