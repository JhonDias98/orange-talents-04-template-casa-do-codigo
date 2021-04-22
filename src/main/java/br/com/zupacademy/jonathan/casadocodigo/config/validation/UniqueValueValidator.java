package br.com.zupacademy.jonathan.casadocodigo.config.validation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//ConstraintValidator<Annotation que vamos usar, Tipo do parâmetro que espera receber>
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;
	
	/**
     * O método(initialize) é chamado na instanciacao da classe que vai ser validada.
     * Seu argumento recebe a anotacao(@interface) customizada com os atributos definidos nela.
     * @param objeto do tipo da anotacao que contem os atributos especificados na annotation
     */
	@Override
	public void initialize(UniqueValue params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	/**
    *
    * @param Valor do atributo a ser buscado
    * @param interface
    * @return True caso a valicacao der ok, caso contrario False
    */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//Aqui é construido a query para consultar os dados
		Query query = manager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		return list.isEmpty();
	}

	
}
