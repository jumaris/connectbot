/**
 *
 */
package org.connectbot;

import org.connectbot.bean.HostBean;
import org.connectbot.service.OnBridgeConnectionListener;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

/**
 * @author kenny
 *
 */
public abstract class CBFragmentActivity extends FragmentActivity implements ConsoleFragment.ConsoleFragmentContainer, OnBridgeConnectionListener {
	private static final int MSG_INVALIDATE_MENU = 1;

	private static final int MSG_CLOSE_BRIDGE = 2;

	private Handler mUiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_INVALIDATE_MENU:
				invalidateOptionsMenu();
				break;
			case MSG_CLOSE_BRIDGE:
				final TerminalBridge bridge = (TerminalBridge) msg.obj;
				if (bridge.isAwaitingClose()) {
					onBridgeViewNeedsRemoval(bridge);
//					mFragmentConsole.removeBridgeView(bridge);
				}
				break;
			}
		}
	};

	/* (non-Javadoc)
	 * @see org.connectbot.ConsoleFragment.ConsoleFragmentContainer#onTerminalViewChanged(org.connectbot.bean.HostBean)
	 */
	public void onTerminalViewChanged(HostBean host) {
		mUiHandler.sendEmptyMessage(MSG_INVALIDATE_MENU);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.service.OnBridgeConnectionListener#onBridgeDisconnected(org.connectbot.service.TerminalBridge)
	 */
	public void onBridgeDisconnected(TerminalBridge bridge) {
		final Message msg = mUiHandler.obtainMessage(MSG_CLOSE_BRIDGE, bridge);
		mUiHandler.sendMessage(msg);

		mUiHandler.sendEmptyMessage(MSG_INVALIDATE_MENU);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.service.OnBridgeConnectionListener#onBridgeConnected(org.connectbot.service.TerminalBridge)
	 */
	public void onBridgeConnected(TerminalBridge bridge) {
		mUiHandler.sendEmptyMessage(MSG_INVALIDATE_MENU);
	}

	/**
	 * Should handle removal of the bridge.
	 * @param bridge
	 */
	abstract protected void onBridgeViewNeedsRemoval(TerminalBridge bridge);

	/* (non-Javadoc)
	 * @see org.connectbot.ConsoleFragment.ConsoleFragmentContainer#getTerminalManager()
	 */
	abstract public TerminalManager getTerminalManager();
}
