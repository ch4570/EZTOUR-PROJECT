package com.devcamp.eztour.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/api.properties")
public class ImportAPI {
    @Value("${IMP.KEY}")
    private String IMP_KEY;
    @Value("${IMP.SECRET}")
    private String IMP_SECRET;

    public String getIMP_KEY(){
        return IMP_KEY;
    }

    public String getIMP_SECRET(){
        return IMP_SECRET;
    }
}
