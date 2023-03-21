package ru.vniizht.testtask_calculator.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.vniizht.testtask_calculator.model.OperationModel;

import java.util.Objects;


@Data
@Service
public class CalculateService {
    private String[] tokens;
    private int pos = 0;

    public void setTokens(String expressions) {
        String reg = "((?<=[+|*|\\-|/|(|)])|(?=[+|*|\\-|/|(|)]))";
        String s = expressions.replaceAll("\\s", "");
        String[] split = s.split(reg);
        if (split[split.length - 1].matches("\\-|\\+|\\*|\\/")){
            this.tokens = new String[]{split[split.length - 1]};
            return;
        }
        this.tokens = split;
    }

    public String eval() {
        try {
            return String.valueOf(calculate());
        } catch (NumberFormatException ex) {
            return ex.getMessage();
        }
    }

    private double calculate() {
        if (Objects.equals(tokens[0], "-")) {
            tokens[1] = "-" + tokens[1];
            pos = 1;
        }
        double first = multiply();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }

            double second = multiply();
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }

        }
        return first;
    }

    private double multiply() {
        double first = factor();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }

            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    private double factor() {
        String next = tokens[pos];
        double result = 0;
        if (next.equals("(")) {
            pos++;
            result = calculate();
            String closingBracket;
            if (pos < tokens.length) {
                closingBracket = tokens[pos];
            } else {
                throw new IllegalArgumentException("Unexpected end of expression");
            }

            if (closingBracket.equals(")")) {
                pos++;
                return result;
            } else {
                throw new IllegalArgumentException("')' expected but " + closingBracket + " found");
            }
        }
        pos++;
        return Double.parseDouble(next);
    }

    public int fibonacci(OperationModel model) {
        int number = 0;
        try {
            number = Integer.parseInt(model.getExpression());
        } catch (NumberFormatException ex) {
            return -1;
        }
        return fibonacci(number);
    }

    private int fibonacci(int n) {
        if (n == 0 || n < 0) return 0;
        else if (n == 1 || n == 2) return 1;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public Long factorial(OperationModel model) {
        Long number = 0L;
        try {
            number = Long.parseLong(model.getExpression());
        } catch (NumberFormatException ex) {
            return -1L;
        }
        return factorial(number);
    }

    private Long factorial(Long n) {
        return n < 2 ? n : n * factorial(n - 1);
    }

    public double sqrt(OperationModel model) {
        double number = 0;
        try {
            number = Double.parseDouble(model.getExpression());
            if (number < 0) return 0;
        } catch (NumberFormatException ex) {
            return -1;
        }
        return Math.sqrt(number);
    }

    public double power(OperationModel model) {
        double number = 0;
        try {
            number = Double.parseDouble(model.getExpression());
        } catch (NumberFormatException ex) {
            return -1;
        }
        return number * number;
    }


}
