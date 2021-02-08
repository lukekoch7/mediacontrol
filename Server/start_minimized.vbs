Set WshShell = CreateObject("WScript.Shell") 
WshShell.Run "python MediaControlServer.py", 2
Set WshShell = Nothing