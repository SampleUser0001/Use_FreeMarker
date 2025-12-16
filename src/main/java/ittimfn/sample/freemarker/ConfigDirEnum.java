package ittimfn.sample.freemarker;

import lombok.Getter;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import freemarker.template.Template;
import freemarker.template.Configuration;

@Getter
public enum ConfigDirEnum {
    DEFAULT(Paths.get(System.getProperty("user.dir"), "src", "main", "resources"), "DefaultTemplate.txt"); 
    
    private File dir;
    private String name;

    private static Map<File, Configuration> confMap = new HashMap<File, Configuration>();

    private ConfigDirEnum(Path path, String name) {
        this.dir = path.toFile();
        this.name = name;
    }
    
    public Template getTemplate() {
        Configuration config = null;
        try {
            if(confMap.containsKey(this.getDir())){
                config = confMap.get(this.getDir());
            } else {
                config = new Configuration(Configuration.VERSION_2_3_34);
                config.setDirectoryForTemplateLoading(this.getDir());
                config.setDefaultEncoding("UTF-8");
                config.setLogTemplateExceptions(false);
                config.setWrapUncheckedExceptions(true);
                confMap.put(this.getDir(), config);
            }
            return config.getTemplate(this.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

