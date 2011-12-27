/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2007 Kenny Root, Jeffrey Sharkey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.connectbot;

import org.connectbot.bean.HostBean;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HostListActivity extends CBFragmentActivity implements
		HostListFragment.HostListFragmentContainer, ConsoleFragment.ConsoleFragmentContainer {
	private static final String TAG = "ConnectBot.HostListActivity";

	protected TerminalManager mManager = null;

	private boolean mIsTwoPane;

	private HostListFragment mFragmentHostList;

	private ConsoleFragment mFragmentConsole;

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mManager = ((TerminalManager.TerminalBinder) service).getService();

			// let manager know about our event handling services
			mManager.addOnBridgeConnectionListener(HostListActivity.this);

			Log.d(TAG, String.format("Connected to TerminalManager and found bridges.size=%d", mManager.bridges.size()));

			if (mIsTwoPane) {
				mManager.setResizeAllowed(true);

				mFragmentConsole.setupConsoles();
			}

			// update our listview binder to find the service
			mFragmentHostList.updateList();
		}

		public void onServiceDisconnected(ComponentName className) {
			if (mIsTwoPane) {
				// tell each bridge to forget about our prompt handler
				synchronized (mManager.bridges) {
					for (TerminalBridge bridge : mManager.bridges)
						bridge.promptHelper.setHandler(null);
				}

				mManager.removeOnBridgeConnectionListener(HostListActivity.this);
				mFragmentConsole.destroyConsoles();
			}

			mFragmentHostList.updateList();

			mManager = null;
		}
	};

	@Override
	public void onStart() {
		super.onStart();

		// start the terminal manager service
		this.bindService(new Intent(this, TerminalManager.class), mConnection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onStop() {
		super.onStop();
		unbindService(mConnection);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		mIsTwoPane = getResources().getBoolean(R.bool.has_two_panes);

		setContentView(R.layout.act_hostlist);

		FragmentTransaction ft = null;

		mFragmentHostList = (HostListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
		if (mFragmentHostList == null) {
			ft = getSupportFragmentManager().beginTransaction();

			mFragmentHostList = HostListFragment.newInstance();
			ft.replace(R.id.listFragment, mFragmentHostList);
		}

		if (mIsTwoPane) {
			mFragmentConsole = (ConsoleFragment) getSupportFragmentManager().findFragmentById(
					R.id.consoleFragment);
			if (mFragmentConsole == null) {
				if (ft == null) {
					ft = getSupportFragmentManager().beginTransaction();
				}

				mFragmentConsole = ConsoleFragment.newInstance();

				ft.replace(R.id.consoleFragment, mFragmentConsole);
			}
		}

		if (ft != null) {
			ft.commit();
		}

		final Button buttonAdd = (Button) findViewById(R.id.button_add);
		if (buttonAdd != null) {
			buttonAdd.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO start the "new host editing" activity
					Log.d(TAG, "Add button pressed");
				}
			});
		}
	}

	@Override
	public TerminalManager getTerminalManager() {
		return mManager;
	}

	/* (non-Javadoc)
	 * @see org.connectbot.ConsoleFragment.ConsoleFragmentContainer#onTerminalViewChanged(org.connectbot.bean.HostBean)
	 */
	@Override
	public void onTerminalViewChanged(HostBean host) {
		super.onTerminalViewChanged(host);

		mFragmentHostList.updateHandler.sendEmptyMessage(-1);
		mFragmentHostList.setCurrentSelected(host);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.CBFragmentActivity#onBridgeViewNeedsRemoval(org.connectbot.service.TerminalBridge)
	 */
	@Override
	protected void onBridgeViewNeedsRemoval(TerminalBridge bridge) {
		if (mIsTwoPane) {
			mFragmentConsole.removeBridgeView(bridge);
		}
	}
}
