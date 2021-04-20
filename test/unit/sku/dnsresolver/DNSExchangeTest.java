package sku.dnsresolver;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DNSExchangeTest {

    @Test
    public void canBeWrittenToAndConstructedFromBytes() {
        DNSExchange protocol = new DNSExchange("message");

        assertThat(DNSExchange.from(protocol.getBytes()), is(equalTo(protocol)));
    }

    @Test
    public void dnsProtocolBuilder() {
        DNSExchange.DNSProtocolBuilder builder = new DNSExchange.DNSProtocolBuilder();
        builder = builder.withMessage("message");

        assertThat(builder.build(), is(equalTo(new DNSExchange("message"))));
    }

    @Test
    public void testToString() {
        DNSExchange protocol = new DNSExchange("message");

        assertThat(protocol, hasToString("DNSExchange[message=message]"));
    }

    @Test
    public void valueObject() {
        DNSExchange protocol1a = new DNSExchange("message");
        DNSExchange protocol1b = new DNSExchange("message");
        DNSExchange protocol2 = new DNSExchange("message2");

        assertThat("same object", protocol1a, is(equalTo(protocol1a)));
        assertThat("same message", protocol1a, is(equalTo(protocol1b)));
        assertThat("different message", protocol2, is(not(equalTo(protocol1a))));
    }

}