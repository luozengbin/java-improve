connect('weblogic', 'Manager123', 't3://swld01.olympus.co.jp:7001')
edit()
startEdit()
redeploy('CustomWebService-Release')
save()
activate()
cancelEdit('y')
disconnect()
exit()