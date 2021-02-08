Set WshShell = CreateObject("WScript.Shell") 

WshShell.Run "MediaControlServer.exe", 2
Set WshShell = Nothing