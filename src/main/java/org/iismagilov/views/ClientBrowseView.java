package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.controller.Client;
import org.iismagilov.controller.Account;
import org.iismagilov.model.QueryDAO;

import java.util.List;

@Route(value="browseClient", layout = MainLayout.class)
@PageTitle("Просмотр клиента | Vaadin App by Ilyas")
public class ClientBrowseView extends VerticalLayout {
    public ClientBrowseView(){
        initBrowseClient();
    }
    private VerticalLayout formLayout;
    private VerticalLayout infoLayout;
    private Integer idClient;

    private void initBrowseClient() {
        formLayout = new VerticalLayout();
        H1 text = new H1("Окно просмотра клиента:");
        add(
                text,
                browseClient()
        );
        System.out.println("Method initBrowseClient() is complete");
    }
    private Component browseClient() {
        System.out.println("Method browseClient(): is start");
        VerticalLayout browseLayout = new VerticalLayout();
        ComboBox<Client> comboBox = new ComboBox<>("Сведения о клиенте");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        System.out.println("Method browseClient(): clientInfo idClient = " + idClient);
        Button browseButton = new Button("Просмотр", click ->
                infoLayout(idClient)
        );
        Button returnButton = exit();
        browseLayout.add(
                comboBox,
                new HorizontalLayout(browseButton, returnButton)
        );
        add(
                browseLayout
        );
        System.out.println("Method browseClient() is complete");
        return browseLayout;
    }

    private Component infoLayout(Integer idClient) {
        if(infoLayout != null){
            infoLayout.removeFromParent();
        }
        infoLayout = new VerticalLayout();
        infoLayout.add(
                clientInfo(idClient),
                accountInfo(idClient)
        );
        add(infoLayout);
        return infoLayout;
    }
    private Grid clientInfo(Integer idClient) {
        System.out.println("Method clientInfo() START for ID = " + idClient);
        Grid<Client> gridClientInfo = new Grid<>(Client.class, false);
        gridClientInfo.addColumn(Client::getId).setHeader("Id").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getSurName).setHeader("Second name").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getFirstName).setHeader("First name").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getLastName).setHeader("Last name").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getPhoneNumber).setHeader("Phone").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getInn).setHeader("Inn").setAutoWidth(true);
        gridClientInfo.addColumn(Client::getAddress).setHeader("Address").setAutoWidth(true);
        gridClientInfo.setMaxHeight("100px");
        Client gridClient = QueryDAO.selectClient(idClient);
        gridClientInfo.setItems(gridClient);
        System.out.println("Method clientInfo() FINISH for ID = " + idClient);
        return gridClientInfo;
    }
    private Grid accountInfo(Integer idClient) {
        System.out.println("Method accountInfo() START for ID = " + idClient);
        Grid<Account> gridAccountInfo = new Grid<>(Account.class, false);
        gridAccountInfo.addColumn(Account::getId).setHeader("Id").setAutoWidth(true);
        gridAccountInfo.addColumn(Account::getAccountNumber).setHeader("Account Number").setAutoWidth(true);
        gridAccountInfo.addColumn(Account::getSumma).setHeader("Summa").setAutoWidth(true);
        gridAccountInfo.addColumn(Account::getCurrency).setHeader("Currency").setAutoWidth(true);
        gridAccountInfo.addColumn(Account::getBik).setHeader("BIK").setAutoWidth(true);
        gridAccountInfo.addColumn(Account::getAccountStatus).setHeader("Status").setAutoWidth(true);
        gridAccountInfo.setMaxHeight("250px");
        List<Account> gridAccount = QueryDAO.selectAccounts(idClient);
        gridAccountInfo.setItems(gridAccount);
        System.out.println("Method accountInfo() FINISH for ID = " + idClient);
        return gridAccountInfo;
    }
    private Button exit(){
        Button cancelButton = new Button("Отменить",click ->
                getUI().ifPresent(ui ->
                        ui.navigate("clientMenu")));
        return cancelButton;
    }
}
