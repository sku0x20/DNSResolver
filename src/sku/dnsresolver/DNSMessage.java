package sku.dnsresolver;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DNSMessage {

    final DNSSocketAddress from;
    final DNSPacket exchange;

    public DNSMessage(DNSSocketAddress from, DNSPacket exchange) {
        this.from = from;
        this.exchange = exchange;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        DNSMessage that = (DNSMessage) obj;

        return new EqualsBuilder()
                .append(from, that.from)
                .append(exchange, that.exchange)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(from)
                .append(exchange)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .toString();
    }
}
