package br.com.fiap.sprint3_EcoPredict.repository;

import br.com.fiap.sprint3_EcoPredict.model.RegistroAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAcessoRepository extends JpaRepository<RegistroAcesso, Long> {

}
