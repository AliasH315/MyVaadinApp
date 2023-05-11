package org.iismagilov.views;

        import com.vaadin.flow.component.Key;
        import com.vaadin.flow.component.button.*;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.textfield.TextField;
        import com.vaadin.flow.component.dialog.Dialog;
        import com.vaadin.flow.router.Route;
        import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        VerticalLayout mainLayout = new VerticalLayout();
        Button buttonMenuClient = new Button("Работа с клиентами");
        buttonMenuClient.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonMenuAccount = new Button("Работа со счетами");
        buttonMenuAccount.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonReturnMainMenu = new Button("Выход из программы");
        buttonReturnMainMenu.addThemeVariants(ButtonVariant.LUMO_LARGE);

        buttonMenuClient.addClickListener(click -> {
            ClientMenuView clientMenuView = new ClientMenuView();
        });
        buttonMenuClient.addClickShortcut(Key.ENTER);

        buttonMenuAccount.addClickListener(click -> {
            AccountMenuView accountMenuView = new AccountMenuView();
        });
        buttonMenuAccount.addClickShortcut(Key.ENTER);

        buttonReturnMainMenu.addClickListener(click -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Вы точно хотите выйти?");
            HorizontalLayout dialogLayout = new HorizontalLayout();
            Button yesButton = new Button("Да", (e) -> dialog.close());
            Button cancelButton = new Button("Нет", (e) -> dialog.close());
            add(dialogLayout,yesButton,cancelButton);

            //yesButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            //cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            //dialog.getFooter().add(yesButton);
            //dialog.getFooter().add(cancelButton);
            //yesButton.addClickShortcut(Key.ENTER);
            //cancelButton.addClickShortcut(Key.ENTER);
            //dialog.open();
        });
        add(
                new H1("Ismagilov App"),
                mainLayout,
                new H1("Меню банка:"),
                mainLayout,
                        buttonMenuClient,
                        buttonMenuAccount,
                        buttonReturnMainMenu

        );
    }
}