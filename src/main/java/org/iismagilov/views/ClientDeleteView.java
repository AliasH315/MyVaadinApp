package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.controller.Client;
import org.iismagilov.model.QueryDAO;

@Route(value="deleteClient", layout = MainLayout.class)
@PageTitle("Удаление клиента | Vaadin App by Ilyas")
public class ClientDeleteView extends VerticalLayout {

    public ClientDeleteView(){
        initDeleteClient();
    }
    VerticalLayout formLayout;

    Integer idClient;

    private void initDeleteClient() {
        formLayout = new VerticalLayout();
        H1 text = new H1("Окно удаления клиента:");
        add(
                text,
                deleteClient()
        );

        System.out.println("Method initDeleteClient() is complete");
    }

    private Component deleteClient(){
        VerticalLayout layoutDelete = new VerticalLayout();
        ComboBox<Client> comboBox = new ComboBox<>("Какого клиента необходимо удалить?");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        //comboBox.setNullSelectionAllowed(false);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        Button deleteButton = new Button("Удалить", (delete) -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Вы действительно хотите удалить данного клиента?");
            Button yesButton = new Button("Да", (Yes) -> {
                dialog.close();
                if (QueryDAO.deleteClient(idClient)){
                    notification("Успешное удаление клиента!");
                    getUI().ifPresent(ui ->
                            ui.navigate("clientMenu"));
                } else {
                    notification("Клиента удалить не удалось!");
                    getUI().ifPresent(ui ->
                            ui.navigate("clientMenu"));
                }
            });
            Button noButton = new Button("Нет", (No) -> dialog.close());
            dialog.getFooter().add(yesButton, noButton);
            dialog.open();   


        });
        Button cancelButton = exit();
        layoutDelete.add(
                comboBox,
                new HorizontalLayout(deleteButton,cancelButton)
        );
        add(layoutDelete);
        System.out.println("Method deleteClient() is complete");
        return layoutDelete;
        }

    private Button exit(){
        Button cancelButton = new Button("Отменить",click ->
            getUI().ifPresent(ui ->
                    ui.navigate("clientMenu")));
        return cancelButton;
    }

    private void notification(String text){
        Notification notification = Notification.show(text);
        notification.setPosition(Notification.Position.MIDDLE);
    }
}
