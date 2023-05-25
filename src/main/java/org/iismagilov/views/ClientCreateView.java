package org.iismagilov.views;

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
public class ClientCreateView extends VerticalLayout {

    public ClientCreateView(){
        System.out.println("Class createClient is start");
        initCreateClient();
    }

    private VerticalLayout formLayout;
    private TextField fieldFirstName;
    private TextField fieldSurName;
    private TextField fieldLastName;
    private TextField fieldPhone;
    private TextField fieldInn;
    private TextField fieldAddress;
    private void initCreateClient() {
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
        VerticalLayout fioLayout = new VerticalLayout();
        fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setWidth("300px");
        fieldFirstName.setMaxLength(30);

        fieldSurName = new TextField("Фамилия клиента:");
        fieldSurName.setWidth("300px");
        fieldSurName.setMaxLength(50);

        fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setWidth("300px");
        fieldLastName.setMaxLength(30);

        Button cancelButton = exit();
        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldFirstName.isEmpty() || fieldSurName.isEmpty() || fieldLastName.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить все поля!");
            }  else {
                fioLayout.removeFromParent();
                createPhoneForm();
            }
        });

        fioLayout.add(
                fieldFirstName,
                fieldSurName,
                fieldLastName,
                new HorizontalLayout(nextButton, cancelButton)
        );
        System.out.println("Method createFIOForm() is complete");
        return fioLayout;
    }

    private void createPhoneForm(){
        VerticalLayout phoneLayout = new VerticalLayout();
        fieldPhone = new TextField("Номер телефона:");
        fieldPhone.setWidth("300px");
        fieldPhone.setMaxLength(16);

        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldPhone.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить номер телефона!");
            } else {
                phoneLayout.removeFromParent();
                createInnForm();
            }
        });
        Button cancelButton = exit();
        phoneLayout.add(
                fieldPhone,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(phoneLayout);
        System.out.println("Method createPhoneForm() is complete");
    }

    private void createInnForm() {
        VerticalLayout innLayout = new VerticalLayout();
        fieldInn = new TextField("ИНН:");
        fieldInn.setWidth("300px");
        fieldInn.setMaxLength(12);

        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldInn.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить ИНН!");
            } else {
                innLayout.removeFromParent();
                createAddressForm();
            }
        });
        Button cancelButton = exit();
        innLayout.add(
                fieldInn,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(innLayout);
        System.out.println("Method createInnForm() is complete");
    }
    private void createAddressForm() {
        VerticalLayout addressLayout = new VerticalLayout();
        fieldAddress = new TextField("Место жительства:");
        fieldAddress.setWidth("300px");
        fieldAddress.setMaxLength(100);

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

        addressLayout.add(
                fieldAddress,
                new HorizontalLayout(saveButton, cancelButton)
        );
        add(addressLayout);
        System.out.println("Class createClient complete is successfully!");
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
