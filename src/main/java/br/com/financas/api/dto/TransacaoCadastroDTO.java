package br.com.financas.api.dto;

import br.com.financas.api.model.TipoTransacao;
import java.math.BigDecimal;
import java.time.LocalDate;

// DTO para receber os dados de cadastro e atualização de uma transação
public record TransacaoCadastroDTO(
        String descricao,
        String categoria,
        LocalDate data,
        TipoTransacao tipo,
        BigDecimal valor
) {}