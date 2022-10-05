package com.example.aea.service;

public class apis {
    public static final String URL_001="http://172.17.7.28:8080/escuelas/";

    public static com.example.aea.service.EscuelaService getEscuelaService(){
        return  Cliente.getClient(URL_001).create(com.example.aea.service.EscuelaService.class);
    }
}
