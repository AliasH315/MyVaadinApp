package org.iismagilov.controller;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.model.QueryDAO;
import org.iismagilov.views.MainLayout;
/*
 * Данный класс CreateClient добавляет клиента в таблицу clients базы данных
 */
@Route(value="createClient", layout = MainLayout.class)
@PageTitle("Создание клиента | Vaadin App by Ilyas")
public class CreateClient extends VerticalLayout {

    public CreateClient(){
        System.out.println("Class createClient is start");
        createClient();
    }

    VerticalLayout formLayout;
    TextField fieldFirstName;
    TextField fieldSurName;
    TextField fieldLastName;
    TextField fieldPhone;
    TextField fieldInn;
    TextField fieldAddress;
    private void createClient() {
        formLayout = new VerticalLayout();
        H1 text = new H1("Окно создания клиента:");
        //text.getStyle().set("text-align", "center");
        add(
                text,
                createFIOForm()
        );

        System.out.println("Method createClient() is complete");
    }
    private Component createFIOForm(){
        VerticalLayout layoutFIO = new VerticalLayout();
        fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setWidth("300px");

        fieldSurName = new TextField("Фамилия клиента:");
        fieldSurName.setWidth("300px");

        fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setWidth("300px");
        Button cancelButton = exit();
        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldFirstName.isEmpty() || fieldSurName.isEmpty() || fieldLastName.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить все поля!");
            }  else {
                layoutFIO.removeFromParent();
                createPhoneForm();
            }
        });

        layoutFIO.add(
                fieldFirstName,
                fieldSurName,
                fieldLastName,
                new HorizontalLayout(nextButton, cancelButton)
        );
        System.out.println("Method createFIOForm() is complete");
        return layoutFIO;
    }

    private void createPhoneForm(){
        VerticalLayout layoutPhone = new VerticalLayout();
        fieldPhone = new TextField("Номер телефона:");
        fieldPhone.setWidth("300px");
        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldPhone.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить номер телефона!");
            } else {
                layoutPhone.removeFromParent();
                createInnForm();
            }
        });
        Button cancelButton = exit();
        layoutPhone.add(
                fieldPhone,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(layoutPhone);
        System.out.println("Method createPhoneForm() is complete");
    }

    private void createInnForm() {
        VerticalLayout layoutInn = new VerticalLayout();
        fieldInn = new TextField("ИНН:");
        fieldInn.setWidth("300px");
        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldInn.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить ИНН!");
            } else {
                layoutInn.removeFromParent();
                createAddressForm();
            }
        });
        Button cancelButton = exit();
        layoutInn.add(
                fieldInn,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(layoutInn);
        System.out.println("Method createInnForm() is complete");
    }
    private void createAddressForm() {
        VerticalLayout layoutAddress = new VerticalLayout();
        fieldAddress = new TextField("Место жительства:");
        fieldAddress.setWidth("300px");
        Button saveButton = new Button("Сохранить", (Save) -> {
            if (fieldAddress.isEmpty()) {
                notification("ВНИМАНИЕ! Необходимо заполнить место жительства!");
            } else {
                QueryDAO.insertClient(
                        fieldFirstName.getValue(),
                        fieldSurName.getValue(),
                        fieldLastName.getValue(),
                        fieldPhone.getValue(),
                        fieldInn.getValue(),
                        fieldAddress.getValue()
                );
                notification("Успешное добавление клиента!");
                getUI().ifPresent(ui ->
                        ui.navigate("clientMenu"));
            }
        });
        Button cancelButton = exit();
        layoutAddress.add(
                fieldAddress,
                new HorizontalLayout(saveButton, cancelButton)
        );
        add(layoutAddress);
        System.out.println("Class createClient complete is successfully!");
    }

    private Button exit(){
        Button cancelButton = new Button("Отменить",(Exit) -> {
            getUI().ifPresent(ui ->
                    ui.navigate("clientMenu"));
        });
        return cancelButton;
    }

    private void notification(String text){
        Notification notification = Notification.show(text);
        notification.setPosition(Notification.Position.MIDDLE);
    }

}
