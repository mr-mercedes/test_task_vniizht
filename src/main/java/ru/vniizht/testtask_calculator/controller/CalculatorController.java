package ru.vniizht.testtask_calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vniizht.testtask_calculator.model.OperationModel;
import ru.vniizht.testtask_calculator.service.CalculateService;

@Controller
public class CalculatorController {
    OperationModel operationModel = new OperationModel();

    @Autowired
    private CalculateService calculateService;

    @RequestMapping("/calculator")
    public String getCalculatorPage(Model model){
        model.addAttribute("operationModel",operationModel);
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="eval", method = RequestMethod.POST)
    public String eval(@ModelAttribute("operationModel")  OperationModel operationModel, Model model ){
        extractExpression(operationModel);
        model.addAttribute("result", calculateService.eval());
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="fibonacci", method = RequestMethod.POST)
    public String fibonacci(@ModelAttribute("operationModel")  OperationModel operationModel, Model model ){
        extractExpression(operationModel);
        int fibonacci = calculateService.fibonacci(operationModel);
        model.addAttribute("result", fibonacci != -1 ? fibonacci : "Error input number");
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="factorial", method = RequestMethod.POST)
    public String factorial(@ModelAttribute("operationModel")  OperationModel operationModel, Model model ){
        extractExpression(operationModel);
        Long factorial = calculateService.factorial(operationModel);
        model.addAttribute("result", factorial != -1 ? factorial : "Error input number");
        return "calculator";
    }


    @RequestMapping(value="/calculator", params="sqrt", method = RequestMethod.POST)
    public String sqrt(@ModelAttribute("operationModel")  OperationModel operationModel, Model model ){
        extractExpression(operationModel);
        double sqrt = calculateService.sqrt(operationModel);
        model.addAttribute("result", sqrt != -1 ? sqrt :"Error input number");
        return "calculator";
    }

    @RequestMapping(value="/calculator", params="power", method = RequestMethod.POST)
    public String power(@ModelAttribute("operationModel")  OperationModel operationModel, Model model ){
        extractExpression(operationModel);
        double power = calculateService.power(operationModel);
        model.addAttribute("result", power != -1 ? power : "Error input number");
        return "calculator";
    }

    private void extractExpression(OperationModel operationModel) {
        String expression = operationModel.getExpression();
        calculateService.setTokens(expression);
        calculateService.setPos(0);
    }

}
