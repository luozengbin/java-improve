arg = "Arguments=\" -server -Xms256m -Xmx768m -XX:MaxPermSize=256m -da\""
prps = makePropertiesObject (arg)
nmStart('AdminServer', props=prps)