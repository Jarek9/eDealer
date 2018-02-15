package com.crud.eDealer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
public class Services {

    private double profit=1;

    private double rate;

    public Services() {

    }

    public double getRate() {


        return rate;
    }

    public double calculateProfit() {
        profit = profit + 1;
        return profit;
    }

}
