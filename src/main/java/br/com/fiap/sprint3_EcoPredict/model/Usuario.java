package br.com.fiap.sprint3_EcoPredict.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_empresa")
    @NotBlank(message = "O nome é obrigatório")
    private String nomeEmpresa;

    @Column(name = "email", unique = true)
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @Column(name = "senha")
    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @Column(name = "cnpj")
    @Size(min = 11, max = 14, message = "O CNPJ deve ter entre 11 e 14 caracteres")
    private String cnpj;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Arquivo> arquivos = new ArrayList<>();

    // Getters e Setters existentes

    // Implementações da interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Retorne as permissões do usuário se houver
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email; // Usando o email como username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modifique conforme necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modifique conforme necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modifique conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Modifique conforme necessário
    }

}
