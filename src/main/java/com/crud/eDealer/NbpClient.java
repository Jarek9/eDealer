package com.crud.eDealer;


import org.springframework.web.client.RestTemplate;

public class NbpClient {
    private final static RestTemplate REST_TEMPLATE = new RestTemplate();


    public static void main(String[] args) {
        new NbpClient().getNbpRate();
    }

    public void getNbpRate() {
        NbpParentDto nbpParentDto = REST_TEMPLATE.getForObject("http://api.nbp.pl/api/exchangerates/rates/A/EUR/2018-02-09?format=json", NbpParentDto.class);
        System.out.println(nbpParentDto);
    }
}
