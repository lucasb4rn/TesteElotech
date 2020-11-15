package br.com.lucas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lucas.entitys.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	List<Pessoa> findByNome(String nome);
	Pessoa findByCpf(String cpf);
	Pessoa findFirstByOrderByIdDesc();
	Page<Pessoa> findAll(Pageable pageable);
	Pessoa save(Optional<Pessoa> pessoa);

}
