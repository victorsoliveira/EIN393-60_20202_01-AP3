package com.unigranrio.netfl.controllers;

import com.unigranrio.business.services.CalculadorDeValores;
import com.unigranrio.netfl.commands.NovoAluguelCommand;
import com.unigranrio.netfl.dtos.AluguelDTO;
import com.unigranrio.netfl.entities.Aluguel;
import com.unigranrio.netfl.entities.Cliente;
import com.unigranrio.netfl.entities.Filme;
import com.unigranrio.netfl.repositories.AluguelRepository;
import com.unigranrio.netfl.repositories.ClienteRepository;
import com.unigranrio.netfl.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AluguelController {


    @Autowired
    private AluguelRepository _aluguelRepository;

    @Autowired
    private ClienteRepository _clienteRepository;

    @Autowired
    private FilmeRepository _filmeRepository;


    private CalculadorDeValores _calculadorDeValores;

    public AluguelController() {
        _calculadorDeValores = new CalculadorDeValores();
    }

    @RequestMapping(value = "/alugueis", method = RequestMethod.GET)
    public List<AluguelDTO> Get() {

        return _aluguelRepository.findAll().stream().map(AluguelDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/alugueis/{id}", method = RequestMethod.GET)
    public ResponseEntity<AluguelDTO> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Aluguel> aluguel = _aluguelRepository.findById(id);

        if(aluguel.isPresent())
            return new ResponseEntity<AluguelDTO>(new AluguelDTO(aluguel.get()), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/alugueis", method =  RequestMethod.POST)
    public ResponseEntity<AluguelDTO> Post(@RequestBody NovoAluguelCommand novo)
    {
        try {
            Cliente cliente = _clienteRepository.getOne(novo.clienteId);
            Filme filme = _filmeRepository.getOne(novo.filmeId);

            Date data = new Date();
            BigDecimal valor = _calculadorDeValores.calcula(data, filme.getValor(), novo.dias);

            Aluguel aluguel = new Aluguel(data, cliente, filme, valor, novo.dias);
            Aluguel saved =  _aluguelRepository.save(aluguel);

            return new ResponseEntity<AluguelDTO>(new AluguelDTO(saved), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/alugueis/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Aluguel> aluguel  = _aluguelRepository.findById(id);

        if(aluguel.isPresent()){
            _aluguelRepository.delete(aluguel.get());
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}