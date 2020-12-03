package com.unigranrio.netfl.controllers;

import com.unigranrio.netfl.commands.SalvaClienteCommand;
import com.unigranrio.netfl.dtos.AluguelDTO;
import com.unigranrio.netfl.dtos.ClienteDTO;
import com.unigranrio.netfl.entities.Cliente;
import com.unigranrio.netfl.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ClienteController  {

    @Autowired
    private ClienteRepository _clienteRepository;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<ClienteDTO> Get() {
        return _clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);

        if(cliente.isPresent())
            return new ResponseEntity<ClienteDTO>(new ClienteDTO(cliente.get()), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clientes", method =  RequestMethod.POST)
    public Cliente Post(@RequestBody SalvaClienteCommand novo)
    {
        Cliente cliente = new Cliente(novo.nome, novo.cpf, novo.dataNascimento);
        return _clienteRepository.save(cliente);
    }

    @RequestMapping(value = "/clientes/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cliente> Put(@PathVariable(value = "id") long id, @RequestBody SalvaClienteCommand mudancas)
    {
        Optional<Cliente> oldCliente = _clienteRepository.findById(id);

        if(oldCliente.isPresent()){

            Cliente cliente = oldCliente.get();
            cliente.setNome(mudancas.nome);
            cliente.setDataNascimento(mudancas.dataNascimento);
            cliente.setCpf(mudancas.cpf);

            _clienteRepository.save(cliente);

            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Cliente> cliente = _clienteRepository.findById(id);

        if(cliente.isPresent()){

            _clienteRepository.delete(cliente.get());
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
