package br.com.broovie.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedQueries({
        @NamedQuery(name = "Genero.findByDescricao", query = "SELECT g FROM Genero g WHERE g.descricao = :descricao")
})
@Entity
@Table
public class Genero extends EntidadePadrao {
    @Column
    private String descricao;

}
