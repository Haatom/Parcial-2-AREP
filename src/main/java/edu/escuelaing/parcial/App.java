package edu.escuelaing.parcial;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import static spark.Spark.port;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(getPort());
        get("/ln", (req, res) -> primeraOperacion(req, res));
        get("/atan", (req, res) -> segundaOperacion(req, res));


    }
    private static String primeraOperacion(Request req, Response res) {

        Double num = Double.parseDouble(req.queryParams("value"));
        Calculadora calculadora = new Calculadora();
        res.type("application/json");
        String pageContent=

                "{"+
                        " \"operation\": \"ln\","+"\n"+
                        "\"input\":"+ num +"\n"+
                        " \"output\":" +calculadora.calcularLn(num) +
                        "}";
        return pageContent;
    }

    private static String segundaOperacion(Request req, Response res) {
        Double num = Double.parseDouble(req.queryParams("value"));
        Calculadora calculadora = new Calculadora();
        res.type("application/json");
        String pageContent=
                "{"+
                        " \"operation\": \"atan\","+"\n"+
                        "\"input\":"+ num +"\n"+
                        " \"output\":" +calculadora.calcularATan(num) +
                        "}";
        return pageContent;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
