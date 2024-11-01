package br.com.fiap.sprint3_EcoPredict.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registro_acesso")
public class RegistroAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_acesso")
    private LocalDateTime dataHoraAcesso;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(LocalDateTime dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }
}
