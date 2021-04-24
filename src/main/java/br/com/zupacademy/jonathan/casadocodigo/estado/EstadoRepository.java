package br.com.zupacademy.jonathan.casadocodigo.estado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	@Query("SELECT e FROM Estado e WHERE e.nome = :nome AND e.pais.id = :paisId")
	List<Estado> consultarEstado(@Param("nome") String nomeEstado, @Param("paisId") Long id);
}
