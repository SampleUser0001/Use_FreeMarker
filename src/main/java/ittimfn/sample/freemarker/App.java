package ittimfn.sample.freemarker;

import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


import ittimfn.sample.freemarker.controller.*;

/**
 * Hello world!
 *
 */
public class App {

    public static final String TEMPLATE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "DefaultTemplate.txt").toString();

    public static void main( String[] args ) throws Exception {

        // 使えるのは<String, Object>のMapだけ
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("username", "ittimfn");
        dataModel.put("greeting", 
            LocalTime.now().isBefore(LocalTime.NOON)
                ? "おはようございます"
                : (LocalTime.now().isBefore(LocalTime.of(18, 0)) ? "こんにちは" : "こんばんは")
        );


        FileTemplate file = new FileTemplate(ConfigDirEnum.DEFAULT, dataModel);
        System.out.println(file.getResult());

        StringTemplate string = new StringTemplate(ConfigStringEnum.DEFAULT, dataModel);
        System.out.println(string.getResult());
        
    }
}
