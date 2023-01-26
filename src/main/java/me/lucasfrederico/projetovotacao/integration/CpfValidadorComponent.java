package me.lucasfrederico.projetovotacao.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.lucasfrederico.projetovotacao.integration.RestClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CpfValidadorComponent {

    private final RestClientFactory restClientFactory;

    @Value("${api.validarcpf.token}")
    private String validarCpfToken;

    @Value("${api.validarcpf.url}")
    private String validarCpfApiURL;

    public boolean isValido(String cpf) {
        return restClientFactory.template(false, restTemplate -> restTemplate
                .getForEntity(String.format(validarCpfApiURL, validarCpfToken, cpf), CpfStatus.class)
                .getBody()
                .isValid());
    }

    @Data
    private static class CpfStatus {
        private boolean valid;
    }

}
