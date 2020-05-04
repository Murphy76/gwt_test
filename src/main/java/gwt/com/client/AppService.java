package gwt.com.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface AppService extends RemoteService {
	ClientDto initData(ClientDto dto) throws IllegalArgumentException;
	ClientDto sortData(ClientDto dto) throws IllegalArgumentException;
}
