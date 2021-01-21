package com.mycompany.calculator.additionservice.service;

import com.mycompany.calculator.additionservice.domain.CalculationResult;

public interface IAdditionService {

    CalculationResult addition(Integer numberOne, Integer numberTwo);
}
