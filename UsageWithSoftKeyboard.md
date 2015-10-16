#summary How to use ConnectBot with the Virtual Keyboard

![http://connectbot.googlecode.com/svn/trunk/www/magic-cb-screen.png](http://connectbot.googlecode.com/svn/trunk/www/magic-cb-screen.png)

## Caveats ##
Since ConnectBot doesn't use any of the normal TextView widgets, Android's IME structure isn't designed to directly support it.

The best way to use Android with a virtual keyboard is in **Portrait** mode. By default, ConnectBot is set to use **Portrait** mode when no hardware keyboard is present. To change this setting, go to **Preferences** from the **Host List**.

In **Landscape** mode, the Android virtual keyboard (or other IMEs) will take up the entire screen. Android provides no way for ConnectBot to resize the terminal view in **Landscape**. However, you may use a _work-around_: **Force Resize** to fit above the virtual keyboard if desired.

On devices without a hardware keyboard, you may press and hold the **MENU** button to bring up the virtual keyboard. NOTE: This applies to any program on the Android platform; it is not ConnectBot specific.

## How to Enter Control, Alt, Escape, and Function Keys ##

You can enter any key combination with ConnectBot and the virtual keyboard, but you must know how keys are mapped on a normal console. For instance, usually combinations of ALT+letter on a PC keyboard are actually mapped to sending, sequentially, ESC key then the letter.

Note there are also ScreenGestures for **Page Up** and **Page Down**.

  * Trackball: 1 press is **CTRL**, 2 presses sends **ESC**
  * **Tab key** = **CTRL + i**
  * **Function key** = **CTRL + number**

## Ctrl and Escape on Devices Without a Trackball ##

Some devices such as the **EVO 4G** do not have a trackball or trackpad at all. You can use [Full Keyboard](http://www.androlib.com/android.application.com-hmw-android-fullkeyboard-npwE.aspx) from the Android Market to get around this problem until something is done in ConnectBot itself.

## Examples ##

  * **ESC** = Press the trackball twice.
  * **ALT + Right Arrow** = Press trackball twice then move trackball to right.
  * **CTRL + A** = Press trackball once then tap the "A" key on the soft keyboard.
  * **F3** = Press trackball once then tap the "3" key on the soft keyboard.