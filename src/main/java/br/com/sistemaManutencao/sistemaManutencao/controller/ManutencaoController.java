package br.com.sistemaManutencao.sistemaManutencao.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sistemaManutencao.sistemaManutencao.model.Manutencao;
import br.com.sistemaManutencao.sistemaManutencao.repository.ManutencaoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sistema_manutencao")
public class ManutencaoController {

    @Autowired
    ManutencaoRepository manutencaoRepository;


    @GetMapping
    public ModelAndView list() {
        return new ModelAndView(
                "list", Map.of("manutencoes", manutencaoRepository.findAll(Sort.by("item"))));
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("form", Map.of("manutencao", new Manutencao()));
    }

    @PostMapping("/create")
    public String create(@Valid Manutencao manutencao, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors())
            return "form";

        manutencaoRepository.save(manutencao);
        return "redirect:/manutencao";
    }

    // 🔹 Editar
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        Optional<Manutencao> manutencao = manutencaoRepository.findById(id);

        if (manutencao.isPresent()) {
            return new ModelAndView("form", Map.of("manutencao", manutencao.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Manutencao manutencao, BindingResult result) {
        if (result.hasErrors())
            return "form";

        manutencaoRepository.save(manutencao);
        return "redirect:/manutencao";
    }

    @PostMapping("/finish/{id}")
public String finish(@PathVariable Long id) {
    var optionalmanutencao = manutencaoRepository.findById(id);
    if (optionalmanutencao.isPresent()) {
        var manutencao = optionalmanutencao.get();
        manutencao.setFinisheadAt(LocalDate.now());
        manutencao.setStatus("Concluído"); // atualiza status automaticamente
        manutencaoRepository.save(manutencao);
        return "redirect:/manutencao";
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}

}
