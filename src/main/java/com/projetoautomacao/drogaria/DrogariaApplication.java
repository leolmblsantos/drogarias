package com.projetoautomacao.drogaria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetoautomacao.drogaria.model.Categoria;
import com.projetoautomacao.drogaria.model.Cidade;
import com.projetoautomacao.drogaria.model.Endereco;
import com.projetoautomacao.drogaria.model.Estado;
import com.projetoautomacao.drogaria.model.Fabricante;
import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.model.Produto;
import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.model.enums.TipoUsuario;
import com.projetoautomacao.drogaria.repository.CategoriaRepository;
import com.projetoautomacao.drogaria.repository.CidadeRepository;
import com.projetoautomacao.drogaria.repository.EnderecoRepository;
import com.projetoautomacao.drogaria.repository.EstadoRepository;
import com.projetoautomacao.drogaria.repository.FabricanteRepository;
import com.projetoautomacao.drogaria.repository.PrincipioAtivoRepository;
import com.projetoautomacao.drogaria.repository.ProdutoRepository;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;

@SpringBootApplication
public class DrogariaApplication implements CommandLineRunner {
	
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
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrincipioAtivoRepository principioAtivoRepository;
	
	@Autowired
	private FabricanteRepository fabricanteRepository;

	public static void main(String[] args) {
		SpringApplication.run(DrogariaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Perfumaria");
		Categoria cat2 = new Categoria(null, "Frauda");
		Categoria cat3 = new Categoria(null, "Medicamento Genérico");
		Categoria cat4 = new Categoria(null, "Medicamento Ético");
		Categoria cat5 = new Categoria(null, "Sorvete");
		Categoria cat6 = new Categoria(null, "Medicamento Similar");
		Categoria cat7 = new Categoria(null, "Medicamento Géneriao PBM");
		Categoria cat8 = new Categoria(null, "Medicamento Ético PBM");
		Categoria cat9 = new Categoria(null, "Medicamento Similar PBM");
		
		PrincipioAtivo pa1 = new PrincipioAtivo(null, "Não informado");
		PrincipioAtivo pa2 = new PrincipioAtivo(null, "Losartan Potasico");
		
		Fabricante fa1 = new Fabricante(null, "Cozzar", "Coozzar", "78787878", "cozzar@email.com");
		Fabricante fa2 = new Fabricante(null, "Unilever", "Unilever", "98989889", "unilever@email.com");
		Fabricante fa3 = new Fabricante(null, "Huggies", "Huggies", "852588888", "huggies@email.com");
		
		
		Produto p1 = new Produto(null, "Desodorante", 14.00);
		Produto p2 = new Produto(null, "Creme corpo", 14.00);
		Produto p3 = new Produto(null, "Frauda Turma da Monica", 18.90);
		Produto p4 = new Produto(null, "Losartana", 5.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));
		cat3.getProdutos().addAll(Arrays.asList(p4));
		
		pa1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		pa2.getProdutos().addAll(Arrays.asList(p4));
		
		fa1.getProdutos().addAll(Arrays.asList(p4));
		fa2.getProdutos().addAll(Arrays.asList(p1, p2));
		fa3.getProdutos().addAll(Arrays.asList(p3));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		
		p1.getPrincipioAtivos().addAll(Arrays.asList(pa1));
		p2.getPrincipioAtivos().addAll(Arrays.asList(pa1));
		p3.getPrincipioAtivos().addAll(Arrays.asList(pa1));
		p4.getPrincipioAtivos().addAll(Arrays.asList(pa2));
		
		p1.getFabricantes().addAll(Arrays.asList(fa2));
		p2.getFabricantes().addAll(Arrays.asList(fa2));
		p3.getFabricantes().addAll(Arrays.asList(fa3));
		p4.getFabricantes().addAll(Arrays.asList(fa1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		principioAtivoRepository.saveAll(Arrays.asList(pa1, pa2));
		fabricanteRepository.saveAll(Arrays.asList(fa1, fa2, fa3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "Goias", "GO");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Goiânia", est2);
		Cidade c3 = new Cidade(null, "Mossâmedes", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Usuario cli1 = new Usuario(null, "Leonardo Francisco dos Santos", "leolmblsantos@gmail.com", "36378912377", TipoUsuario.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Usuario cli2 = new Usuario(null, "Maria umbelina de Oliveira Santos", "leolhml@gmail.com", "31628382740", TipoUsuario.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
//		cli2.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Avenida 85", "300", "Apto 303", "Setor Marista", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Goias", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		usuarioRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
	}
}
