package com.projetoautomacao.drogaria.service;

import org.springframework.mail.SimpleMailMessage;

import com.projetoautomacao.drogaria.model.Usuario;

public interface EmailService {
	
//void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);

}
