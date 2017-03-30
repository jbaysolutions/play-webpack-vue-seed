package controllers;

import akka.stream.javadsl.Flow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.*;

import views.html.*;

import java.util.UUID;
import java.util.function.Consumer;


public class HomeController extends Controller {
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);


    public Result index() {
        return ok(index.render());
    }

/*
    public Result websockets() {
        return ok(websockets.render());
    }
*/

    /*public WebSocket socket() {
        return WebSocket.Text.accept(request -> {

            // log the message to stdout and send response back to client
            return Flow.<String>create().map(msg -> {
                System.out.println(msg);
                return "I received your message: " + msg;
            });
        });
    }*/


    /**
     * Making a WebSocket for chat application available.
     *
     * @return WebSocket
     */
/*    public LegacyWebSocket<String> socket() {

        return new LegacyWebSocket<String>() {
            @Override
            public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {

                in.onMessage(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        out.write("PONG! " + s);
                    }
                });

                in.onClose(new Runnable() {
                    @Override
                    public void run() {
                        logger.info("WebSocket Disconnected");
                    }
                });

                // Now we do some of or logic
                // 1- We generate a UUID that we'll associate with this WebSocket
                String uuid = UUID.randomUUID().toString();

                // 2- Now we store the UUID that we just created, in some fashion
                // to associate it with the IN and OUT streams of the Web Socket
                *//*WebSessionsHandler.getInstance().createNewQRSession(
                        uuid,
                        in,out
                );*//*

                // 3- We send the UUID to the browser, through the WebSocket
                // we just opened with the browser
                out.write("wsready###" + uuid);

            }
        };

    }*/

}
