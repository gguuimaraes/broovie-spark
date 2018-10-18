package br.com.broovie.api;

import br.com.broovie.entidade.Usuario;
import br.com.broovie.persistencia.UsuarioDAO;
import org.apache.log4j.Logger;

import java.util.Date;

public class UsuarioApi {
    final static Logger LOGGER = Logger.getLogger(UsuarioApi.class);

    public static void main(String[] args) {
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario u = new Usuario().builder()
                .nome("GABRIEL GUIMARAES")
                .nomeUsuario("gguuimaraes")
                .dataNascimento(new Date())
                .senha("123456")
                .cidade("GOIANIA")
                .estado("GOIAS")
                .pais("BRASIL")
                .email("gguuimaraes@hotmail.com")
                .build();
        uDao.create(u);
        LOGGER.info("Usuario criado");
        LOGGER.info("Listando usuarios");
        uDao.findAll().forEach(usuario -> {
            LOGGER.info(u.toString());
        });
        u.builder().nome("ANA CAROLINA")
                .nomeUsuario("anacarolina20")
                .senha("54321")
                .email("anacarolinabarbosa20-05@hotmail.com")
                .build();
        uDao.update(u);
        LOGGER.info("Usuario alterado");
        LOGGER.info("Listando usuarios");
        uDao.findAll().forEach(usuario -> {
            LOGGER.info(u.toString());
        });
        uDao.remove(u);
        LOGGER.info("Usuario removido");
        LOGGER.info("Listando usuarios");
        uDao.findAll().forEach(usuario -> {
            LOGGER.info(u.toString());
        });
    }
}
