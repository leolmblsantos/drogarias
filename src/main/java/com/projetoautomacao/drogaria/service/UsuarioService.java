package com.projetoautomacao.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoautomacao.drogaria.dto.UsuarioDTO;
import com.projetoautomacao.drogaria.dto.UsuarioNewDTO;
import com.projetoautomacao.drogaria.model.Cidade;
import com.projetoautomacao.drogaria.model.Endereco;
import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.model.enums.TipoUsuario;
import com.projetoautomacao.drogaria.repository.EnderecoRepository;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;
import com.projetoautomacao.drogaria.service.exceptions.DataIntegrityException;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Usuario find(Integer id) {
			
		Optional<Usuario> obj = usuarioRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = usuarioRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return usuarioRepository.save(newObj);
	}

	/* ----- Serviço de delete ------ */
	public void delete(Integer id) {
		find(id);
		try {
			usuarioRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma usuario que possui produtos");
		}
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usu = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoUsuario.toEnum(objDto.getTipo()), bCryptPasswordEncoder.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null); 
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), usu, cid);
		usu.getEnderecos().add(end);
		usu.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2()!=null) {
			usu.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			usu.getTelefones().add(objDto.getTelefone3());
		}
		return usu;
		
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
