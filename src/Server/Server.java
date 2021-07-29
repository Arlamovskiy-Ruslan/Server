package Server;

import Handler.Handler;
import jdk.jfr.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

public class Server {

    private int port;

    private String directory;

    private URL url;

    public Server(int port, String directory) {
        this.port = port;
        this.url = url;
        this.directory = directory;
    }

    public void start() {
        try (var server = new ServerSocket(this.port)) {
            while (true) {
                var socket = server.accept();
                var thread = new Handler(socket, this.directory);

                thread.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        var port = Integer.parseInt(args[0]);
        var directory = args[1];

        new Server(port,directory).start();
    }
}
