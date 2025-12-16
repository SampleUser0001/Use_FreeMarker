package ittimfn.sample.freemarker.controller;

import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Template;

import ittimfn.sample.freemarker.ConfigDirEnum;

public class FileTemplate {
    
    private ConfigDirEnum en;
    private Map<String, Object> dataModel;
    
    
    public FileTemplate(ConfigDirEnum en, Map<String, Object> dataModel) {
        this.en = en;
        this.dataModel = dataModel;
    }

    public String getResult() throws Exception {
        try {
            Template template = en.getTemplate();
            
            // 標準出力するだけなら下記で良いが、Stringにしたい場合はStringWriterを使う
            // template.process(dataModel, new OutputStreamWriter(System.out));
            try (StringWriter out = new StringWriter()) {
                template.process(dataModel, out);
                String result = out.toString();
                return result;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
