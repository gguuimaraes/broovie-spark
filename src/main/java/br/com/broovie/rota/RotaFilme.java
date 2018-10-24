package br.com.broovie.rota;

import br.com.broovie.dao.FilmeDao;
import br.com.broovie.dao.FilmeDao;
import br.com.broovie.entidade.Filme;
import br.com.broovie.entidade.Filme;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

public class RotaFilme {
    final static Logger LOGGER = Logger.getLogger(RotaFilme.class);

    final static String CONTENT_TYPE = "application/json";
    final static FilmeDao FILME_DAO = new FilmeDao();
    final static Gson GSON = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy")
            .create();

    static RotaFilme instance = null;

    public static RotaFilme getInstance() {
        if (instance == null)
            instance = new RotaFilme();
        return instance;
    }

    private RotaFilme() {
        get("", (request, response) -> {
            response.type(CONTENT_TYPE);
            response.status(HttpStatus.OK_200);
            return GSON.toJson(FILME_DAO.findAll());
        });

        get("/:id", (request, response) -> {
            response.type(CONTENT_TYPE);
            try {
                response.status(HttpStatus.OK_200);
                return GSON.toJson(FILME_DAO.findOne(Long.valueOf(request.params(":id"))));
            } catch (Exception e) {
                response.status(HttpStatus.NO_CONTENT_204);
                return "";
            }
        });
        post("", (request, response) -> {
            response.type(CONTENT_TYPE);
            Filme f = GSON.fromJson(request.body(), Filme.class);
            try {
                FILME_DAO.create(f);
                f = FILME_DAO.findByNome(f.getNome());
                response.status(HttpStatus.CREATED_201);
                return GSON.toJson(f);
            } catch (Exception e) {
                response.status(HttpStatus.NO_CONTENT_204);
                return "";
            }
        });
        put("", (request, response) -> {
            response.type(CONTENT_TYPE);
            Filme fNovo = GSON.fromJson(request.body(), Filme.class);
            Filme fAntigo = FILME_DAO.findOne(fNovo.getId());
            if (fAntigo != null) {
                fAntigo.setNome(fNovo.getNome());
                fAntigo.setClassificacaoIndicativa(fNovo.getClassificacaoIndicativa());
                fAntigo.setSinopse(fNovo.getSinopse());
                fAntigo.setGenero(fNovo.getGenero());
                FILME_DAO.update(fAntigo);
                response.status(HttpStatus.CREATED_201);
                return GSON.toJson(fAntigo);
            }
            response.status(HttpStatus.NO_CONTENT_204);
            return "";
        });
        delete("/:id", (request, response) -> {
            response.type(CONTENT_TYPE);
            try {
                Filme u = FILME_DAO.findOne(Long.valueOf(request.params(":id")));
                if (u != null) {
                    FILME_DAO.delete(u);
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
