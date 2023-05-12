package org.iismagilov.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.model.QueryDAO;

@Route(value="clientMenu", layout = MainLayout.class)
@PageTitle("Работа с клиентами | Vaadin App by Ilyas")
public class ClientMenuView extends VerticalLayout {

    public ClientMenuView() {
        VerticalLayout clientLayout = new VerticalLayout();
        Button buttonClientAdd = new Button("Создать клиента");
        buttonClientAdd.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonClientUpdate = new Button("Редактировать клиента");
        buttonClientUpdate.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonClientDelete = new Button("Удалить клиента");
        buttonClientDelete.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonReturn = new Button("Вернуться назад");
        buttonReturn.addThemeVariants(ButtonVariant.LUMO_LARGE);
        add(
                new H1("Операции с клиентами:"),
                clientLayout,
                buttonClientAdd,
                buttonClientUpdate,
                buttonClientDelete,
                buttonReturn
        );

        buttonClientAdd.addClickListener(click -> {
            FormLayout formLayout = new FormLayout();
            formLayout.setHeight("500px");
            formLayout.setWidth("900px");
            TextField fieldFirstName = new TextField("Имя клиента:");
            fieldFirstName.setRequiredIndicatorVisible(true);
            formLayout.add(fieldFirstName);
            fieldFirstName.setWidth("300px");
            TextField fieldSurName = new TextField("Фамилия клиента:");
            fieldSurName.setRequiredIndicatorVisible(true);
            fieldSurName.setWidth("300px");
            formLayout.add (fieldSurName);
            TextField fieldLastName = new TextField("Отчество клиента:");
            fieldLastName.setRequiredIndicatorVisible(true);
            fieldLastName.setWidth("300px");
            formLayout.add(fieldLastName);
            TextField fieldPhone = new TextField("Номер телефона:");
            fieldPhone.setWidth("300px");
            formLayout.add(fieldPhone);
            TextField fieldInn = new TextField("ИНН:");
            fieldInn.setWidth("300px");
            formLayout.add (fieldInn);
            TextField fieldAddress = new TextField("Адрес:");
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
                fieldFirstName.clear();
                fieldSurName.clear();
                fieldLastName.clear();
                fieldPhone.clear();
                fieldInn.clear();
                fieldAddress.clear();

            });
            Button cancelButton = new Button("Выход", (Exit) -> {
                fieldFirstName.clear();
                fieldSurName.clear();
                fieldLastName.clear();
                fieldPhone.clear();
                fieldInn.clear();
                fieldAddress.clear();
            });
            formLayout.add(new HorizontalLayout(saveButton,cancelButton));
            add(
                    formLayout
            );

            System.out.println("buttonClientAdd formLayout is complete!");



        });
        // Не реализовано
        buttonClientUpdate.addClickListener(click -> {
            QueryDAO.updateClient(3,"wer2","dfg2","sdfsd2f","9172434364","343453453","Nfegfg");

        });
        // Не реализовано
        buttonClientDelete.addClickListener(click -> {
            QueryDAO.deleteClient(3);
        });

        buttonReturn.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate(""));
        });
        }
}
