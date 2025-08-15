package br.com.financas.api.dto;

import br.com.financas.api.model.TipoTransacao;
import br.com.financas.api.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDate;

// DTO para devolver os dados detalhados de uma transação
public record TransacaoDetalhesDTO(
        Long id,
        String descricao,
        String categoria,
        LocalDate data,
        TipoTransacao tipo,
        BigDecimal valor
) {
    // Construtor que facilita a conversão da Entidade para o DTO
    public TransacaoDetalhesDTO(Transacao transacao) {
        this(transacao.getId(), transacao.getDescricao(), transacao.getCategoria(), transacao.getData(), transacao.getTipo(), transacao.getValor());
    }
}