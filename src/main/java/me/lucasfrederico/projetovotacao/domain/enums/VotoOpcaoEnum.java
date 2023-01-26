package me.lucasfrederico.projetovotacao.domain.enums;

public enum VotoOpcaoEnum {

    SIM,
    NAO;

    public static VotoOpcaoEnum ofTexto(String texto) {
        return texto.equals("Sim") ? SIM : NAO;
    }

}
