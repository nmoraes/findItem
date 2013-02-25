package com.example.myproject.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	
	void findItem(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void generarDestacados(String input, AsyncCallback<String> callback);
}
