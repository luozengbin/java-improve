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
                # ����͒ʏ�A�T�[�o���A�N�e�B�u�łȂ����Ƃ��Ӗ����Ă���̂ŁA��������
                pass
            heapSize = cmo.getHeapSizeCurrent()
            if heapSize > THRESHOLD:
            # �K�v�ȏ��������s���� (�A���[�g�̑��M�A�d�q���[���̑��M�Ȃ�)
                print 'WARNING: The HEAPSIZE is Greater than the Threshold'
            else:
                print heapSize
        java.lang.Thread.sleep(1800000)

def getRunningServerNames():
    domainConfig()
    return cmo.getServers()

if __name__== "main":
    monitorJVMHeapSize()