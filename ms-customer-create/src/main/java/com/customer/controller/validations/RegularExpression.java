package com.customer.controller.validations;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class RegularExpression {

    public static final String TEXT_PATTERN = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._%+-]+@(?:hotmail\\.com|gmail\\.com|outlook\\.com)";

    public static final String SEX_PATTERN = "^[MF]$";

    public static final String PHONE_PATTERN = "^[0-9]{10}$";

}