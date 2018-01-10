package com.crud.eDealer.api;

import com.crud.eDealer.api.structure.Rate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MyService {

    private ApiNBPService apiNBPService;

    public double calculateCurrency(String currencyCode, int quantity) {

        List<Rate> rates = apiNBPService.getRatesFromNBP();

        Double mid = rates.stream()
                .filter(rate -> rate.getCode().equalsIgnoreCase(currencyCode))
                .map(rate -> rate.getMid())
                .findFirst()
                .orElseThrow(()-> new RuntimeException());

        return quantity / mid;
    }

}
