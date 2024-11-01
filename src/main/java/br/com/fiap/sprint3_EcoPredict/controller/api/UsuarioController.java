package br.com.fiap.sprint3_EcoPredict.controller.api;

import br.com.fiap.sprint3_EcoPredict.model.Usuario;
import br.com.fiap.sprint3_EcoPredict.repository.UsuarioRepository;
import br.com.fiap.sprint3_EcoPredict.request.UsuarioRequest;
import br.com.fiap.sprint3_EcoPredict.response.UsuarioResponse;
import br.com.fiap.sprint3_EcoPredict.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Criar novo usuário", description = "Criar novo usuário")
    @ApiResponse(responseCode = "201", description = "usuário criado")
    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioConvertido = usuarioService.requestToUsuario(usuarioRequest);
        Usuario usuarioPersistido = usuarioRepository.save(usuarioConvertido);
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioPersistido);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Retornar todos os usuários", description = "retorna uma lista de todos os usuários")
    @GetMapping
    public  ResponseEntity<List<UsuarioResponse>> read() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        if (listaUsuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UsuarioResponse> listaUsuarioResponse = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuario);
            usuarioResponse.setLink(
                        linkTo(
                                methodOn(UsuarioController.class).read(usuario.getId())).withSelfRel());
            listaUsuarioResponse.add(usuarioResponse);
        }
        return new ResponseEntity<>(listaUsuarioResponse, HttpStatus.OK);
    }

    @Operation(summary = "Retorna usuário prlo id", description = "retorna um único usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> read(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuario.get());
            usuarioResponse.setLink(
                    linkTo(
                            methodOn(UsuarioController.class)
                                    .read()).withRel("Lista de usuarios"));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a user", description = "Updates an existing user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequest usuarioRequest) {
        Optional<Usuario> usuarioPersistido = usuarioRepository.findById(id);
        if (usuarioPersistido.isPresent()) {
            Usuario usuario = usuarioService.requestToUsuario(usuarioRequest);
            usuario.setId(id);
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioAtualizado);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
