package br.com.broovie.dao;

import br.com.broovie.entidade.Filme;
import br.com.broovie.entidade.Usuario;

public class FilmeDao extends DaoGenerico<Filme> {
    public FilmeDao() {
        setClazz(Filme.class);
    }

    public Filme findByNome(String nome) {
        try {
        return (Filme) em.createNamedQuery("Filme.findByNome")
                .setParameter("nome", nome)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
