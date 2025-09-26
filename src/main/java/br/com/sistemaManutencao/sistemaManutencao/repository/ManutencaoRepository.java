
package br.com.sistemaManutencao.sistemaManutencao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemaManutencao.sistemaManutencao.model.Manutencao;


public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{}