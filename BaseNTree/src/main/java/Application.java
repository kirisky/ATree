import Base.EntityTree;

public class Application {

    public static void main(String[] args) {
        var server = new Server(new EntityTree());
        server.start();
    }

}
