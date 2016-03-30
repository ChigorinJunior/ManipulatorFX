package sample;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.functions.CustomFunctionsFactory;

import java.util.HashMap;

public class Evaluator {

    public static Expression buildExpression(String expression, HashMap<String, Double> substitution) {
        return new ExpressionBuilder(expression)
                .variables(substitution.keySet())
                .functions(new CustomFunctionsFactory().getCustomFunctions())
                .build();
    }

    public static double evaluateExpression(Expression expression, HashMap<String, Double> substitution) {
        for (String variable: substitution.keySet()) {
            expression.setVariable(variable, substitution.get(variable));
        }

        return expression.evaluate();
    }
}
