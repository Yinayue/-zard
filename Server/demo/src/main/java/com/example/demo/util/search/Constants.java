package com.example.demo.util.search;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    static final String MODEL_PATH = "model";
    static final String URL = "jdbc:mysql://localhost:3306/degreeproject";
    static final String NAME = "root";
    static final String PASSWORD = "123456";
//    static final String Field = "address";


    static Map<String,String[]> getIOMap()
    {
        Map<String,String[]> IOMap = new HashMap<>();
//        IOMap.put("input",new String[] {"address","remark"});///以address, remark作为输入
        IOMap.put("input",new String[] {"address"});
        return IOMap;
    }

}
