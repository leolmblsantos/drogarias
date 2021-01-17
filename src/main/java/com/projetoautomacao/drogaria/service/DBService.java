package com.projetoautomacao.drogaria.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Categoria;
import com.projetoautomacao.drogaria.model.Cidade;
import com.projetoautomacao.drogaria.model.Empresa;
import com.projetoautomacao.drogaria.model.Endereco;
import com.projetoautomacao.drogaria.model.Estado;
import com.projetoautomacao.drogaria.model.Fabricante;
import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.model.Produto;
import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.model.enums.Perfil;
import com.projetoautomacao.drogaria.model.enums.TipoUsuario;
import com.projetoautomacao.drogaria.repository.CategoriaRepository;
import com.projetoautomacao.drogaria.repository.CidadeRepository;
import com.projetoautomacao.drogaria.repository.EmpresaRepository;
import com.projetoautomacao.drogaria.repository.EnderecoRepository;
import com.projetoautomacao.drogaria.repository.EstadoRepository;
import com.projetoautomacao.drogaria.repository.FabricanteRepository;
import com.projetoautomacao.drogaria.repository.PrincipioAtivoRepository;
import com.projetoautomacao.drogaria.repository.ProdutoRepository;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;


@Service
public class DBService {
	
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
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void instantiateTestDatabase() throws ParseException {
		
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
		
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3, p5));
		cat3.getProdutos().addAll(Arrays.asList(p4,p6, p7));
		cat4.getProdutos().addAll(Arrays.asList(p5,p6, p10));
		cat5.getProdutos().addAll(Arrays.asList(p5,p10));
		cat6.getProdutos().addAll(Arrays.asList(p8,p9, p11));
		cat7.getProdutos().addAll(Arrays.asList(p5,p10));
		
		pa1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		pa2.getProdutos().addAll(Arrays.asList(p4));
		
		fa1.getProdutos().addAll(Arrays.asList(p4));
		fa2.getProdutos().addAll(Arrays.asList(p1, p2));
		fa3.getProdutos().addAll(Arrays.asList(p3));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat5, cat6));
		p6.getCategorias().addAll(Arrays.asList(cat7));
		p7.getCategorias().addAll(Arrays.asList(cat8));
		p8.getCategorias().addAll(Arrays.asList(cat9));
		p9.getCategorias().addAll(Arrays.asList(cat5, cat9));
		p10.getCategorias().addAll(Arrays.asList(cat6, cat8));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
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
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "Goias", "GO");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Goiânia", est2);
		Cidade c3 = new Cidade(null, "Mossâmedes", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Usuario cli1 = new Usuario(null, "Leonardo Francisco dos Santos", "leolmblsantos@gmail.com", "36378912377", TipoUsuario.PESSOAFISICA, bCryptPasswordEncoder.encode("123") );
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Usuario cli2 = new Usuario(null, "Maria umbelina de Oliveira Santos", "leolhml@gmail.com", "31628382740", TipoUsuario.PESSOAFISICA,bCryptPasswordEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		cli2.addPerfil(Perfil.ADMIN);
		
		Empresa emp1 = new Empresa(null, "Drogaria 1", "86325750000159", "aldeiaguanabara@email.com");
		Empresa emp2 = new Empresa(null, "Drogaria 2", "91355092000188", "aldeiagatopreto@email.com");
		emp1.getTelefoneEmpresa().addAll(Arrays.asList("643771211"));
		emp2.getTelefoneEmpresa().addAll(Arrays.asList("643770000"));
		
		Endereco e1 = new Endereco(null, "Avenida 85", "300", "Apto 303", "Setor Marista", "38220834", cli1, c1, emp1);
		Endereco e2 = new Endereco(null, "Avenida Goias", "105", "Sala 800", "Centro", "38777012", cli1, c2, emp2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c3, null);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
//		emp1.getEnderecosEmpresa().addAll(Arrays.asList(e1));
//		emp2.getEnderecosEmpresa().addAll(Arrays.asList(e2));

		empresaRepository.saveAll(Arrays.asList(emp1, emp2));
		usuarioRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
	}

}
