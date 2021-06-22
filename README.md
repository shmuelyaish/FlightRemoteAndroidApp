# FlightRemoteAndroidApp
Author: Shmuel Yaish
This project for my university course Advanced Programming 2

Introduction: I created an android application that acts as a remote control for the desktop flight gear airplane
Note: app works best with a 5.8 inch screen in portrait
Video presentation: 
Part I: https://www.youtube.com/watch?v=slk8ThRlRyg
Part II: https://www.youtube.com/watch?v=8mzoYDJU1n0

How to use:
1) Install the flight gear app on your computer from this site https://www.flightgear.org/
2) Open flight gear on your computer
3) Go to settings and scroll down
4) Put this in the box on the bottom     --telnet=socket,in,10,127.0.0.1,8080,tcp
5) That will tell it to open a tcp socket on local host at port 8080. You can choose a different port if you'd like
6) Open the mobile app on your phone.
7) Type in your computers IP address in IP, and port in port.
8) Hit connect, notice, it may take a few moments to connect. If you get a message saying "Try connecting again" wait a momment and then hit "Connect" again. If the problem continues, check that the IP and Port are valid.
9) You should get a message "Connected" once connected.
10) On the flight gear computer, hit "Cessna" and then hit "Autostart" to start the engines
11) On the app the left slider controls the throttle, bottom controls the rudder, the x axis on the joystick is the Aileron and the y is the elevator
12) Have a safe flight!

How to install app on android:
1) Download the zip folder "FlightRemote.zip"
2) Open the project on intellij
3) Connect android that is in developer mode and has USB debugging enabled
4) Install the app through intellij on to android
5) Open the app "FlightRemote"
