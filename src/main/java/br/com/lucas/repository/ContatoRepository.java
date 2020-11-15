package br.com.lucas.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucas.entitys.Contato;


@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

	Contato findByNome(String nome);
	Optional<Contato> findById(Integer id);
	Contato findFirstByOrderByIdDesc();
	void deleteById(Integer id);
	Page<Contato> findAll(Pageable pageable);
	Contato save(Contato contato);

	
}
