package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.controller.Client;
import org.iismagilov.model.QueryDAO;

@Route(value="browseClient", layout = MainLayout.class)
@PageTitle("Просмотр клиента | Vaadin App by Ilyas")
public class ClientBrowseView extends VerticalLayout {
    public ClientBrowseView(){
        initBrowseClient();
    }
    VerticalLayout formLayout;

    Integer idClient;

    private void initBrowseClient() {
        formLayout = new VerticalLayout();
        H1 text = new H1("Окно просмотра клиента:");
        add(
                text,
                browseClient()
        );

        System.out.println("Method initBrowseClient() is complete");
    }
    private Component browseClient(){
        System.out.println("Method browseClient(): is start");
        VerticalLayout layoutBrowse = new VerticalLayout();
        ComboBox<Client> comboBox = new ComboBox<>("Сведения о клиенте");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            System.out.println("Method browseClient(): select idClient = "+ idClient);
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        layoutBrowse.add(comboBox);
        System.out.println("Method browseClient(): clientInfo idClient = "+ idClient);
        Button browseButton = new Button("Просмотр", (browse) -> {
            add(
            clientInfo(idClient)
            );
        });
        Button returnButton = returning();

        layoutBrowse.add(
                comboBox,
                new HorizontalLayout(browseButton,returnButton)
        );
        add(layoutBrowse);
        System.out.println("Method browseClient() is complete");
        return layoutBrowse;
    }

    private Button returning(){
        Button cancelButton = new Button("Назад",(Exit) -> {
            getUI().ifPresent(ui ->
                    ui.navigate("clientMenu"));
        });
        return cancelButton;
    }

    private Grid clientInfo(Integer idClient){
        System.out.println("Method clientInfo() START for ID = " + idClient);
        Grid<Client> gridInfo = new Grid<>(Client.class, false);
        gridInfo.addColumn(Client::getId).setHeader("Id");
        gridInfo.addColumn(Client::getSurName).setHeader("Second name");
        gridInfo.addColumn(Client::getFirstName).setHeader("First name");
        gridInfo.addColumn(Client::getLastName).setHeader("Last name");
        gridInfo.addColumn(Client::getPhoneNumber).setHeader("Phone");
        gridInfo.addColumn(Client::getInn).setHeader("Inn");
        gridInfo.addColumn(Client::getAddress).setHeader("Address");
        Client gridClient = QueryDAO.selectClient(idClient);
        gridInfo.setItems(gridClient);
        System.out.println("Method clientInfo() is FINISH");
        return gridInfo;
    }
}
