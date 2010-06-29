import thread
import time

def checkHealth(serverName):
  while 1:
    slBean = getSLCRT(serverName)
    status = slBean.getState()
    print 'Status of Managed Server is '+status
    if status != "RUNNING":
      print 'Starting server '+serverName
      start(serverName, block="true")
    time.sleep(5) 

def getSLCRT(svrName):
    domainRuntime()
    slrBean = cmo.lookupServerLifeCycleRuntime(svrName)
    return slcBean