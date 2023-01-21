package br.com.attornatus.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.form.EnderecoForm;
import br.com.attornatus.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRep;
	
	@GetMapping
	public Page<EnderecoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 3) Pageable paginacao){
		Page<Endereco> endereco = this.enderecoRep.findAll(paginacao);
		return EnderecoDto.converter(endereco);
	}
	
	@PostMapping
	public ResponseEntity<EnderecoDto> cadastrar(@RequestBody @Valid EnderecoForm form, UriComponentsBuilder uriBuilder){
		Endereco endereco = form.converter();
		this.enderecoRep.save(endereco);
		
		URI uri = uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
	}
}
