package com.crud.eDealer;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class eDealerUI extends UI {


    private VerticalLayout vl;
    private Label profit = new Label("0.0");

    private  DateField buyDate = new DateField("DATA TRANSAKCJI");
    private  ComboBox buyCurrency = new ComboBox("WALUTA");

    private  DateField sellDate = new DateField("DATA TRANSAKCJI");
    private  ComboBox sellCurrency = new ComboBox("WALUTA");

    private TextField buyRate = new TextField("KURS");
    private TextField buyValue = new TextField("WARTOSC W PLN");
    private TextField buyQuantity = new TextField("ILOSC");

    private TextField sellRate = new TextField("KURS");
    private TextField sellValue = new TextField("WARTOSC W PLN");
    private TextField sellQuantity = new TextField("ILOSC");

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
        TextField bQuantity = buyQuantity;
        bQuantity.addValueChangeListener(event -> {buyQuantity.setValue(bQuantity.getValue());
        services.setBuyQuantity(Double.parseDouble(buyQuantity.getValue()));});
        DateField Date = buyDate;
        Date.addListener(event -> {buyDate.setValue(Date.getValue()); setBuyFields();});
        TextField bRate = buyRate;
        TextField bValue = buyValue;
        hl.addComponentsAndExpand(currency,bQuantity,Date,bRate,bValue);

        vl.addComponent(hl);
        Label header3 = new Label("SPRZEDAŻ");
        header3.addStyleName(ValoTheme.LABEL_BOLD);
        vl.addComponent(header3);
    }

    private void addSellTextFields() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");
        ComboBox currency = sellCurrency;
        currency.setItems("EUR","GBP","USD");
        currency.addSelectionListener(event -> {currency.setValue(buyCurrency.getValue());});
        TextField sQuantity = sellQuantity;
        sQuantity.addValueChangeListener(event -> {sellQuantity.setValue(sQuantity.getValue());
        services.setSellQuantity(Double.parseDouble(sellQuantity.getValue()));});
        DateField Date = sellDate;
        Date.addListener(event -> {sellDate.setValue(Date.getValue()); setSellFields();});
        TextField sRate = sellRate;
        TextField sValue = sellValue;
        formLayout.addComponentsAndExpand(currency, sQuantity, Date, sRate, sValue);

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
            buyValue.setValue(String.format("%.4f",services.calculatePurchaseValue()));
            sellValue.setValue(String.format("%.4f",services.calculateSellValue()));
            services.calculateProfit();
            profit.setValue(String.format("%.2f",services.getProfit()));


        });
    }

        public void setBuyFields() {
        nbpClient.setCurrencyCode(String.valueOf(buyCurrency.getValue()));
        nbpClient.setDate(String.valueOf(buyDate.getValue()));
        nbpClient.setURL(nbpClient.createUrl());
        buyRate.setValue(String.valueOf(nbpClient.getNbpRate().get(0).getRate()));
        services.setBuyRate(Double.parseDouble(buyRate.getValue()));
        buyValue.setValue(String.format("%.4f",services.calculatePurchaseValue()));
    }

    public void setSellFields() {
        nbpClient.setCurrencyCode(String.valueOf(sellCurrency.getValue()));
        nbpClient.setDate(String.valueOf(sellDate.getValue()));
        nbpClient.setURL(nbpClient.createUrl());
        sellRate.setValue(String.valueOf(nbpClient.getNbpRate().get(0).getRate()));
        services.setSellRate(Double.parseDouble(sellRate.getValue()));
        sellValue.setValue(String.format("%.4f",services.calculateSellValue()));
    }


    }




