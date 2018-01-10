package com.crud.eDealer;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/edealer")
public class eDealerController {

    @RequestMapping(method = RequestMethod.GET, value = "getRate")
    public Service getRate() {
        return null;
    }
}
