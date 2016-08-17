package controllers;

import akka.stream.javadsl.Flow;
import play.mvc.*;

import views.html.*;



public class HomeController extends Controller {

    public Result index() {
        return ok(index.render());
    }

    public Result websockets() {
        return ok(websockets.render());
    }

    public WebSocket socket() {
        return WebSocket.Text.accept(request -> {

            // log the message to stdout and send response back to client
            return Flow.<String>create().map(msg -> {
                System.out.println(msg);
                return "I received your message: " + msg;
            });
        });
    }

}
