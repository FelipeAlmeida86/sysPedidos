package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ClienteController {

    private final ClienteRepository repositorio;

    public ClienteController(ClienteRepository repositorio) {
        this.repositorio = repositorio;

    }

    @GetMapping("/clientes")
    public List<Cliente> getclientes() {
        return (List<Cliente>) repositorio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClientePorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("Id não encontrado"));
    }

    @PostMapping("/cliente")
    public Cliente postCliente(@RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível criar o cliente." + ex.getMessage());
        }
    }

    @PutMapping("/cliente/{id}")
    public Cliente putCliente(@PathVariable BigInteger id, @RequestBody Cliente entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível alterar o mcliente." + ex.getMessage());
        }
    }

    @DeleteMapping("/Cliente/{id}")
    public String delereCliente(@PathVariable BigInteger id) throws Exception {
        try {
            repositorio.deleteById(id);
            return "Excluído";
        } catch (Exception ex) {
            throw new Exception("Não foi possível excluir o município." + ex.getMessage());
        }
    }

}
