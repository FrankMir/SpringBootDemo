package com.amat.useraccess.dto.base;

import org.junit.Test;

public class InputConverterTest {

    @Test
    public void testConvertFunction() {
        ConvertObject convertObject = new ConvertObject();
        convertObject.setName("Test");
        convertObject.setPassword("Password");
        ConvertClass convertClass = convertObject.convertTo();
    }
}
