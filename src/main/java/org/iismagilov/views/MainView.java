package org.iismagilov.views;

        import com.vaadin.flow.component.Key;
        import com.vaadin.flow.component.button.*;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.dialog.Dialog;
        import com.vaadin.flow.router.Route;

@Route(value="", layout = MainLayout.class)
//@CssImport("./styles/views/main/main-view.css")
public class MainView extends VerticalLayout {
    public MainView() {
        VerticalLayout mainLayout = new VerticalLayout();
        Button buttonClientMenu = new Button("Работа с клиентами");
        buttonClientMenu.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonAccountMenu = new Button("Работа со счетами");
        buttonAccountMenu.addThemeVariants(ButtonVariant.LUMO_LARGE);
        Button buttonReturnMainMenu = new Button("Выход из программы");
        buttonReturnMainMenu.addThemeVariants(ButtonVariant.LUMO_LARGE);

        buttonClientMenu.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate("/clientMenu"));
        });
        buttonClientMenu.addClickShortcut(Key.ENTER);

        buttonAccountMenu.addClickListener(click -> {
            getUI().ifPresent(ui ->
                    ui.navigate("/accountMenu"));
        });
        buttonAccountMenu.addClickShortcut(Key.ENTER);

        buttonReturnMainMenu.addClickListener(click -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Вы точно хотите выйти?");
            Button yesButton = new Button("Да", (Yes) -> dialog.close());
            Button cancelButton = new Button("Нет", (No) -> dialog.close());
            dialog.getFooter().add(yesButton,cancelButton);
            dialog.open();

            //yesButton.addClickShortcut(Key.ENTER);
            //cancelButton.addClickShortcut(Key.ENTER);
            //dialog.open();
        });
        add(
                new H1("Основное меню:"),
                mainLayout,
                    buttonClientMenu,
                    buttonAccountMenu,
                    buttonReturnMainMenu
        );
    }
}