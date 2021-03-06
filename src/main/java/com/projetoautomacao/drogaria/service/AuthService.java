package com.projetoautomacao.drogaria.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;


@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
/* ============= Random gera caracteres aleatorios ============== */
	private Random rand = new Random();
	
	
	
	public void sendNewPassword(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPass);
	}


/* ========== Gera uma senha com 10 caracteres ============== */
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}



	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}else if(opt == 1) { //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula  //tabela unicode-table.com
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
