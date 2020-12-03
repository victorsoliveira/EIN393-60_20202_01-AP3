package com.unigranrio.netfl.controllers;

import com.unigranrio.netfl.commands.SalvaFilmeCommand;
import com.unigranrio.netfl.dtos.FilmeDTO;
import com.unigranrio.netfl.entities.Filme;
import com.unigranrio.netfl.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FilmeController {

    @Autowired
    private FilmeRepository _filmeRepository;

    @RequestMapping(value = "/filmes", method = RequestMethod.GET)
    public List<FilmeDTO> Get() {
        return _filmeRepository.findAll().stream().map(FilmeDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/filmes/{id}", method = RequestMethod.GET)
    public ResponseEntity<FilmeDTO> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Filme> filme = _filmeRepository.findById(id);

        if(filme.isPresent())
            return new ResponseEntity<FilmeDTO>(new FilmeDTO(filme.get()), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/filmes", method =  RequestMethod.POST)
    public Filme Post(@RequestBody SalvaFilmeCommand novo)
    {
        Filme filme = new Filme(novo.titulo, novo.descricao, novo.valor);
        return _filmeRepository.save(filme);
    }

    @RequestMapping(value = "/filmes/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Filme> Put(@PathVariable(value = "id") long id, @RequestBody SalvaFilmeCommand mudancas)
    {
        Optional<Filme> oldFilme = _filmeRepository.findById(id);

        if(oldFilme.isPresent()){

            Filme filme = oldFilme.get();
            filme.setTitulo(mudancas.titulo);
            filme.setDescricao(mudancas.descricao);
            filme.setValor(mudancas.valor);

            _filmeRepository.save(filme);

            return new ResponseEntity<Filme>(filme, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/filmes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Filme> filme  = _filmeRepository.findById(id);

        if(filme.isPresent()){

            _filmeRepository.delete(filme.get());
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
