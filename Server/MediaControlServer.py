import socket
import pyautogui
import sys

HOST = socket.gethostbyname(socket.gethostname())
PORT = 9004

def getAction(data):
    switcher={
        b'\x01':'playpause',
        b'\x02':'volumedown',
        b'\x03':'volumeup',
        b'\x04':'prevtrack',
        b'\x05':'nexttrack'
    }
    return switcher.get(data, "invalid input")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    while True:
        conn, addr = s.accept()
        with conn:
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                action = getAction(data)
                print(action)
                pyautogui.press(action)