package br.com.fiap.sprint3_EcoPredict.controller.mvc;

import br.com.fiap.sprint3_EcoPredict.model.RegistroAcesso;
import br.com.fiap.sprint3_EcoPredict.repository.RegistroAcessoRepository;
import br.com.fiap.sprint3_EcoPredict.response.RegistroAcessoResponse;
import br.com.fiap.sprint3_EcoPredict.service.RegistroAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistroAcessoViewController {

    @Autowired
    private RegistroAcessoService registroAcessoService;

    @Autowired
    private RegistroAcessoRepository registroAcessoRepository;

    // Lista de todos os registros de acesso
    @GetMapping("/listaAcessos")
    public ModelAndView listaAcessos() {
        List<RegistroAcesso> listaAcessos = registroAcessoRepository.findAll();
        List<RegistroAcessoResponse> listaAcessosResponse = new ArrayList<>();
        for (RegistroAcesso registroAcesso : listaAcessos) {
            listaAcessosResponse.add(registroAcessoService.registroAcessoToResponse(registroAcesso));
        }
        ModelAndView mv = new ModelAndView("registroAcesso/listaAcessos");
        mv.addObject("listaAcessos", listaAcessosResponse);
        return mv;
    }

    // Exibir formulário para novo registro de acesso
    @GetMapping("/novoAcesso")
    public ModelAndView novoRegistroAcesso() {
        ModelAndView mv = new ModelAndView("registroAcesso/formAcesso");
        mv.addObject("registroAcesso", new RegistroAcesso());
        return mv;
    }

    // Salvar novo registro de acesso
    @PostMapping("/salvarAcesso")
    public ModelAndView salvarRegistroAcesso(RegistroAcesso registroAcesso, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registroAcesso/formAcesso");
        }
        registroAcessoRepository.save(registroAcesso);
        return new ModelAndView("redirect:/listaAcessos");
    }
}
