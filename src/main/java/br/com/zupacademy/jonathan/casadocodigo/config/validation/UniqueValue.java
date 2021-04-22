package br.com.zupacademy.jonathan.casadocodigo.config.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
	
	String message() default "{unique.value}"; //mensagem default para quando a validação falhar
	
	Class<?>[] groups() default { }; //aplica validação para grupos especificos

	Class<? extends Payload>[] payload() default { }; //manda informação a mais para dentro da validação
	
	//abaixo é o que a anotação vai precisar para validar
	String fieldName();
	Class<?> domainClass();
}
