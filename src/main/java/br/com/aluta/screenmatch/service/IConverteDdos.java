package br.com.aluta.screenmatch.service;

public interface IConverteDdos {
    <T> T obterDados(String json, Class <T> classe);
}
