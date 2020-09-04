package com.example.service;

import com.example.model.OperationModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculateSimpleTest {

    CalculateSimple calc = new CalculateSimple();

    @Test
    public void testAdd() {
        OperationModel operationModel = new OperationModel(5, 4);
        int expResult = 9;
        int result = calc.add(operationModel);
        assertThat(result, Matchers.equalTo(expResult));
    }

    @Test
    public void testSubtract() {
        OperationModel operationModel = new OperationModel(-8, 2);
        int expResult = -10;
        int result = calc.subtract(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testMultiply() {
        OperationModel operationModel = new OperationModel(7, -5);
        int expResult = -35;
        int result = calc.multiply(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testDivide() {
        OperationModel operationModel = new OperationModel(14, 4);
        double expResult = 3.5;
        double result = calc.divide(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testFactorial() {
        OperationModel operationModel = new OperationModel(5);
        int expResult = 120;
        int result = calc.factorial(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testFibonacci() {
        OperationModel operationModel = new OperationModel(7);
        int expResult = 13;
        int result = calc.fibonacci(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testSqrt() {
        OperationModel operationModel = new OperationModel(9);
        double expResult = 3;
        double result = calc.sqrt(operationModel);
        assertThat(result, Matchers.equalTo(expResult));
    }

    @Test
    public void testPower() {
        OperationModel operationModel = new OperationModel(10);
        int expResult = 100;
        int result = calc.power(operationModel);
        assertThat(result, Matchers.equalTo(expResult));

    }

    @Test
    public void testClearSimple() {
        OperationModel expResult = new OperationModel(0, 0);
        OperationModel result = calc.clearSimple(new OperationModel(""));

        assertThat(result, Matchers.isA(OperationModel.class));
        assertThat(result.getA(), Matchers.equalTo(expResult.getA()));
        assertThat(result.getB(), Matchers.equalTo(expResult.getB()));
    }

    @Test
    public void testClearAdvanced() {
        int expResult = new OperationModel(0).getC();
        int result = calc.clearAdvanced(new OperationModel("")).getC();
        assertThat(result, Matchers.equalTo(expResult));
    }

}
