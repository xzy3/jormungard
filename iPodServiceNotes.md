# Regestry keys #

`HKEY_LOCAL_MACHINE\SOFTWARE\Apple Computer, Inc.\iPod\RegisteredApps` lists the applications the ipod service has registered on my computer:

  * 4 is iTunes
  * 1 is defined but has no key that specifies what program it is
  * 1024 is just like 1

# using the com object #

human readable name: `IPodService.iPodManager`

Boiler plate code to use the com object
```
from win32com import client

ITUNES_ID = 4

iPodManager = client.Dispatch("IPodService.iPodManager")

iPodManager.Login(ITUNES_ID)
ipodHandle = iPodManager.FindFirstiPod(ITUNES_ID)

#do stuff here using the ipodhandle and ITUNES_ID

iPodManager.Logout(ITUNES_ID)
```

unfortunately that code dies on line 5