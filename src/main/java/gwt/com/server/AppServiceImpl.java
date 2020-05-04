package gwt.com.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import gwt.com.client.AppService;
import gwt.com.client.ClientDto;
import gwt.com.server.model.Model;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {
	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public ClientDto initData (ClientDto dto) throws IllegalArgumentException {
		dto.setNumbers(Model.setNumbers(dto.getSize()));
		dto.setAscSort(false);
		return dto;
	}

	@Override
	public ClientDto sortData(ClientDto dto) throws IllegalArgumentException {
		dto.setNumbers(Model.sortArray(dto.getNumbers(), dto.isAscSort()));
		dto.setAscSort(!dto.isAscSort());
		
		return dto;
	}
}
