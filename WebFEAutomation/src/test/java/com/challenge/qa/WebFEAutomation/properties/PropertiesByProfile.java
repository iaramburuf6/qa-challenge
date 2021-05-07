package com.challenge.qa.WebFEAutomation.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesByProfile extends Properties {

    /**
     * Serial version
     */
    private static final long serialVersionUID = 1L;

    /**
     * System variable key
     */
    private static final String PROFILE_PROPERTY_KEY = "profile";


    public PropertiesByProfile(String name) throws IOException {
        String profile = System.getProperty(PROFILE_PROPERTY_KEY);
        String targetFilename = null;

        if (StringUtils.isBlank(profile)) {
            targetFilename = "/" + name + ".properties";
        } else {
            targetFilename = "/" + name + "-" + profile + ".properties";
        }

        try (InputStream inputStream = PropertiesByProfile.class.getResourceAsStream(targetFilename)) {
            load(inputStream);
        }
    }
}
