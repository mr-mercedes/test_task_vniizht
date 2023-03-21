package ru.vniizht.testtask_calculator.service;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.vniizht.testtask_calculator.model.OperationModel;

import static org.junit.jupiter.api.Assertions.*;

class CalculateServiceTest {

    static OperationModel operationModel = new OperationModel();
    static CalculateService calculateService = new CalculateService();
    private final static String TEST_NUMBER = "7";
    private final static String TEST_EXPRESSION = "1+2*(3+4/2-(1+2))*2+1";


    @BeforeAll
    public static void setUp() {
        operationModel.setExpression(TEST_NUMBER);
        calculateService.setPos(0);

    }

    @Test
    public void eval() {
        prepareEval();
        Double evalNumber = Double.parseDouble(calculateService.eval());
        assertEquals(10, evalNumber);
    }

    private void prepareEval(){
        operationModel.setExpression(TEST_EXPRESSION);
        calculateService.setTokens(operationModel.getExpression());
    }

    @Test
    public void fibonacci() {
        int fibonacci = calculateService.fibonacci(operationModel);
        assertEquals(13, fibonacci);
    }

    @Test
    public void factorial() {
        Long factorial = calculateService.factorial(operationModel);
        assertEquals(5040, factorial);
    }

    @Test
    public void sqrt() {
        operationModel.setExpression(TEST_NUMBER);
        calculateService.setPos(0);
        Double sqrt = calculateService.sqrt(operationModel);
        assertEquals((Double) Math.sqrt(7), sqrt);
    }

    @Test
    public void power() {
        operationModel.setExpression(TEST_NUMBER);
        calculateService.setPos(0);
        Double power = calculateService.power(operationModel);
        assertEquals((Double) Math.pow(7, 2), power);
    }
}