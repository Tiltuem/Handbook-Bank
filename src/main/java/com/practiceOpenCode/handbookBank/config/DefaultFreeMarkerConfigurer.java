package com.practiceOpenCode.handbookBank.config;

import freemarker.ext.jsp.TaglibFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class DefaultFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Override
    public TaglibFactory getTaglibFactory() {
        TaglibFactory tagLibFactory = super.getTaglibFactory();

        if (tagLibFactory.getObjectWrapper()==null) {
            tagLibFactory.setObjectWrapper(super.getConfiguration().getObjectWrapper());
        }

        return tagLibFactory;
    }
}