package com.classplusapp.chat.util;

public enum ExecutionMode {
	HEAD(false), HEADLESS(true);

	private boolean b;

	private ExecutionMode(boolean b) {
		this.b = b;
	}

	public boolean getMode() {
		return b;

	}

}