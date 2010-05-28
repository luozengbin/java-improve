waitTime=300000
THRESHOLD=100000000
uname = "weblogic"
pwd = "Manager123"
url = "t3://localhost:7001"
def monitorJVMHeapSize():
    connect(uname, pwd, url)
    while 1:
        serverNames = getRunningServerNames()
        domainRuntime()
        for name in serverNames:
            print 'Now checking '+name.getName()
            try:
           
            except WLSTException,e:
                # これは通常、サーバがアクティブでないことを意味しているので、無視する
                pass
            heapSize = cmo.getHeapSizeCurrent()
            if heapSize > THRESHOLD:
            # 必要な処理を実行する (アラートの送信、電子メールの送信など)
                print 'WARNING: The HEAPSIZE is Greater than the Threshold'
            else:
                print heapSize
        java.lang.Thread.sleep(1800000)

def getRunningServerNames():
    domainConfig()
    return cmo.getServers()

if __name__== "main":
    monitorJVMHeapSize()