package br.com.gabrieleholanda.fichas_cantina.entity;

import br.com.gabrieleholanda.fichas_cantina.enums.StatusAlunoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_CARTEIRINHA")
public class FichasCantina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUNO")
    private Long id;

    @Column(name = "NOME_ALUNO")
    private String nome;

    @Column(name = "MATRICULA")
    private String matricula;
    @Column(name = "SALDO")
    private Double saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_ALUNO")
    private StatusAlunoEnum status;
}
