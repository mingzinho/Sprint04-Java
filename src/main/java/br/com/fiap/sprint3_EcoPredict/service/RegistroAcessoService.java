package br.com.fiap.sprint3_EcoPredict.service;

import br.com.fiap.sprint3_EcoPredict.model.RegistroAcesso;
import br.com.fiap.sprint3_EcoPredict.request.RegistroAcessoRequest;
import br.com.fiap.sprint3_EcoPredict.response.RegistroAcessoResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroAcessoService {

    public RegistroAcesso requestToRegistroAcesso(RegistroAcessoRequest registroAcessoRequest) {
        RegistroAcesso registroAcesso = new RegistroAcesso();
        registroAcesso.setDataHoraAcesso(LocalDateTime.now());
        registroAcesso.setUsuario(registroAcessoRequest.getUsuario());
        return registroAcesso;
    }

    public RegistroAcessoResponse registroAcessoToResponse(RegistroAcesso registroAcesso) {
        RegistroAcessoResponse registroAcessoResponse = new RegistroAcessoResponse();
        registroAcessoResponse.setId(registroAcesso.getId());
        registroAcessoResponse.setDataHoraAcesso(registroAcesso.getDataHoraAcesso());
        registroAcessoResponse.setUsuario(registroAcesso.getUsuario());
        return registroAcessoResponse;
    }
}
