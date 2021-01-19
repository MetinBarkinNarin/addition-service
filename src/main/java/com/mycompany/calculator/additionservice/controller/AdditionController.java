package com.mycompany.calculator.additionservice.controller;

import com.mycompany.calculator.additionservice.domain.CalculationResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdditionController {

    public CalculationResult getCalculationResult(@RequestParam(value = "one") Integer one,
                                           @RequestParam(value = "two") Integer two);
}
