package br.com.attornatus.repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public Endereco findByCep(@NotBlank @NotNull @NotEmpty String cep);

}
