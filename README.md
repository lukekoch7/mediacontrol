# mediacontrol
### A simple android app and python server that let you remotely press media control buttons on your PC with your smartphone


For simple usage, install the apk on your phone and start the server on your pc.
To start the server, you have two options:
- run the  python script located at `Server/MediaControlServer.py`. You might have to install ![pyautogui](https://pyautogui.readthedocs.io/en/latest/).
- use the windows executable located at `Server/dist.zip/MediaControlServer/MediaControlServer.exe`.

For ease of use, there are VisualBasic scripts that start the server ether minimized or in the background. Simply double-click them to start the server.

Port 9004 is used for socket communication.
