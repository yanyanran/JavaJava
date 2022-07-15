package netty;

public class NettyChats {
    public static void main(String[] args) throws InterruptedException {
        NettyChatServer server = new NettyChatServer();
        NettyChatClient client1 = new NettyChatClient();
        NettyChatClient client2 = new NettyChatClient();
        server.run();
        client1.run();
        client2.run();
    }
}