package com.crud.eDealer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
@Setter
public class Services {

    private double buyQuantity;
    private double buyRate;
    private double purchaseValue;

    private double sellQuantity;
    private double sellRate;
    private double sellValue;

    private double profit;



    public Services() {

    }


    public double calculateProfit() {
        profit = (sellQuantity * sellRate) - (sellQuantity * buyRate);

        return profit;
    }

    public double calculatePurchaseValue() {
        purchaseValue = buyQuantity * buyRate;
        return purchaseValue;
    }

    public double calculateSellValue() {
        sellValue = sellQuantity * sellRate;
        return sellValue;
    }

}
