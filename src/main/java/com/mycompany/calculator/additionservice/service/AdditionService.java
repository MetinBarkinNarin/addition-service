package com.mycompany.calculator.additionservice.service;

import com.mycompany.calculator.additionservice.domain.Calculation;
import com.mycompany.calculator.additionservice.domain.CalculationResult;

import java.util.Optional;

public interface AdditionService {

    CalculationResult addition(Integer numberOne,Integer numberTwo);
}
