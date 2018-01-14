package com.crud.eDealer.api;

import com.crud.eDealer.api.structure.OuterObj;
import com.crud.eDealer.api.structure.Rate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApiNBPService {

    private final static RestTemplate REST_TEMPLATE = new RestTemplate();

    private final static String URL = "http://api.nbp.pl/api/exchangerates/tables/A";


    public List<Rate> getRatesFromNBP() {

        log.info("todo: write some comment");

        OuterObj[] outerObjs = REST_TEMPLATE.getForObject(URL, OuterObj[].class);

        return Optional.ofNullable(outerObjs[0].getRates()).orElse(new ArrayList<>());
    }

}
