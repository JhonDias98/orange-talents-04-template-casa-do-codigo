package br.com.zupacademy.jonathan.casadocodigo.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Com as annotatios abaixo não seria necessario criar uma classe para validar o CPF/CNPJ
//@CPF
//@CNPJ
//@ConstraintComposition(CompositionType.OR)
//@ReportAsSingleViolation

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPForCNPJValidator.class)
@Documented
public @interface CPForCNPJ {

	String message() default "CPF ou CNPJ inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
