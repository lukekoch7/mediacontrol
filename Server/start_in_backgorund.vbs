Set WshShell = CreateObject("WScript.Shell") 
WshShell.Run "python MediaControlServer.py", 0
Set WshShell = Nothing