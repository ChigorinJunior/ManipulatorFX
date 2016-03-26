package sample.evaluators;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.functions.CustomFunctionsFactory;

import java.util.HashMap;

public class ExpressionEvaluator {
    public static double evaluateExpression(String expression, HashMap<String, Double> substitution) {
        Expression expression1 = new ExpressionBuilder(expression)
                .variables(substitution.keySet())
                .functions(new CustomFunctionsFactory().getCustomFunctions())
                .build();

        for (String variable: substitution.keySet()) {
            expression1.setVariable(variable, substitution.get(variable));
        }

        return expression1.evaluate();
    }
}
