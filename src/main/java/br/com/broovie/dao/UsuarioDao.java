package br.com.broovie.dao;

import br.com.broovie.entidade.Usuario;

public class UsuarioDao extends DaoGenerico<Usuario> {
    public UsuarioDao() {
        setClazz(Usuario.class);
    }

    public Usuario findByNomeUsuario(String nomeUsuario) {
        try {
        return (Usuario) em.createNamedQuery("Usuario.findByNomeUsuario")
                .setParameter("nomeUsuario", nomeUsuario)
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario autenticar(String nomeUsuario, String senha) {
        try {
            return (Usuario) em.createNamedQuery("Usuario.autenticar")
                    .setParameter("nomeUsuario", nomeUsuario)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
