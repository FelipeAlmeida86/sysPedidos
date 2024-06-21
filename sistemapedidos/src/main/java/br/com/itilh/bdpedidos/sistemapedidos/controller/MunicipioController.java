package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MunicipioController {

    private final MunicipioRepository repositorio;

    public MunicipioController(MunicipioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/municipios")
    public List<Municipio> getmunicipios() {
        return (List<Municipio>) repositorio.findAll();
    }

    @GetMapping("/municipios/estado/{id}")
    public List<Municipio> getMunicipiosPorEstadoId(@PathVariable BigInteger id) {
        return (List<Municipio>) repositorio.findByEstadoId(id);
    }

    @GetMapping("/municipios/estado/{nome}")
    public List<Municipio> getMunicipiosPorEstadoNome(@PathVariable String nome) {
        return (List<Municipio>) repositorio.findByEstadoNomeIgnoreCase(nome);
    }

    @GetMapping("/municipio/{id}")
    public Municipio getMunicipoioPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("Id não encontrado"));
    }

    @PostMapping("/municipio")
    public Municipio postMunicipio(@RequestBody Municipio entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível criar o município." + ex.getMessage());
        }
    }

    @PutMapping("/municipio/{id}")
    public Municipio putMunicipio(@PathVariable BigInteger id, @RequestBody Municipio entity) throws Exception {
        Optional<Municipio> estadoAmazenado = repositorio.findById(id);
        if (estadoAmazenado.isPresent()) {
            // Atribuir novo nome ao objeto já existem no banco de dados
            estadoAmazenado.get().setNome(entity.getNome());
            //
            return repositorio.save(estadoAmazenado.get());
        }
        throw new Exception("Alteração não foi realizada.");
    }

    @DeleteMapping("/municipio/{id}")
    public String deleteMunicipio(@PathVariable BigInteger id) throws Exception {

        Optional<Municipio> municipoArmazenado = repositorio.findById(id);
        if (municipoArmazenado.isPresent()) {
            repositorio.delete(municipoArmazenado.get());
            return "Excluído";
        }
        throw new Exception("Id não econtrado para a exclusão");
    }

}
