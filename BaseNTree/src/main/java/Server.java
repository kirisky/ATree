import Base.EntityTree;
import DTO.EntityRequest;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class Server {
    private final String INTERNAL_ROUTER_ERROR  = "Internal router error!";
    private final String NOT_FOUND_API          = "Not found the API! Make sure the URL you give is correct.";

    private final EntityTree tree;


    public Server(EntityTree tree) {
        this.tree = tree;
    }

    public void start() {
        var vertx = Vertx.vertx();
        var router = createRouter(vertx);
        vertx
                .createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    private Router createRouter(Vertx vertx) {
        var basePath = "/api";
        var jsonException = "Json format is invalid! Please pass correct json string!";
        var router = Router.router(vertx);

        router.get(basePath + "/read-entity/:id").handler(ctx -> {
            var id = ctx.request().getParam("id");
            ctx.response().end(readEntity(id));
        }).failureHandler(ctx -> ctx.response().end(INTERNAL_ROUTER_ERROR));

        router.get(basePath + "/list-entries")
                .handler(ctx -> ctx.response().end(listEntries()))
                .failureHandler(ctx -> ctx.response().end(INTERNAL_ROUTER_ERROR));

        router.post(basePath + "/send-entity").handler(ctx -> ctx.request().bodyHandler(buff -> {
            try{
                ctx.response().end(sendEntity(buff.toJsonObject().mapTo(EntityRequest.class)));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ctx.response().end(jsonException);
            }
        })).failureHandler(ctx -> ctx.response().end(INTERNAL_ROUTER_ERROR));

        router.route().last().handler(ctx -> ctx.response().end(NOT_FOUND_API));

        return router;
    }

    private String sendEntity(EntityRequest request) {
        var addEntitySuccess = "Adding entity success!";
        var addEntityFailed = "Adding entity failed! Please make sure the parentId is correct.";

        return tree.addEntity(request.parentId, request.data) ? addEntitySuccess : addEntityFailed;
    }

    private String listEntries() {
        var emptyEntries = "No any entities exist!";
        var listEntries = tree.listEntries();

        return listEntries == null ? emptyEntries : listEntries;
    }

    private String readEntity(String id) {
        var readEntitySuccess = "Read the target entity!";
        var readEntityFailed = "Cannot read the target entity! Please give it a valid entity Id!";

        var entity = tree.readEntity(id);

        return entity == null
                ? readEntityFailed
                : String.format("%s \n { %s }", readEntitySuccess, entity.toString());
    }
}
