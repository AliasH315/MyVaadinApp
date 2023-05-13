package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.iismagilov.model.QueryDAO;

public class ClientAddView extends FormLayout {

    FormLayout formLayout;
    TextField fieldFirstName;
    TextField fieldSurName;
    TextField fieldLastName;
    TextField fieldPhone;

    TextField fieldInn;
    TextField fieldAddress;
    public Component createClient(){
        formLayout = new FormLayout();
        formLayout.setHeight("500px");
        formLayout.setWidth("900px");
        fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setRequiredIndicatorVisible(true);
        formLayout.add(fieldFirstName);
        fieldFirstName.setWidth("300px");
        fieldSurName = new TextField("Фамилия клиента:");
        fieldSurName.setRequiredIndicatorVisible(true);
        fieldSurName.setWidth("300px");
        formLayout.add(fieldSurName);
        fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setRequiredIndicatorVisible(true);
        fieldLastName.setWidth("300px");
        formLayout.add(fieldLastName);
        fieldPhone = new TextField("Номер телефона:");
        fieldPhone.setWidth("300px");
        formLayout.add(fieldPhone);
        fieldInn = new TextField("ИНН:");
        fieldInn.setWidth("300px");
        formLayout.add(fieldInn);
        fieldAddress = new TextField("Адрес:");
        fieldAddress.setWidth("300px");
        formLayout.add(fieldAddress);
        Button saveButton = new Button("Сохранить", (Save) -> {
            QueryDAO.insertClient(
                    fieldFirstName.getValue().toString(),
                    fieldSurName.getValue().toString(),
                    fieldLastName.getValue().toString(),
                    fieldPhone.getValue().toString(),
                    fieldInn.getValue().toString(),
                    fieldAddress.getValue().toString()
            );
            Notification notification = Notification.show("Успешное добавление клиента!");
            notification.setPosition(Notification.Position.MIDDLE);
            clearField();
            });
        Button cancelButton = new Button("Выход",(Exit) -> {
            formLayout.removeFromParent();
            });
        formLayout.add(new HorizontalLayout(saveButton, cancelButton));
    return formLayout;
    }

    private void clearField(){
        fieldFirstName.clear();
        fieldSurName.clear();
        fieldLastName.clear();
        fieldPhone.clear();
        fieldInn.clear();
        fieldAddress.clear();
    }
}
