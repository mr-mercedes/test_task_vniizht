package ru.vniizht.testtask_calculator.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import ru.vniizht.testtask_calculator.model.OperationModel;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {

    protected OperationModel operationModel = new OperationModel("1+2*(3+4/2-4(1+2))*2+1");
    protected CalculateService calculateService;

    public CalculateServiceTest(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @Before
    public void setUp(){
        calculateService.setTokens(operationModel.getExpression());
        calculateService.setPos(0);
    }
    @Test
    public void eval() {
        assertEquals(10, Integer.parseInt(calculateService.eval()));
    }

    @Test
    public void fibonacci() {
    }

    @Test
    public void factorial() {
    }

    @Test
    public void sqrt() {
    }

    @Test
    public void power() {
    }
}