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
import org.iismagilov.views.MainLayout;

import java.lang.reflect.Field;

@Route(value="test")
@PageTitle("Тестирование | Vaadin App by Ilyas")
public class Test {
    public Component createClient() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setMargin(true);

        formLayout.add(
                new H1("Окно создания клиента:"),
                createFIOForm()
        );
        System.out.println("Method createClient() is complete");
        return formLayout;
    }

    private Component createFIOForm(){
        System.out.println("Method createFIOForm() is begin");
        VerticalLayout layoutFIO = new VerticalLayout();

        TextField fieldFirstName = new TextField("Имя клиента:");
        fieldFirstName.setWidth("300px");

        TextField fieldSurName = new TextField("Фамилия клиента:");
        //fieldSurName.setRequiredIndicatorVisible(true);
        fieldSurName.setWidth("300px");

        TextField fieldLastName = new TextField("Отчество клиента:");
        fieldLastName.setWidth("300px");
        Button cancelButton = exit(layoutFIO);
        Button nextButton = new Button("Далее", (Next) -> {
            if (fieldFirstName.isEmpty() || fieldSurName.isEmpty() || fieldLastName.isEmpty()){
                Notification notification = Notification.show("ВНИМАНИЕ!Необходимо заполнить все поля!");
                notification.setPosition(Notification.Position.MIDDLE);
            }  else {
                layoutFIO.removeFromParent();
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

    private Button exit(Component layout){
        Button cancelButton = new Button("Отменить",(Exit) -> {
            layout.removeFromParent();
        });
        return cancelButton;
    }
}


