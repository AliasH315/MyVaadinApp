package org.iismagilov.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="accountMenu", layout = MainLayout.class)
@PageTitle("Работа со счетами | Vaadin App by Ilyas")
public class AccountMenuView extends VerticalLayout {
    public AccountMenuView() {
        VerticalLayout accountLayout = new VerticalLayout();
        Button buttonOpenAccount = new Button("Открыть счет клиенту");
        buttonOpenAccount.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonUpdateAccount = new Button("Редактировать счет клиента");
        buttonUpdateAccount.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonCloseAccount = new Button("Закрыть счет клиенту");
        buttonCloseAccount.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonSaveMoney = new Button("Зачислить деньги");
        buttonSaveMoney.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonTransferMoney = new Button("Перевести деньги");
        buttonTransferMoney.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonReturn = new Button("Вернуться назад");
        buttonReturn.addThemeVariants(ButtonVariant.LUMO_LARGE);

        buttonOpenAccount.addClickListener(click ->
            getUI().ifPresent(ui ->
                    ui.navigate("createAccount")));

        buttonUpdateAccount.addClickListener(click ->
                getUI().ifPresent(ui ->
                        ui.navigate("updateAccount")));

        buttonCloseAccount.addClickListener(click ->
                getUI().ifPresent(ui ->
                        ui.navigate("deleteAccount")));

        buttonReturn.addClickListener(click ->
            getUI().ifPresent(ui ->
                    ui.navigate("")));

        add(
                new H1("Операции со счетами:"),
                accountLayout,
                    buttonOpenAccount,
                    buttonUpdateAccount,
                    buttonCloseAccount,
                    buttonSaveMoney,
                    buttonTransferMoney,
                    buttonReturn
        );

    }
}

