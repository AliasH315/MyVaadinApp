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
        init();
    }

    private void init(){
        QueryDAO.getClients();
        System.out.println("Initializing Clients is complete");
        initClientMenuView();
    }
    private Component initClientMenuView() {
        VerticalLayout clientLayout = new VerticalLayout();
        Button buttonClientAdd = new Button("Создать клиента");
        buttonClientAdd.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonClientBrowse = new Button("Просмотреть клиента");
        buttonClientBrowse.addThemeVariants(ButtonVariant.LUMO_LARGE);
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
                buttonClientBrowse,
                buttonClientUpdate,
                buttonClientDelete,
                buttonReturn
        );

        buttonClientAdd.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate("createClient"));
        });

        buttonClientBrowse.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate("browseClient"));
        });
        // Не реализовано
        buttonClientUpdate.addClickListener(click -> {

        });
        buttonClientDelete.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate("deleteClient"));
        });

        buttonReturn.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate(""));
        });
        return clientLayout;
    }

}
