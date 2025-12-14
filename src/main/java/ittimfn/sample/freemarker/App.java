package ittimfn.sample.freemarker;

import java.io.ObjectInputFilter.Config;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Hello world!
 *
 */
public class App {

    public static final String TEMPLATE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "DefaultTemplate.txt").toString();

    public static void main( String[] args ) {

        // 使えるのは<String, Object>のMapだけ
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("username", "ittimfn");
        dataModel.put("greeting", 
            LocalTime.now().isBefore(LocalTime.NOON)
                ? "おはようございます"
                : (LocalTime.now().isBefore(LocalTime.of(18, 0)) ? "こんにちは" : "こんばんは")
        );

        try {
            Template template = FreeMakerConfigFactory.getTemplate(
                Paths.get(System.getProperty("user.dir"), "src", "main", "resources"),
                "DefaultTemplate.txt");
            
            // 標準出力するだけなら下記で良いが、Stringにしたい場合はStringWriterを使う
            // template.process(dataModel, new OutputStreamWriter(System.out));
            try (StringWriter out = new StringWriter()) {
                template.process(dataModel, out);
                String result = out.toString();
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
