package com.crud.eDealer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Getter
@Setter
public class NbpClient {
    private final static RestTemplate REST_TEMPLATE = new RestTemplate();
    private String URL;

    public List<NbpDto> getNbpRate() {
        NbpParentDto nbpParentDto  = REST_TEMPLATE.getForObject(URL, NbpParentDto.class);
        return Optional.ofNullable(nbpParentDto.getRates()).orElse(new ArrayList<>());
    }


}
