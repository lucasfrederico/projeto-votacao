package me.lucasfrederico.projetovotacao.integration;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Component
public class RestClientFactory {

    private final RestTemplateBuilder builder;

    public RestClientFactory(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @SneakyThrows
    public <T> T template(boolean disableSSl, Function<RestTemplate, T> execute) {
        if (!disableSSl) {
            return execute.apply(builder.build());
        } else {
            TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
            var sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            var sslsf = new SSLConnectionSocketFactory(sslContext,
                    NoopHostnameVerifier.INSTANCE);

            var socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslsf)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();

            try (
                    var connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
                    var httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
            ) {
                if (httpClient == null) {
                    throw new IllegalArgumentException("Não foi possível instanciar o Cliente HTTP");
                }
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
                requestFactory.setHttpClient(httpClient);
                return execute.apply(new RestTemplate(requestFactory));
            }
        }
    }
}
