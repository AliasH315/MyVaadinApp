package org.iismagilov.controller;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.iismagilov.model.QueryDAO;

import java.util.Set;

public class DeleteClient extends VerticalLayout {


    public void deleteClient() {
        ComboBox<Client> comboBox = new ComboBox<>("Выберите клиента для удаления:");
        comboBox.setItems(QueryDAO.getClients());
            comboBox.setItemLabelGenerator(p -> {
                return p.getFirstName() + " " + p.getSurName()+" " + p.getLastName();
            });
        add(comboBox);
        }
}
