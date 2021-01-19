package com.mycompany.calculator.additionservice.controller;

import com.mycompany.calculator.additionservice.domain.Calculation;
import com.mycompany.calculator.additionservice.domain.CalculationResult;
import com.mycompany.calculator.additionservice.service.AdditionService;
import com.mycompany.calculator.additionservice.service.IAdditionService;
import com.sun.media.jfxmedia.Media;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/addition")
public class IAdditionController implements AdditionController {

    private IAdditionService iAdditionService;

    public IAdditionController(IAdditionService iAdditionService) {
        this.iAdditionService = iAdditionService;
    }


    @GetMapping(
            value = "/calculate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public CalculationResult getCalculationResult(@RequestParam(value = "one") Integer one,
                                                  @RequestParam(value = "two") Integer two) {

        return this.iAdditionService.addition(one, two);
    }

}
