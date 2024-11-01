package br.com.fiap.sprint3_EcoPredict.controller.api;

import br.com.fiap.sprint3_EcoPredict.model.RegistroAcesso;
import br.com.fiap.sprint3_EcoPredict.repository.RegistroAcessoRepository;
import br.com.fiap.sprint3_EcoPredict.request.RegistroAcessoRequest;
import br.com.fiap.sprint3_EcoPredict.response.RegistroAcessoResponse;
import br.com.fiap.sprint3_EcoPredict.service.RegistroAcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/registroAcesso")
public class RegistroAcessoController {

    @Autowired
    private RegistroAcessoService registroAcessoService;
    @Autowired
    private RegistroAcessoRepository registroAcessoRepository;

    @PostMapping
    public ResponseEntity<RegistroAcessoResponse> create(@Valid @RequestBody RegistroAcessoRequest registroAcessoRequest) {
        RegistroAcesso registroAcessoConvertido = registroAcessoService.requestToRegistroAcesso(registroAcessoRequest);
        RegistroAcesso registroAcessoPersistido = registroAcessoRepository.save(registroAcessoConvertido);
        RegistroAcessoResponse registroAcessoResponse = registroAcessoService.registroAcessoToResponse(registroAcessoPersistido);
        return new ResponseEntity<>(registroAcessoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistroAcessoResponse>> read() {
        List<RegistroAcesso> listaAcessos = registroAcessoRepository.findAll();
        if (listaAcessos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RegistroAcessoResponse> listaRegistroAcessoResponse = new ArrayList<>();
        for (RegistroAcesso registroAcesso : listaAcessos) {
            RegistroAcessoResponse registroAcessoResponse = registroAcessoService.registroAcessoToResponse(registroAcesso);
            registroAcessoResponse.setLink(
                    linkTo(
                            methodOn(RegistroAcessoController.class).read(registroAcesso.getId())).withSelfRel());
            listaRegistroAcessoResponse.add(registroAcessoResponse);
        }
        return new ResponseEntity<>(listaRegistroAcessoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAcessoResponse> read(@PathVariable Long id) {
        Optional<RegistroAcesso> registroAcesso = registroAcessoRepository.findById(id);
        if (registroAcesso.isPresent()) {
            RegistroAcessoResponse registroAcessoResponse = registroAcessoService.registroAcessoToResponse(registroAcesso.get());
            registroAcessoResponse.setLink(
                    linkTo(
                            methodOn(RegistroAcessoController.class)
                                    .read()).withRel("Lista de acessos"));
            return new ResponseEntity<>(registroAcessoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroAcessoResponse> update(@PathVariable Long id, @Valid @RequestBody RegistroAcessoRequest registroAcessoRequest) {
        Optional<RegistroAcesso> registroAcessoPersistido = registroAcessoRepository.findById(id);
        if (registroAcessoPersistido.isPresent()) {
            RegistroAcesso registroAcesso = registroAcessoService.requestToRegistroAcesso(registroAcessoRequest);
            registroAcesso.setId(id);
            RegistroAcesso registroAcessoAtualizado = registroAcessoRepository.save(registroAcesso);
            RegistroAcessoResponse registroAcessoResponse = registroAcessoService.registroAcessoToResponse(registroAcessoAtualizado);
            return new ResponseEntity<>(registroAcessoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<RegistroAcesso> registroAcesso = registroAcessoRepository.findById(id);
        if (registroAcesso.isPresent()) {
            registroAcessoRepository.delete(registroAcesso.get());
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
