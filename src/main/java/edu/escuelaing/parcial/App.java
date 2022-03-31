package edu.escuelaing.parcial;

import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class App {
    private static Calculadora calcular = new Calculadora();
    public static void main(String[] args) {
        port(getPort());
        get("/atan", (req, res) -> calculateData(req, res,"atan"));
        get("/ln", (req, res) -> calculateData(req, res,"ln"));
    }

    private static JSONObject calculateData(Request req, Response res,String operation) {
        double number = Double.parseDouble(req.queryParams("value"));
        JSONObject obj = new JSONObject();
        obj.put("input",number);
        obj.put("operation",operation);
        obj.put("output",(operation.equals("atan"))? calcular.calcularATan(number) : calcular.calcularLn(number));
        return obj;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}