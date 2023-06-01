package org.iismagilov.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.iismagilov.controller.Account;
import org.iismagilov.controller.Client;
import org.iismagilov.model.QueryDAO;

import java.text.RuleBasedCollator;

@Route(value="createAccount", layout = MainLayout.class)
@PageTitle("Создание счета | Vaadin App by Ilyas")
public class AccountCreateView extends VerticalLayout{

    public AccountCreateView(){
        initCreateAccount();
    }
    private VerticalLayout formLayout;
    private TextField fieldAccount;
    private TextField fieldBik;
    private String fieldСurrency;
    private Integer idClient;

    private void initCreateAccount() {
        formLayout = new VerticalLayout();
        System.out.println("Method initCreateAccount() is START!");
        H1 text = new H1("Окно создания счета:");
        ComboBox<Client> comboBox = new ComboBox<>("Для какого клиента создаем счет?");
        comboBox.setWidth("400px");
        comboBox.setAllowCustomValue(true);
        comboBox.setPlaceholder("Выберите клиента...");
        comboBox.setItems(QueryDAO.getClients());
        comboBox.setItemLabelGenerator(selectClient -> {
            idClient = selectClient.getId();
            return selectClient.getSurName() + " " + selectClient.getFirstName() + " " + selectClient.getLastName();
        });
        Button selectButton = new Button("Выбрать", click -> {
            if (comboBox.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо выбрать клиента!");
            } else {
                createAccountForm();
                formLayout.removeFromParent();
            }
        });

        Button returnButton = exit();

        formLayout.add(
                comboBox,
                new HorizontalLayout(selectButton, returnButton)
        );
        add(
                text,
                formLayout
        );
        System.out.println("Method initCreateAccount() is FINISH!");
    }

    private Component createAccountForm(){
        VerticalLayout accountLayout = new VerticalLayout();

        fieldAccount = new TextField("Номер счета:");
        fieldAccount.setWidth("300px");
        fieldAccount.setMaxLength(30);

        Button cancelButton = exit();
        Button nextButton = new Button("Далее", click -> {
            if (fieldAccount.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить Номер счета!");
            }  else {
                accountLayout.removeFromParent();
                createBikForm();
            }
        });

        accountLayout.add(
                fieldAccount,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(accountLayout);

        System.out.println("Method createAccountForm() is complete");
        return accountLayout;
    }

    private void createBikForm(){
        VerticalLayout bikLayout = new VerticalLayout();
        fieldBik = new TextField("БИК:");
        fieldBik.setWidth("300px");
        fieldBik.setMaxLength(20);

        Button nextButton = new Button("Далее", click -> {
            if (fieldBik.isEmpty()){
                notification("ВНИМАНИЕ! Необходимо заполнить БИК!");
            } else {
                bikLayout.removeFromParent();
                createCurrencyForm();
            }
        });
        Button cancelButton = exit();
        bikLayout.add(
                fieldBik,
                new HorizontalLayout(nextButton, cancelButton)
        );
        add(bikLayout);
        System.out.println("Method createBikForm() is complete");
    }

    public void createCurrencyForm() {
        VerticalLayout currencyLayout = new VerticalLayout();
        ComboBox<Account.Currency> currencyComboBox = new ComboBox<>();
        currencyComboBox.setLabel("Валюта:");
        currencyComboBox.setItems(Account.Currency.values());
        currencyComboBox.setWidth("400px");
        currencyComboBox.setAllowCustomValue(true);
        currencyComboBox.setPlaceholder("Выберите валюту...");
        currencyComboBox.setItemLabelGenerator(selectCurrency -> {
            fieldСurrency = selectCurrency.name();
            return fieldСurrency;
        });
        Button saveButton = new Button("Сохранить", click -> {
            System.out.println("Method createCurrencyForm(): fieldСurrency = "+ fieldСurrency);
            if (currencyComboBox.isEmpty()){
                    notification("ВНИМАНИЕ! Необходимо выбрать тип валюты!");
            } else {
                    QueryDAO.insertAccount(
                            idClient,
                            fieldAccount.getValue(),
                            fieldBik.getValue(),
                            fieldСurrency
                    );
                    notification("Успешное создание счета!");
                    getUI().ifPresent(ui ->
                            ui.navigate("accountMenu"));
                }
        });
        Button cancelButton = exit();

        currencyLayout.add(
                currencyComboBox,
                new HorizontalLayout(saveButton, cancelButton)
            );
            add(currencyLayout);
            System.out.println("Method AccountCreateView() is FINISH!");

    }
    private Button exit(){
        Button cancelButton = new Button("Отменить",click ->
                getUI().ifPresent(ui ->
                        ui.navigate("accountMenu")));
        return cancelButton;
    }

    private void notification(String text){
        Notification notification = Notification.show(text);
        notification.setPosition(Notification.Position.MIDDLE);
    }
}
