package org.iismagilov.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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

        buttonReturn.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate(""));
        });
        add(
                new H1("Операции с клиентами:"),
                clientLayout,
                    buttonClientAdd,
                    buttonClientUpdate,
                    buttonClientDelete,
                    buttonReturn
        );
    }
}
