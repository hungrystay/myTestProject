package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverChannel =
                ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8888));

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            System.out.println("into while loop");
            int selected = selector.select();
            if (selected == 0) {
                System.out.println("waiting, waiting !!!");
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIter = selectedKeys.iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            RequestHandler requestHandler = new RequestHandler();

            while(keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isAcceptable()) {
                    System.out.println("into acceptable loop");
                    ServerSocketChannel channel =
                            (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();


                    clientChannel.configureBlocking(false);
                    clientChannel.register(
                            selector, SelectionKey.OP_READ);

                }

                if (key.isReadable()) {
                    SocketChannel channel =
                            (SocketChannel) key.channel();
                    System.out.println("into readable loop");
                    channel.read(buffer);
                    String request = new String(buffer.array()).trim();
                    buffer.clear();
                    String response = requestHandler.handle(request);
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }

                keyIter.remove();
            }
        }
    }

}
