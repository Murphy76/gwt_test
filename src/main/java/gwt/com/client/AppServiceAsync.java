package gwt.com.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AppService</code>.
 */
public interface AppServiceAsync {
	void initData(ClientDto dto, AsyncCallback<ClientDto> callback) throws IllegalArgumentException;
	void sortData(ClientDto dto, AsyncCallback<ClientDto> callback) throws IllegalArgumentException;
}
