package com.unigranrio.netfl.dtos;

import com.unigranrio.netfl.entities.Cliente;

import java.util.Date;

public class ClienteDTO {

    public String nome;
    public Date dataNascimento;
    public String cpf;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataNascimento = cliente.getDataNascimento();
    }
}
