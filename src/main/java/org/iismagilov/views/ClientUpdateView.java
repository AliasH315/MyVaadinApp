package org.iismagilov.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.controller.Client;
import org.iismagilov.model.QueryDAO;

@Route(value="updateClient", layout = MainLayout.class)
@PageTitle("Редактирование клиента | Vaadin App by Ilyas")
public class ClientUpdateView extends VerticalLayout {

    VerticalLayout updateLayout;

    TextField fieldFirstName;
    TextField fieldSurName;
    TextField fieldLastName;
    TextField fieldPhone;
    TextField fieldInn;
    TextField fieldAddress;

    Integer idClient;

    public ClientUpdateView(){
        initUpdateClient();
    }

    private void initUpdateClient() {
        updateLayout = new VerticalLayout();
        H1 text = new H1("Окно редактирования клиента:");
        ComboBox<Client> comboBox = new ComboBox<>("Какого клиента необходимо редактировать?");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            System.out.println("Method updateClient(): select idClient = " + idClient);
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        updateLayout.add(comboBox);
        System.out.println("Method updateClient(): clientInfo idClient = " + idClient);
        Button selectButton = new Button("Выбрать", (browse) ->
            updateClient(idClient)
        );
        Button returnButton = exit();

        updateLayout.add(
                comboBox,
                new HorizontalLayout(selectButton, returnButton)
        );
        add(
                text,
                updateLayout
        );
        System.out.println("Method browseClient() is complete");
    }

    private void updateClient(Integer idClient) {
        FormLayout formLayout = new FormLayout();
        fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setWidth("300px");

        fieldSurName = new TextField("Фамилия клиента:");
        fieldSurName.setWidth("300px");

        fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setWidth("300px");

        fieldPhone = new TextField("Номер телефона:");
        fieldPhone.setWidth("300px");

        fieldInn = new TextField("ИНН:");
        fieldInn.setWidth("300px");

        fieldAddress = new TextField("Место жительства:");
        fieldAddress.setWidth("300px");

        Button cancelButton = exit();
        Button saveButton = new Button("Сохранить", (Save) -> {
            if (fieldFirstName.isEmpty() && fieldSurName.isEmpty() && fieldLastName.isEmpty()
                    && fieldPhone.isEmpty() && fieldInn.isEmpty() && fieldAddress.isEmpty()) {
                notification("ВНИМАНИЕ! Необходимо изменить хотя бы один параметр!");
            } else {
                QueryDAO.updateClient(idClient,
                        fieldFirstName.getValue(),
                        fieldSurName.getValue(),
                        fieldLastName.getValue(),
                        fieldPhone.getValue(),
                        fieldInn.getValue(),
                        fieldAddress.getValue()
                );
                notification("Успешное изменение клиента!");
                getUI().ifPresent(ui ->
                        ui.navigate("clientMenu"));
            }
        });
        formLayout.add(
                fieldFirstName,
                fieldSurName,
                fieldLastName,
                fieldPhone,
                fieldInn,
                fieldAddress,
                new HorizontalLayout(saveButton, cancelButton)
        );
        add(formLayout);

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
