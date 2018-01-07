package com.crud.eDealer;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class eDealerUI extends UI {

    private VerticalLayout vl;


    @Override
    protected void init(VaadinRequest request) {
        setupLayout();
        addHeaders();
        addBuyTextFields();
        addSellTextFields();
        addProfitLabels();
        addCalculateButton();
    }

    private void setupLayout() {
        vl =  new VerticalLayout();
        vl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(vl);

    }

    private void addHeaders() {
        Label header1 = new Label("€Dealer");
        Label header2 = new Label("KUPNO");
        header1.addStyleName(ValoTheme.LABEL_H2);
        header1.addStyleName(ValoTheme.LABEL_BOLD);
        header2.addStyleName(ValoTheme.LABEL_BOLD);
        vl.addStyleName("black");
        vl.addComponents(header1,header2);
    }


    private void addBuyTextFields() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setWidth("80%");
        ComboBox buyCurrency = new ComboBox("WALUTA");
        buyCurrency.setItems("EUR","GBP","USD");
        TextField buyQuantity = new TextField("ILOSC");
        DateField buyDate = new DateField("DATA TRANSAKCJI");
        TextField buyRate = new TextField("KURS");
        TextField buyValue = new TextField("WARTOSC W PLN");
        hl.addComponentsAndExpand(buyCurrency,buyQuantity,buyDate,buyRate,buyValue);

        vl.addComponent(hl);
        Label header3 = new Label("SPRZEDAŻ");
        header3.addStyleName(ValoTheme.LABEL_BOLD);
        vl.addComponent(header3);
    }

    private void addSellTextFields() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");
        ComboBox sellCurrency = new ComboBox("WALUTA");
        sellCurrency.setItems("EUR","GBP","USD");
        TextField sellQuantity = new TextField("ILOSC");
        DateField sellDate = new DateField("DATA TRANSAKCJI");
        TextField sellRate = new TextField("KURS");
        TextField sellValue = new TextField("WARTOSC W PLN");
        formLayout.addComponentsAndExpand(sellCurrency, sellQuantity, sellDate, sellRate, sellValue);

        vl.addComponent(formLayout);
    }

    private void addProfitLabels() {
        Label headerProfit = new Label("ZYSK/STRATA (PLN)");
        Label headerValue = new Label("0,00");
        headerProfit.addStyleName(ValoTheme.LABEL_BOLD);
        headerValue.addStyleName(ValoTheme.LABEL_BOLD);
        headerValue.addStyleName(ValoTheme.LABEL_H2);
        vl.addComponents(headerProfit,headerValue);
    }

    private void addCalculateButton() {

        Button calculate = new Button("PRZELICZ");
        calculate.addStyleName(ValoTheme.BUTTON_PRIMARY);
        vl.addComponent(calculate);
        calculate.addClickListener(event -> {
            vl.addComponent(new Label("Brak danych do wykonania obliczeń"));
        });

    }


}
