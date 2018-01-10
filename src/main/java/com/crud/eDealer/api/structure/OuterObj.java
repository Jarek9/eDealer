package com.crud.eDealer.api.structure;

import java.util.ArrayList;
import java.util.List;

public class OuterObj {

    private String table;

    private List<Rate> rates = new ArrayList<>();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
