import io.vertx.core.Vertx;

public class TreeApplication {
    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(req ->
                req.response().end("Hello Vertx!")).listen(8989);
    }
}
