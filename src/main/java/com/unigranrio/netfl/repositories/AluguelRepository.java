package com.unigranrio.netfl.repositories;

import com.unigranrio.netfl.entities.Aluguel;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
