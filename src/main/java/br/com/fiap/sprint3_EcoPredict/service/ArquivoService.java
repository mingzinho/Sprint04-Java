package br.com.fiap.sprint3_EcoPredict.service;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import br.com.fiap.sprint3_EcoPredict.request.ArquivoRequest;
import br.com.fiap.sprint3_EcoPredict.response.ArquivoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ArquivoService {

    public Arquivo requestToArquivo(ArquivoRequest arquivoRequest) {
        Arquivo arquivo = new Arquivo();
        arquivo.setNomeArquivo(arquivoRequest.getNomeArquivo());
        arquivo.setTipo(arquivoRequest.getTipo());
        arquivo.setDataUpload(LocalDateTime.now());
        arquivo.setUsuario(arquivoRequest.getUsuario());
        return arquivo;
    }

    public ArquivoResponse arquivoToResponse(Arquivo arquivo) {
        ArquivoResponse arquivoResponse = new ArquivoResponse();
        arquivoResponse.setId(arquivo.getId());
        arquivoResponse.setNomeArquivo(arquivo.getNomeArquivo());
        arquivoResponse.setTipo(arquivo.getTipo());
        arquivoResponse.setDataUpload(arquivo.getDataUpload());
        arquivoResponse.setUsuario(arquivo.getUsuario());
        return arquivoResponse;
    }
}
