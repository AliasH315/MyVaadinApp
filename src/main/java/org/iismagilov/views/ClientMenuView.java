package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.model.QueryDAO;

@Route(value="clientMenu", layout = MainLayout.class)
@PageTitle("Работа с клиентами | Vaadin App by Ilyas")
public class ClientMenuView extends VerticalLayout {

    public ClientMenuView() {
        initClientMenuView();
    }

    private Component initClientMenuView() {
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
            ClientAddView clientAddView = new ClientAddView();
            add(
                    clientAddView.createClient()
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
        return clientLayout;
    }
}
