<wiki:gadget up\_ad\_client="ca-pub-7409863523067273" up\_ad\_slot="0505625396" width="468" height="60" border="0" up\_keywords="connectbot android ssh secure shell sftp terminal" url="http://goo.gl/R5bvK" />

## [Go to our new website](https://connectbot.org/) ##

ConnectBot is a [Secure Shell](http://en.wikipedia.org/wiki/Secure_Shell) client for the Android platform.  Its ultimate goal is to create a secure connection through which you can use a shell on a remote machine and transfer files back and forth to your phone.

## Development Team ##

Two core developers are working on the client, [Kenny Root](http://the-b.org/) and [Jeffrey Sharkey](http://jsharkey.org/).

We're both idling in <a href='irc://irc.freenode.net:6667/%23connectbot'>#connectbot</a> on freenode**if you have questions.  If you're a developer, check out details about how the current codebase is [Design](Design.md)ed.**

There are also mailing lists: [ConnectBot-users](http://groups.google.com/group/connectbot-users) for user support and [ConnectBot-commits](http://groups.google.com/group/connectbot-commits) for development updates.

## Translation to your language ##

If you'd like to see ConnectBot translated into your language and you're willing to help, then head on over to [ConnectBot Translations at LaunchPad](https://translations.launchpad.net/connectbot/trunk/+pots/fortune).

## Available in Android Market ##

_The latest stable version of ConnectBot is available in the Android Market._ You can scan the code below using the Barcode Scanner application to go directly to its entry in Market:

![http://connectbot.googlecode.com/svn/trunk/www/qr-code.png](http://connectbot.googlecode.com/svn/trunk/www/qr-code.png)

If you want to run the **development versions**, you can follow these quick steps to getting ConnectBot working on your Android phone:

1. **Enable outside-of-Market applications.**  Go into Settings, Applications, and enable the "Unknown sources" option.

2. **Uninstall any old versions.**  Go into Settings, Applications, Manage Applications.  Look through the list for ConnectBot and uninstall if it's there.  **_or_**  From from your desktop console, type `./adb -d uninstall org.connectbot`

3. **Install the new version.**  Open your Android web browser to this page and download one of the APKs shown on the right.  Tap on the download when it's done and follow the instructions to install the app.  **_or_** From your desktop console, download an APK and type `./adb -d install [filename].apk`


## If you run into _application_ problems ##

Please, _please_ send us relevant logcat dumps when you have a crash.  Here's how to get a logcat dump:

1. **Enable USB debugging.**  Go into Settings, Applications, Development, and enable the "USB debugging" option.

2. **Install the Android SDK.**  You'll need a desktop tool called `adb` that will help you get error logs.

3. **Make sure your phone can connect.**  Follow the instructions here to make sure that `adb` can talk with your device:

http://code.google.com/android/intro/develop-and-debug.html#developingondevicehardware

3. **Dump logcat data.**  From your desktop console, type `./adb -d logcat | grep -i connectbot`.  Make sure it's showing some data, then copy everything into a text file and attach to your bugreport here on this site.  **CAREFULLY** read over the logs for any sensitive information **BEFORE** posting.  You might need to Ctrl+C to quit adb once it stops printing data.



---


&lt;wiki:gadget url="http://the-b.org/~kenny/gplusbadge.xml" /&gt;

&lt;wiki:gadget url="http://www.ohloh.net/p/15367/widgets/project\_users.xml?style=blue" height="100"  border="0" /&gt;


**Join us!**  We're making an awesome SSH client for Android, and have several NewFeatures that we'd like to implement.

## Versions for Pre-1.5 (pre-Cupcake) Phones ##

The last version specifically supporting OS 1.0 or 1.1 is [r203](https://code.google.com/p/connectbot/source/detail?r=203). You can download it here for your older phone:

[ConnectBot v1.3](http://connectbot.googlecode.com/files/ConnectBot-svn-r203.apk).

## Video demo ##

Check out video showing off new features, including gesture terminals and font resizing:

http://www.jsharkey.org/downloads/ssh.html

Here are some updated screenshots on the 0.9 SDK:

![http://www.jsharkey.org/downloads/ssh2.png](http://www.jsharkey.org/downloads/ssh2.png)
![http://www.jsharkey.org/downloads/ssh4.png](http://www.jsharkey.org/downloads/ssh4.png)

And some older screenshots on the m3 SDK:

![http://connectbot.googlecode.com/svn/trunk/www/connectbot-top.png](http://connectbot.googlecode.com/svn/trunk/www/connectbot-top.png) ![http://connectbot.googlecode.com/svn/trunk/www/connectbot-list.png](http://connectbot.googlecode.com/svn/trunk/www/connectbot-list.png)