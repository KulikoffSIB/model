import org.jpmml.evaluator.*;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Building a model evaluator from a PMML file
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream("Human_dress.pmml");
        Evaluator evaluator = new LoadingModelEvaluatorBuilder()
                .setLocatable(false)
                .setVisitors(new DefaultVisitorBattery())
                //.setOutputFilter(OutputFilters.KEEP_FINAL_RESULTS)
                .load(is)
                .build();

        evaluator.verify();

        // do here whatever u want to do with model with jpmml api

        // Printing input (x1, x2, .., xn) fields
        List<? extends InputField> inputFields = evaluator.getInputFields();
        inputFields.forEach(System.out::println);
        System.out.println("------------------------------");

        // Printing primary result (y) field(s)
        List<? extends TargetField> targetFields = evaluator.getTargetFields();
        targetFields.forEach(System.out::println);
        System.out.println("------------------------------");

        // Printing secondary result (eg. probability(y), decision(y)) fields
        List<? extends OutputField> outputFields = evaluator.getOutputFields();
        outputFields.forEach(System.out::println);
        System.out.println("------------------------------");

        evaluator = null;
    }
}
