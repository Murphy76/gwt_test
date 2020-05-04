package gwt.com.client.controller;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.com.client.ClientDto;
import gwt.com.client.AppServiceAsync;
import gwt.com.client.view.IntroView;
import gwt.com.client.view.SortScreen;
import gwt.com.shared.FieldVerifier;

public class MainController {

	private IntroView intro;
	private SortScreen sortScreen;
	private AppServiceAsync appService;
	private ClientDto dto = new ClientDto();

	public MainController(AppServiceAsync appService) {
		this.appService = appService;
	}

	public void initIntroListeners() {
		intro.addButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				checkClick();
			}
		});
		intro.addKeyHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					checkClick();
				}
			}

		});

	}

	private void checkClick() {
		String value = intro.getNumberTextBox().getValue();
		if (FieldVerifier.isValidNumber(value)) {
			dto.setSize(Integer.parseInt(value));
			initData();
		} else {
			Window.alert("Please, enter a positive integer number from 1 to 1000.");
		}
	}

	public void initSortListeners() {
		sortScreen.addUpToThirtyButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String value = ((Button) event.getSource()).getHTML();
				dto.setSize(Integer.parseInt(value));
				initData();
				repaintRoot(sortScreen.asWidget());
			}

		});
		sortScreen.addSortButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (dto.getSize() > 1) {
					sortData();
					repaintRoot(sortScreen.asWidget());
				}
			}
		});
		sortScreen.addResetButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {

				intro.getNumberTextBox().setText("");
				repaintRoot(intro.asWidget());
			}
		});

	}

	private void repaintRoot(Widget composite) {
		RootPanel.get().clear();
		RootPanel.get().add(composite.asWidget());
	}

	public IntroView getIntro() {
		if (intro == null) {
			intro = new IntroView();
			initIntroListeners();
		}
		return intro;
	}

	public SortScreen getSortScreen() {
		if (sortScreen == null) {
			sortScreen = new SortScreen();
			initSortListeners();
		}
		sortScreen.buildContent(dto.getNumbers());
		return sortScreen;
	}

	private void initData() {
		appService.initData(dto, new AsyncCallback<ClientDto>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error");
			}

			@Override
			public void onSuccess(ClientDto result) {
				dto = result;
				repaintRoot(getSortScreen());
			}
		});

	}

	private void sortData() {

		appService.sortData(dto, new AsyncCallback<ClientDto>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error");
			}

			@Override
			public void onSuccess(ClientDto result) {
				dto = result;
				sortScreen.buildContent(dto.getNumbers());
				repaintRoot(getSortScreen());
			}
		});

	}

}
