package br.com.broovie.persistencia;

import br.com.broovie.entidade.Usuario;

public class UsuarioDAO extends PersistenciaGenerica<Usuario> {
    public UsuarioDAO() {
        setClazz(Usuario.class);
    }
}
