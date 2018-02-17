package com.crud.eDealer;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class eDealerUI extends UI {

    private VerticalLayout vl;
    private Label profit = new Label("0.0");
    private  DateField buyDate = new DateField("DATA TRANSAKCJI");
    private  ComboBox buyCurrency = new ComboBox("WALUTA");

    private  DateField sellDate = new DateField("DATA TRANSAKCJI");
    private  ComboBox sellCurrency = new ComboBox("WALUTA");

    private TextField buyRate = new TextField("KURS");
    Services services = new Services();
    NbpClient nbpClient = new NbpClient();



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
        ComboBox currency = buyCurrency;
        currency.setItems("EUR","GBP","USD");
        currency.addSelectionListener(event -> { buyCurrency.setValue(currency.getValue());
        sellCurrency.setValue(currency.getValue());});
        TextField buyQuantity = new TextField("ILOSC");
        DateField Date = buyDate;
        Date.addListener(event -> {buyDate.setValue(Date.getValue());});
        TextField bRate = buyRate;
        TextField buyValue = new TextField("WARTOSC W PLN");
        hl.addComponentsAndExpand(currency,buyQuantity,Date,buyRate,buyValue);

        vl.addComponent(hl);
        Label header3 = new Label("SPRZEDAŻ");
        header3.addStyleName(ValoTheme.LABEL_BOLD);
        vl.addComponent(header3);
    }

    private void addSellTextFields() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");
        ComboBox currency = new ComboBox("WALUTA");
        currency.setItems("EUR","GBP","USD");
        currency.addSelectionListener(event -> {currency.setValue(buyCurrency.getValue());});
        TextField sellQuantity = new TextField("ILOSC");
        DateField Date = sellDate;
        TextField sellRate = new TextField("KURS");
        TextField sellValue = new TextField("WARTOSC W PLN");
        formLayout.addComponentsAndExpand(currency, sellQuantity, Date, sellRate, sellValue);

        vl.addComponent(formLayout);
    }

    private void addProfitLabels() {
        Label headerProfit = new Label("ZYSK/STRATA (PLN)");
        Label headerValue = profit;
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
            services.calculateProfit();
            profit.setValue(String.valueOf(services.getProfit()));
            nbpClient.setURL(createUrl());
            System.out.println("\n" + "Wysyłam zapytanie pod adres:" + "\n" + nbpClient.getURL() + "\n"
            + "\n" + "Kurs " + buyCurrency.getValue() + " z dnia: " + buyDate.getValue() + " wynosi: "
                    + nbpClient.getNbpRate());

        });
    }

    public String createUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append("http://api.nbp.pl/api/exchangerates/rates/A");
        builder.append("/");
        builder.append(buyCurrency.getValue());
        builder.append("/");
        builder.append(buyDate.getValue());
        return builder.toString();
    }


}
