package br.com.financas.api.controller;

import br.com.financas.api.dto.TransacaoCadastroDTO;
import br.com.financas.api.dto.TransacaoDetalhesDTO;
import br.com.financas.api.model.Transacao;
import br.com.financas.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody TransacaoCadastroDTO dto, UriComponentsBuilder uriBuilder) {
        var transacao = new Transacao(dto);
        repository.save(transacao);

        var uri = uriBuilder.path("/transacoes/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new TransacaoDetalhesDTO(transacao));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDetalhesDTO>> listar() {
        var lista = repository.findAll().stream().map(TransacaoDetalhesDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody TransacaoCadastroDTO dto) {
        var transacao = repository.getReferenceById(id);
        transacao.atualizarInformacoes(dto);
        return ResponseEntity.ok(new TransacaoDetalhesDTO(transacao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}