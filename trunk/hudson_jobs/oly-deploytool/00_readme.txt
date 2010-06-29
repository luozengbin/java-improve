(24Jan2010 QNESおがわ 記)

ビルド&デプロイ用スクリプトの使い方


1. 必要なファイル、ソフトウェア

  * JDK6.0
  * Jdeveloper10g (10.1.3.4.0、サーバと同一バージョン)
  * Apache Ant 1.6.5 or later
  * oly-deploytool-bin-YYYYMMDD_HHMM.zip
  * oly-deploytool-dep-YYYYMMDD_HHMM.zip

2. 環境設定

  環境に合わせて設定情報を変更する。
    00_setenv.cmd       <-- jdk, jdeveloper, antなど
    build.properties    <-- サーバ情報など

3. ソースコード入手

  SVNからソースをexportして取り出す
  想定ディレクトリ構造は、以下のとおり。

    mono_pj
       |
       +--- mono_bpel_devlOpen
       +--- mono_cws_devlOpen
       +--- mono_esb_devlOpen
       +--- mono_json_devlOpen
       +--- mono_xpath_devlOpen

  ディレクトリ構成が異なる場合は、バッチファイルの指定
  PROJECT_BASEを変更するか、新たに環境変数WORKSPACEを
  定義すればよい。
  (ただし、WORKSPACEはビルド対象毎に変更する必要あり)

4. ビルドおよびデプロイ

  4.1 CustomWS

   使い方: build_cws [package [deploy|undeploy]]

    WARファイルを作成したい場合
      DOS> build_cws package

    デプロイしたい場合
      DOS> build_cws undeploy deploy

  4.2 Json

   使い方: build_json [package [deploy|undeploy]]

    WARファイルを作成したい場合
      DOS> build_json package

    デプロイしたい場合
      DOS> build_json undeploy deploy

  4.3 Xpath

   使い方: build_xpath [package]

    JARファイルを作成したい場合
      DOS> build_xpath package

  4.4 ESB

   使い方: build_esb {esb-soap|esb-rtg} [clean|deploy|undeploy]

    ESB-Soapをデプロイしたい場合
      DOS> build_esb esb-soap

      デプロイが成功したESBは、再デプロイしないように加工
      しているため、状態をクリアしたい時はcleanオプション
      で初期化すればよい。

    ESB-Rtgをアンデプロイしたい場合
      DOS> build_esb esb-rtg undeploy

  4.5 BPEL

   使い方: build_bpel {bpel-cmp|bpel-req} [clean|deploy]

    BPEL-Cmpをデプロイしたい場合
      DOS> build_bpel bpel-cmp

      デプロイが成功したESBは、再デプロイしないように加工
      しているため、状態をクリアしたい時はcleanオプション
      で初期化すればよい。

5. ログ出力

  logディレクトリに実行時のログを採取する。
  必要に応じて内容確認のこと。

