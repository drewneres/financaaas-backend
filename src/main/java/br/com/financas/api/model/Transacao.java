package br.com.financas.api.model;

import br.com.financas.api.dto.TransacaoCadastroDTO; // Importe o novo DTO
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String categoria;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private BigDecimal valor;

    // NOVO CONSTRUTOR usando o DTO
    public Transacao(TransacaoCadastroDTO dto) {
        this.descricao = dto.descricao();
        this.categoria = dto.categoria();
        this.data = dto.data();
        this.tipo = dto.tipo();
        this.valor = dto.valor();
    }

    // NOVO MÉTODO usando o DTO
    public void atualizarInformacoes(TransacaoCadastroDTO dto) {
        if (dto.descricao() != null) this.descricao = dto.descricao();
        if (dto.categoria() != null) this.categoria = dto.categoria();
        if (dto.data() != null) this.data = dto.data();
        if (dto.tipo() != null) this.tipo = dto.tipo();
        if (dto.valor() != null) this.valor = dto.valor();
    }
}