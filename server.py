from flask import Flask
from flask_socketio import SocketIO
import pyautogui

app = Flask(__name__)
socketio = SocketIO(app)

# لتحريك الماوس
@socketio.on('mouse_move')
def handle_mouse_move(data):
    print(f"Mouse move data received: {data}")  # طباعة الإحداثيات
    # تحريك الماوس
    pyautogui.moveRel(data['x'], data['y'])
    # إرسال الإحداثيات إلى جميع العملاء المتصلين
    socketio.emit('update_coordinates', {'x': data['x'], 'y': data['y']})

# للنقر بزر الماوس
@socketio.on('mouse_click')
def handle_mouse_click(data):
    if data['button'] == 'left':
        pyautogui.click()
    elif data['button'] == 'right':
        pyautogui.click(button='right')

# لإدخال النصوص
@socketio.on('keyboard_input')
def handle_keyboard_input(data):
    pyautogui.typewrite(data['text'])

if __name__ == '__main__':
    socketio.run(app, host='192.168.0.61', port=5000)
