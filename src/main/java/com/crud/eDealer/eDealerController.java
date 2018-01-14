package com.crud.eDealer;

import com.crud.eDealer.api.MyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
//@RequestMapping("/v1/edealer")
public class eDealerController {

    private MyService myService;

    @RequestMapping(method = RequestMethod.GET, value = "getRate")
    public Double getRate(@RequestParam String code, @RequestParam Integer quantity) {
        return myService.calculateCurrency(code, quantity);
    }
}
