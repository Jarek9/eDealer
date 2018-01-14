package com.crud.eDealer.api;

import com.crud.eDealer.api.structure.Rate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiNBPServiceTest {

    @Autowired
    private ApiNBPService apiNBPService;

    @Test
    public void getRatesFromNBP() throws Exception {

        //when
        List<Rate> rates = apiNBPService.getRatesFromNBP();

        //then
        assertTrue(rates.size() > 1);
    }

}