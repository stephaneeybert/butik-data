package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.spring.AbstractPropertiesVerifier;

import org.springframework.stereotype.Component;

@Component
public class DataPropertiesVerifier extends AbstractPropertiesVerifier {

    DataPropertiesVerifier() {
        super(PropertyNames.class);
    }

}
