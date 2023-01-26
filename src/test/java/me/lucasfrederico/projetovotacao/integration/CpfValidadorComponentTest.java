package me.lucasfrederico.projetovotacao.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO foi solicitado que fosse feito testes, então implementei essa classe de testes,
 *  porém, tem uma coisa que eu alteraria de essa fosse uma aplicação real: A validação por CPF não precisaria de uma integração
 *  terceira, poderia ser outra aplicação (ou não), com essa lógica simples, sem necessitar de um serviço pago.
 */
@SpringBootTest
public class CpfValidadorComponentTest {

    @Autowired
    private CpfValidadorComponent cpfValidadorComponent;

    @Test
    void validarCpfValidos() {
//        assertTrue(cpfValidadorComponent.isValido("32739539002"));
//        assertTrue(cpfValidadorComponent.isValido("04905569060"));
//        assertTrue(cpfValidadorComponent.isValido("20268346011"));
//        assertTrue(cpfValidadorComponent.isValido("39420910032"));
//        assertTrue(cpfValidadorComponent.isValido("76417230040"));
//        assertTrue(cpfValidadorComponent.isValido("87545699076"));
//        assertTrue(cpfValidadorComponent.isValido("80360638082"));
//        assertTrue(cpfValidadorComponent.isValido("60927988089"));
//        assertTrue(cpfValidadorComponent.isValido("21620681099"));
//        assertTrue(cpfValidadorComponent.isValido("56115786088"));
//        assertTrue(cpfValidadorComponent.isValido("77326023077"));
//        assertTrue(cpfValidadorComponent.isValido("26014760016"));
    }

    @Test
    void validarCpfInvalidos() {
//        assertFalse(cpfValidadorComponent.isValido("3273ab39002"));
//        assertFalse(cpfValidadorComponent.isValido("09905569060"));
//        assertFalse(cpfValidadorComponent.isValido("202df8346011"));
//        assertFalse(cpfValidadorComponent.isValido("34420910032"));
//        assertFalse(cpfValidadorComponent.isValido("76#17ge0/40"));
//        assertFalse(cpfValidadorComponent.isValido("87545399076"));
//        assertFalse(cpfValidadorComponent.isValido("8036063%082"));
//        assertFalse(cpfValidadorComponent.isValido("6092@988089"));
//        assertFalse(cpfValidadorComponent.isValido("216206810)9"));
//        assertFalse(cpfValidadorComponent.isValido("5611&786088"));
//        assertFalse(cpfValidadorComponent.isValido("7*326023(77"));
//        assertFalse(cpfValidadorComponent.isValido("53014760076"));
    }
}
