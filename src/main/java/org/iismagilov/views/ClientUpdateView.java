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

    private VerticalLayout updateLayout;
    private TextField fieldFirstName;
    private  TextField fieldSurName;
    private TextField fieldLastName;
    private TextField fieldPhone;
    private TextField fieldInn;
    private TextField fieldAddress;

    private Integer idClient;
    private String firstName;
    private String surName;
    private  String lastName;
    private String phone;
    private String inn;
    private String address;

    private Boolean isFirstForm = true;

    public ClientUpdateView(){
        initUpdateClient();
    }

    private void initUpdateClient() {
        updateLayout = new VerticalLayout();
        System.out.println("Method initUpdateClient() is START!");
        H1 text = new H1("Окно редактирования клиента:");
        ComboBox<Client> comboBox = new ComboBox<>("Какого клиента необходимо редактировать?");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            surName = selectClient.getSurName();
            firstName = selectClient.getFirstName();
            lastName = selectClient.getLastName();
            phone = selectClient.getPhoneNumber();
            inn = selectClient.getInn();
            address = selectClient.getAddress();
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        Button selectButton = new Button("Выбрать", click -> {
            updateClient(idClient);
            updateLayout.removeFromParent();
        });

        Button returnButton = exit();

        updateLayout.add(
                comboBox,
                new HorizontalLayout(selectButton, returnButton)
        );
        add(
                text,
                updateLayout
        );
        System.out.println("Method initUpdateClient() is FINISH!");
    }

    private void updateClient(Integer idClient) {
        FormLayout formLayout = new FormLayout();
        fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setWidth("300px");
        fieldFirstName.setValue(firstName);
        fieldFirstName.setMaxLength(30);

        fieldSurName = new TextField("Фамилия клиента:");
        fieldSurName.setWidth("300px");
        fieldSurName.setValue(surName);
        fieldSurName.setMaxLength(50);

        fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setWidth("300px");
        fieldLastName.setValue(lastName);
        fieldLastName.setMaxLength(30);

        fieldPhone = new TextField("Номер телефона:");
        fieldPhone.setWidth("300px");
        fieldPhone.setValue(phone);
        fieldPhone.setMaxLength(16);

        fieldInn = new TextField("ИНН:");
        fieldInn.setWidth("300px");
        fieldInn.setValue(inn);
        fieldInn.setMaxLength(12);

        fieldAddress = new TextField("Место жительства:");
        fieldAddress.setWidth("300px");
        fieldAddress.setValue(address);
        fieldAddress.setMaxLength(100);

        Button cancelButton = exit();
        Button saveButton = new Button("Сохранить", click -> {
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
                fieldSurName,
                fieldFirstName,
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
