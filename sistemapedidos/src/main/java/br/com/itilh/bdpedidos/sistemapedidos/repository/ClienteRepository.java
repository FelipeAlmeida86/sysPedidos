package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, BigInteger> {

    List<Cliente> findByRazaoSocial(String razaoSocial);

    List<Cliente> findByRazaoSocialStartingWithIgnoreCase(String razaoSocial);

    List<Cliente> findByRazaoSocialEndingWithIgnoreCase(String razaoSocial);

    List<Cliente> findByRazaoSocialContainingIgnoreCase(String razaoSocial);

}
