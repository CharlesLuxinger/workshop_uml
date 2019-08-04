package com.charlesluxinger.workshop_uml;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.charlesluxinger.workshop_uml.domain.Categoria;
import com.charlesluxinger.workshop_uml.domain.Cidade;
import com.charlesluxinger.workshop_uml.domain.Cliente;
import com.charlesluxinger.workshop_uml.domain.Endereco;
import com.charlesluxinger.workshop_uml.domain.Estado;
import com.charlesluxinger.workshop_uml.domain.Produto;
import com.charlesluxinger.workshop_uml.domain.enums.TipoCliente;
import com.charlesluxinger.workshop_uml.repositories.CategoriaRepository;
import com.charlesluxinger.workshop_uml.repositories.CidadeRepository;
import com.charlesluxinger.workshop_uml.repositories.ClienteRepository;
import com.charlesluxinger.workshop_uml.repositories.EnderecoRepository;
import com.charlesluxinger.workshop_uml.repositories.EstadoRepository;
import com.charlesluxinger.workshop_uml.repositories.ProdutoRepository;

@SpringBootApplication
public class WorkshopUmlApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(WorkshopUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@email.com", "01101201314", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2733385431", "2798884545"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "31", "Casa", "Chacara Parreiral", "29164335", c1, cli1);
		Endereco e2 = new Endereco(null, "Rua Mattos", "109", "Apto 1502", "Praia do Canto", "29055350", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
