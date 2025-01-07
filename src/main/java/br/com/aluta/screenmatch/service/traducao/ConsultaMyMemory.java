package br.com.aluta.screenmatch.service.traducao;

import br.com.aluta.screenmatch.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();

        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        DadosTraducao traducao;

        try {
            String json = consumo.obterDados(url);
            traducao = mapper.readValue(json, DadosTraducao.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return traducao.dadosResposta().textoTraduzido();

    }
}