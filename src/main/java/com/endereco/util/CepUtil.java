package com.endereco.util;

public class CepUtil {

    private static final String REGEX_CEP = "^\\d{8}$";
    private static final String CEP_ALL_ZERO = "00000000";
    public static final String CEP_INVALIDO = "CEP inválido";
    public static final String CEP_NOT_FOUND = "CEP não encontrado";

    public static boolean isValidCep(String cep){
        return cep != null && cep.matches("[0-9]{8}");
    }

    public static String changeCepByPosition(String cep, int position){
        StringBuilder newCep = new StringBuilder(cep);
        newCep.setCharAt(position, '0');
        return newCep.toString();
    }

    public static boolean isAllZero(String cep){
        return cep.equals(CEP_ALL_ZERO);
    }

}
