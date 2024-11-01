package br.com.fiap.sprint3_EcoPredict.controller.api;

import br.com.fiap.sprint3_EcoPredict.model.Arquivo;
import br.com.fiap.sprint3_EcoPredict.repository.ArquivoRepository;
import br.com.fiap.sprint3_EcoPredict.request.ArquivoRequest;
import br.com.fiap.sprint3_EcoPredict.response.ArquivoResponse;
import br.com.fiap.sprint3_EcoPredict.service.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = "/arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;
    @Autowired
    private ArquivoRepository arquivoRepository;


    @PostMapping
    public ResponseEntity<ArquivoResponse> create(@Valid @RequestBody ArquivoRequest arquivoRequest) {
        Arquivo arquivoConvertido = arquivoService.requestToArquivo(arquivoRequest);
        Arquivo arquivoPersistido = arquivoRepository.save(arquivoConvertido);
        ArquivoResponse arquivoResponse = arquivoService.arquivoToResponse(arquivoPersistido);
        return new ResponseEntity<>(arquivoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArquivoResponse>> read() {
        List<Arquivo> listaArquivos = arquivoRepository.findAll();
        if (listaArquivos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ArquivoResponse> listaArquivoResponse = new ArrayList<>();
        for (Arquivo arquivo : listaArquivos) {
            ArquivoResponse arquivoResponse = arquivoService.arquivoToResponse(arquivo);
            arquivoResponse.setLink(
                        linkTo(
                                methodOn(ArquivoController.class).read(arquivo.getId())).withSelfRel());
            listaArquivoResponse.add(arquivoResponse);
        }
        return new ResponseEntity<>(listaArquivoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArquivoResponse> read(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            ArquivoResponse arquivoResponse = arquivoService.arquivoToResponse(arquivo.get());
            arquivoResponse.setLink(
                    linkTo(
                            methodOn(ArquivoController.class)
                                    .read()).withRel("Lista de arquivos"));
            return new ResponseEntity<>(arquivoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArquivoResponse> update(@PathVariable Long id, @Valid @RequestBody ArquivoRequest arquivoRequest) {
        Optional<Arquivo> arquivoPersistido = arquivoRepository.findById(id);
        if (arquivoPersistido.isPresent()) {
            Arquivo arquivo = arquivoService.requestToArquivo(arquivoRequest);
            arquivo.setId(id);
            Arquivo arquivoAtualizado = arquivoRepository.save(arquivo);
            ArquivoResponse arquivoResponse = arquivoService.arquivoToResponse(arquivoAtualizado);
            return new ResponseEntity<>(arquivoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        if (arquivo.isPresent()) {
            arquivoRepository.delete(arquivo.get());
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
