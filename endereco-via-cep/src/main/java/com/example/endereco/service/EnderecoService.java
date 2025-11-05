package com.example.endereco.service;

import com.example.endereco.model.Endereco;
import com.example.endereco.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository repo;

    public EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    public List<Endereco> listar() {
        return repo.findAll();
    }

    public Optional<Endereco> porId(Long id) {
        return repo.findById(id);
    }

    public Endereco salvar(Endereco e) {
        return repo.save(e);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
