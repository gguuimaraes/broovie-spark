package br.com.broovie.entidade;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedQueries({
        @NamedQuery(name = "Filme.findByNome", query = "SELECT f FROM Filme f WHERE f.nome = :nome")
})
@Entity
@Table
public class Filme extends EntidadePadrao {
    @Column(unique = true)
    private String nome;
    @OneToOne
    private Genero genero;
    @Column
    private String sinopse;
    @Column
    private int classificacaoIndicativa;

}
