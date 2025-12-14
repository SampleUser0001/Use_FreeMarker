package ittimfn.sample.freemarker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerConfigFactory {
    
    private static final Map<Path, Configuration> cfgMap = new HashMap<>();

    public static Template getTemplate(Path templateDir, String filename) {
        Configuration cfg = null;
        try {
            if(cfgMap.containsKey(templateDir)){
                cfg = cfgMap.get(templateDir);
            } else {
                cfg = new Configuration(Configuration.VERSION_2_3_34);
                cfg.setDirectoryForTemplateLoading(templateDir.toFile());
                cfg.setDefaultEncoding("UTF-8");
                cfg.setLogTemplateExceptions(false);
                cfg.setWrapUncheckedExceptions(true);
                cfgMap.put(templateDir, cfg);
            }
            return cfg.getTemplate(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}