package com.example.demo.util.predict;

import net.sf.json.JSONObject;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PMMLDemo {

//    public static void main(String[] args) throws Exception {
//        long startTime =  System.currentTimeMillis();
//
//        //String path = "src/resources/Tpot_Model.pmml";
//        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Tpot_Model.pmml";
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("x1", 1);
//        map.put("x2", 3);
//        map.put("x3", 9);
//        map.put("x4", 1);
//        map.put("x5", 1);
//        map.put("x6", 2003);
//        map.put("x7", 161);
//
//        predict(map, path);
//
//        long endTime =  System.currentTimeMillis();
//        long usedTime = (endTime-startTime)/1000;
//        System.out.println(usedTime+"s");
//    }

    public static Double predict(Map<String, Integer> irismap, String pathxml) throws Exception {
        PMML pmml;
        File file = new File(pathxml);
        Double result = null;
        InputStream inputStream = new FileInputStream(file);
        try (InputStream is = inputStream) {
            pmml = org.jpmml.model.PMMLUtil.unmarshal(is);

            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
            ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            Evaluator evaluator = (Evaluator) modelEvaluator;

            List<InputField> inputFields = evaluator.getInputFields();
            Map<FieldName, FieldValue> argements = new LinkedHashMap<>();
            for (InputField inputField : inputFields) {
                FieldName inputFieldName = inputField.getName();
                Object raeValue = irismap.get(inputFieldName.getValue());
                FieldValue inputFieldValue = inputField.prepare(raeValue);
                argements.put(inputFieldName, inputFieldValue);
            }

            Map<FieldName, ?> results = evaluator.evaluate(argements);
            List<TargetField> targetFields = evaluator.getTargetFields();
            for (TargetField targetField : targetFields) {
                FieldName targetFieldName = targetField.getName();
                Object targetFieldValue = results.get(targetFieldName);
//                System.out.println("target: " + targetFieldName.getValue());
                String targetFieldValue_str = targetFieldValue.toString();
                JSONObject json_Result = JSONObject.fromObject(targetFieldValue_str);
                result = (Double) json_Result.get("result");
                System.out.println(result);
                
            }
        }
        
        return result;
        
    }


}
