package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

@RestController
public class ProdutoController {
    private final ProdutoRepository repositorio;

    public ProdutoController(ProdutoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/produtos")
    public List<Produto> getProduto() {
        return (List<Produto>) repositorio.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("Id não encontrado"));
    }

    @PostMapping("/produto")
    public Produto postProduto(@RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível criar o produto." + ex.getMessage());
        }
    }

    @PutMapping("/produto/{id}")
    public Produto putProduto(@PathVariable BigInteger id, @RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível alterar o produto." + ex.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}")
    public String delereProduto(@PathVariable BigInteger id) throws Exception {
        try {
            repositorio.deleteById(id);
            return "Excluído";
        } catch (Exception ex) {
            throw new Exception("Não foi possível excluir o Produto." + ex.getMessage());
        }
    }

}
