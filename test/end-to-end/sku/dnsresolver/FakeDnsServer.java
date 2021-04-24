package sku.dnsresolver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FakeDnsServer {
    public static final String FAKE_DNS_IP_ADDRESS = "127.0.0.1";
    public static final String FAKE_DNS_PORT = "5000";

    private NetworkThread serverThread;
    private final SingleMessageListener messageListener = new SingleMessageListener();

    public void startServer() throws Exception {
        DNSSocketAddress socketAddress = new DNSSocketAddress(FAKE_DNS_IP_ADDRESS, FAKE_DNS_PORT);
        serverThread = new NetworkThread(messageListener, socketAddress);
        serverThread.start();
    }

    public void stopServer() {
        serverThread.stopThread();
    }

    // TODO:
    public void hasReceivedRequestFor(String domainName) throws InterruptedException {
//        DNSPacket exchange = new DNSPacket.DNSExchangeBuilder()
//                .withMessage(domainName)
//                .build();
//        messageListener.receivesAMessageWith(exchange);
    }

    public void respondWith(String s) {
        serverThread.sendRequest(s, messageListener.lastAddress);
    }

    public String ipAddress() {
        return FAKE_DNS_IP_ADDRESS;
    }


    public String port() {
        return FAKE_DNS_PORT;
    }

    public static class SingleMessageListener implements DNSMessageListener {
        private final ArrayBlockingQueue<DNSPacket> messages = new ArrayBlockingQueue<>(1);

        private DNSSocketAddress lastAddress;

        @Override
        public void message(DNSMessage message) {
            messages.add(message.exchange);
            lastAddress = message.from;
        }

        public void receivesAMessageWith(DNSPacket dnsPacket) throws InterruptedException {
            assertThat("DNS Protocol", messages.poll(5, TimeUnit.SECONDS), is(dnsPacket));
        }
    }
}
