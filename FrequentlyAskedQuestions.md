#summary Questions that are frequently asked with their answers

# Server Types #

  * _Q:_ Why can't I connect to my BitVise WinSSHD server using ConnectBot?
    * _A:_ Bitvise WinSSHD has a built-in **very** limited "terminfo" database which is not compatible with ConnectBot's default emulation of "screen"
    * To fix this, go to **Host List** -> press "**Menu**" -> **Preferences** -> **Emulation type** -> select "**xterm**"

  * _Q:_ Why can't I connect to my Mikrotik router?
    * _A:_ Mikrotik uses a version of OpenSSHd that uses an obsolete DH group exchange. ConnectBot does not support that at this time.

  * _Q_: How can I have multiple connections to the same host?
    * _A_: ConnectBot distinguishes between connections based on attributes such as its nickname. In the Host List, long press on the entry, edit it, and change its host name. This will allow you to connect to the same host from the quick connect box again and it will make another entry.

  * _Q_: How do I connect to a SSH server with a non-standard port (e.g., 2222)?
    * _A_: When entering an address in the quick-connect bar, you can simply type something like "me@example.com:2222" when connecting as indicated by the hint. For hosts that have already been made, simply long-press on the entry in the list and select "Edit host."

  * _Q_: Why doesn't the SSH agent work?
    * _A_: You have to enable SSH agent for each host you want to use it on, because a malicious host could potentially use your credentials to log in to another host. To enable SSH agent, long press on the entry in the Host List screen, edit the host, and click the checkbox for enabling SSH agent.

  * _Q_: How do I export my private or public keys from ConnectBot?
    * _A_: In the Pubkey list, long press on the entry for those options. Note that it only copies the key to the Android clipboard in the current version. It doesn't export to a file. Also see the videos demonstrating pubkey usage in the UserInterface page.

  * _Q_: The local shell is lacking some feature I desire like tab completion. Why doesn't ConnectBot provide this?
    * _A_: The terminal emulator doesn't provide features like tab completion. That is the job of the shell. There is an ARM-based GNU bash shell available for download under Downloads.

  * _Q_: I can't see the font! It's too small!
    * _A_: The Volume+ and Volume- keys change the font.

  * _Q_: How do I connect to the same host multiple times
    * _A_: The only way to do this now is to make multiple entries in the Host List with different nicknames. You can edit a host by long-pressing on the entry.

  * _Q_: I want to rotate my screen, but it's stuck in portrait/landscape!
    * _A_: I don't think you actually want to rotate your screen, because in landscape mode with a software keyboard, it covers up the terminal. However, go to Preferences from Host List to change the "Rotation mode."