package com.amat.useraccess.dto.base;

import com.amat.useraccess.model.dto.base.InputConverter;
import lombok.Data;

@Data
public class ConvertObject implements InputConverter<ConvertClass> {
    private String name;
    private String password;

}
