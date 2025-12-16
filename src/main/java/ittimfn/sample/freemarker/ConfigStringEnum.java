package ittimfn.sample.freemarker;

import lombok.Getter;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import freemarker.template.Template;
import freemarker.template.Configuration;
import freemarker.cache.StringTemplateLoader;

@Getter
public enum ConfigStringEnum {
    DEFAULT("${greeting}! ${username}!"); 
    
    private String templateString;

    private static Map<ConfigStringEnum, Configuration> confMap = new HashMap<ConfigStringEnum, Configuration>();

    private ConfigStringEnum(String template) {
        this.templateString = template;
    }
    
    public Template getTemplate() {
        Configuration config = null;
        try {
            if(confMap.containsKey(this)){
                config = confMap.get(this);
            } else {
                config = new Configuration(Configuration.VERSION_2_3_34);
                
                StringTemplateLoader loader = new StringTemplateLoader();
                loader.putTemplate(this.name(), this.getTemplateString());                
                config.setTemplateLoader(loader);

                config.setDefaultEncoding("UTF-8");
                config.setLogTemplateExceptions(false);
                config.setWrapUncheckedExceptions(true);
                confMap.put(this, config);
            }
            return config.getTemplate(this.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

