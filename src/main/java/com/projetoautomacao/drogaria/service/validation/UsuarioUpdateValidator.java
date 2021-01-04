package com.projetoautomacao.drogaria.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.projetoautomacao.drogaria.controller.exception.FieldMessage;
import com.projetoautomacao.drogaria.dto.UsuarioDTO;
import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioUpdate ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {
		
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		/* ==== Método PUT não atualiza CPF e CNPJ ==== */		
		
//		if (objDto.getTipo().equals(TipoUsuario.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
//			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
//		}
//
//		if (objDto.getTipo().equals(TipoUsuario.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
//			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
//		}

		Usuario aux = usuarioRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

