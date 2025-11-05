package com.example.endereco.controller;

import com.example.endereco.model.Endereco;
import com.example.endereco.service.EnderecoService;
import com.example.endereco.service.ViaCepService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final ViaCepService viaCepService;

    public EnderecoController(EnderecoService enderecoService, ViaCepService viaCepService) {
        this.enderecoService = enderecoService;
        this.viaCepService = viaCepService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", enderecoService.listar());
        return "enderecos/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("endereco", new Endereco());
        return "enderecos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Endereco e = enderecoService.porId(id).orElseThrow(() -> new IllegalArgumentException("Endereco não encontrado"));
        model.addAttribute("endereco", e);
        return "enderecos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Endereco endereco, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "enderecos/form";
        }
        enderecoService.salvar(endereco);
        return "redirect:/enderecos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        enderecoService.deletar(id);
        return "redirect:/enderecos";
    }

    // Endpoint to lookup CEP via AJAX (returns JSON)
    @GetMapping("/buscar-cep")
    @ResponseBody
    public Map<String, Object> buscarCep(@RequestParam String cep) {
        return viaCepService.buscarCep(cep);
    }
}
