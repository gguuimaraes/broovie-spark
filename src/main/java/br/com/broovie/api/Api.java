package br.com.broovie.api;

import br.com.broovie.rota.RotaUsuario;
import com.google.gson.Gson;

import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.path;

public class Api {
    final static String TOKEN = "token";

    public static void main(String[] args) {
        path("/api", () -> {
            /*before("/*", (request, response) -> {
                if (!TOKEN.equals(request.headers("Authorization"))) {
                    halt(401, "Sessao não autorizada. Favor informar TOKEN de acesso.");
                }
            });*/
            path("/usuario", () -> {
                RotaUsuario.getInstance();
            });
        });
    }
}
