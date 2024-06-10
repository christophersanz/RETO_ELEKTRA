package com.customer.controller.dto;

import com.customer.controller.validations.RegularExpression;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDto {

    @NotNull
    @NotBlank(message = "El campo 'nombre' es obligatorio")
    @Pattern(regexp = RegularExpression.TEXT_PATTERN, message = "El campo 'nombre' solo puede contener letras y espacios")
    private String nombre;

    @NotNull
    @NotBlank(message = "El campo 'apellidoPaterno' es obligatorio")
    @Pattern(regexp = RegularExpression.TEXT_PATTERN, message = "El campo 'apellidoPaterno' solo puede contener letras y espacios")
    private String apellidoPaterno;

    @NotNull
    @NotBlank(message = "El campo 'apellidoMaterno' es obligatorio")
    @Pattern(regexp = RegularExpression.TEXT_PATTERN, message = "El campo 'apellidoMaterno' solo puede contener letras y espacios")
    private String apellidoMaterno;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Pattern(regexp = RegularExpression.SEX_PATTERN, message = "El campo 'sexo' solo puede contener 'M' o 'F'")
    private String sexo;

    @Pattern(regexp = RegularExpression.EMAIL_PATTERN, message = "Error en el formato del correo. Ejemplo: email@dominio.com")
    private String correo;

    @Pattern(regexp = RegularExpression.PHONE_PATTERN, message = "El campo 'telefono' debe contener 10 dígitos")
    private String telefono;

}
