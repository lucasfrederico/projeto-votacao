package me.lucasfrederico.projetovotacao.integration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * {@link Component} responsável por acessar o serviço <a href="https://www.invertexto.com/">invertexto</a> e verificar se o CPF está válido.
 * <p>
 * TODO eu realmente não recomendaria que isso continuasse assim. A lógica desse serviço para a validação do CPF,
 *  é teoricamente simples, utilizando o que sabemos sobre código verificador e algumas regras, conseguimos chegar
 *  na resposta se o CPF é válido ou não, não precisamos de um serviço terceiro que poderá ter algum custo pra gente
 *  para consumir isso. É claro que podemos isolar essa lógica em outro micro serviço e acessar ele através desse componente,
 *  mas eu realmente não gosto da ideia de usarmos um serviço pago da internet, quando podemos desenvolver o nosso próprio,
 *  sem problemas com manutenção ou bugs (visto que a lógica é muito simples).
 *
 *  obs.: fiz isso, porque no Word que foi enviado pra mim com o teste, solicitou a integração com um sistema terceiro, então eu fiz. :)
 */
@Component
@RequiredArgsConstructor
public class CpfValidadorComponent {

    private final RestClientFactory restClientFactory;

    @Value("${api.validarcpf.token}")
    private String validarCpfToken;

    @Value("${api.validarcpf.url}")
    private String validarCpfApiURL;

    /**
     * Verificar se um CPF é válido.
     *
     * @param cpf CPF informado.
     */
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
