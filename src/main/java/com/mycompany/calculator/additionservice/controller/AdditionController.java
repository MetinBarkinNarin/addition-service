package com.mycompany.calculator.additionservice.controller;

import com.mycompany.calculator.additionservice.domain.CalculationResult;
import com.mycompany.calculator.additionservice.service.AdditionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/addition")
public class AdditionController implements IAdditionController {

    private AdditionService iAdditionServiceoooo;

    public AdditionController(AdditionService iAdditionServiceoooo) {
        this.iAdditionServiceoooo = iAdditionServiceoooo;
    }


    @GetMapping(
            value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public CalculationResult getCalculationResult(@RequestParam(value = "one") Integer one,
                                                  @RequestParam(value = "two") Integer two) {

        return this.iAdditionServiceoooo.addition(one, two);
    }

}
