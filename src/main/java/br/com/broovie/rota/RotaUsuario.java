package br.com.broovie.rota;

import br.com.broovie.entidade.Usuario;
import br.com.broovie.dao.UsuarioDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import spark.RouteGroup;

import static spark.Spark.*;

public class RotaUsuario {
    final static Logger LOGGER = Logger.getLogger(RotaUsuario.class);

    final static String CONTENT_TYPE = "application/json";
    final static UsuarioDao USUARIO_DAO = new UsuarioDao();
    final static Gson GSON = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create();

    static RotaUsuario instance = null;

    public static RotaUsuario getInstance() {
        if (instance == null)
            instance = new RotaUsuario();
        return instance;
    }

    private RotaUsuario() {
        get("", (request, response) -> {
            response.type(CONTENT_TYPE);
            response.status(HttpStatus.OK_200);
            return GSON.toJson(USUARIO_DAO.findAll());
        });

        get("/:id", (request, response) -> {
            response.type(CONTENT_TYPE);
            try {
                response.status(HttpStatus.OK_200);
                return GSON.toJson(USUARIO_DAO.findOne(Long.valueOf(request.params(":id"))));
            } catch (Exception e) {
                response.status(HttpStatus.NO_CONTENT_204);
                return "";
            }
        });
        post("", (request, response) -> {
            response.type(CONTENT_TYPE);
            Usuario u = GSON.fromJson(request.body(), Usuario.class);
            try {
                USUARIO_DAO.create(u);
                u = USUARIO_DAO.findByNomeUsuario(u.getNomeUsuario());
                response.status(HttpStatus.CREATED_201);
                return GSON.toJson(u);
            } catch (Exception e) {
                response.status(HttpStatus.NO_CONTENT_204);
                return "";
            }
        });
        put("", (request, response) -> {
            response.type(CONTENT_TYPE);
            Usuario uNovo = GSON.fromJson(request.body(), Usuario.class);
            Usuario uAntigo = USUARIO_DAO.findOne(uNovo.getId());
            if (uAntigo != null) {
                uAntigo.setNome(uNovo.getNome());
                uAntigo.setNomeUsuario(uNovo.getNomeUsuario());
                uAntigo.setEmail(uNovo.getEmail());
                uAntigo.setDataNascimento(uNovo.getDataNascimento());
                //uAntigo.setCidade(uNovo.getCidade());
                //uAntigo.setEstado(uNovo.getEstado());
                uAntigo.setPais(uNovo.getPais());
                uAntigo.setSenha(uNovo.getSenha());
                USUARIO_DAO.update(uAntigo);
                response.status(HttpStatus.CREATED_201);
                return GSON.toJson(uAntigo);
            }
            response.status(HttpStatus.NO_CONTENT_204);
            return "";
        });
        delete("/:id", (request, response) -> {
            response.type(CONTENT_TYPE);
            try {
                Usuario u = USUARIO_DAO.findOne(Long.valueOf(request.params(":id")));
                if (u != null) {
                    USUARIO_DAO.delete(u);
                    response.status(HttpStatus.CREATED_201);
                    return GSON.toJson(u);
                }
            } catch (Exception e) {
            }
            response.status(HttpStatus.NO_CONTENT_204);
            return "";
        });

    }
}
