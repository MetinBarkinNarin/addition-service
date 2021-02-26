package com.mycompany.calculator.additionservice.controller;

import com.mycompany.calculator.additionservice.domain.CalculationResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestParam;

@Api(value = "REST API for addition service information.")
public interface IAdditionController {

    public CalculationResult getCalculationResult(@RequestParam(value = "one") Integer one,
                                                  @RequestParam(value = "two") Integer two);
}
