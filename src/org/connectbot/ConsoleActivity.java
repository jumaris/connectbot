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
import org.connectbot.service.OnBridgeConnectionListener;
import org.connectbot.service.TerminalBridge;
import org.connectbot.service.TerminalManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class ConsoleActivity extends CBFragmentActivity implements ConsoleFragment.ConsoleFragmentContainer, OnBridgeConnectionListener {
	public final static String TAG = "ConnectBot.ConsoleActivity";

	protected TerminalManager mManager = null;

	protected Uri requested;

	ConsoleFragment mFragmentConsole;

	private ServiceConnection connection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mManager = ((TerminalManager.TerminalBinder) service).getService();

			// let manager know about our event handling services
			mManager.addOnBridgeConnectionListener(ConsoleActivity.this);

			Log.d(TAG, String.format("Connected to TerminalManager and found bridges.size=%d", mManager.bridges.size()));

			mManager.setResizeAllowed(true);

			mFragmentConsole.setupConsoles();
		}

		public void onServiceDisconnected(ComponentName className) {
			// tell each bridge to forget about our prompt handler
			synchronized (mManager.bridges) {
				for (TerminalBridge bridge : mManager.bridges)
					bridge.promptHelper.setHandler(null);
			}

			mManager.removeOnBridgeConnectionListener(ConsoleActivity.this);
			mFragmentConsole.destroyConsoles();

			mManager = null;
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// If should be in two-pane mode, finish to return to host list activity
		if (getResources().getBoolean(R.bool.has_two_panes)) {
			finish();
			return;
		}

		setContentView(R.layout.act_console);

		mFragmentConsole = (ConsoleFragment) getSupportFragmentManager().findFragmentById(R.id.consoleFragment);

		if (mFragmentConsole == null) {
			mFragmentConsole = ConsoleFragment.newInstance();

			getSupportFragmentManager().beginTransaction().replace(R.id.consoleFragment, mFragmentConsole).commit();
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		// connect with manager service to find all bridges
		// when connected it will insert all views
		bindService(new Intent(this, TerminalManager.class), connection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume called");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onNewIntent(android.content.Intent)
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		Log.d(TAG, "onNewIntent called");

		requested = intent.getData();

		if (requested == null) {
			Log.e(TAG, "Got null intent data in onNewIntent()");
			return;
		}

		if (mManager == null) {
			Log.e(TAG, "We're not bound in onNewIntent()");
			return;
		}

		mFragmentConsole.startConsole(requested);
	}

	@Override
	public void onStop() {
		super.onStop();

		unbindService(connection);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		Log.d(TAG, String.format(
				"onConfigurationChanged; requestedOrientation=%d, newConfig.orientation=%d",
				getRequestedOrientation(), newConfig.orientation));
	}

	@Override
	public TerminalManager getTerminalManager() {
		return mManager;
	}

	@Override
	public void onTerminalViewChanged(HostBean host) {
		super.onTerminalViewChanged(host);
	}

	/* (non-Javadoc)
	 * @see org.connectbot.CBFragmentActivity#onBridgeViewNeedsRemoval(org.connectbot.service.TerminalBridge)
	 */
	@Override
	protected void onBridgeViewNeedsRemoval(TerminalBridge bridge) {
		mFragmentConsole.removeBridgeView(bridge);
	}
}
