package com.projetoautomacao.drogaria.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetoautomacao.drogaria.controller.exception.FieldMessage;
import com.projetoautomacao.drogaria.dto.UsuarioNewDTO;
import com.projetoautomacao.drogaria.model.enums.TipoUsuario;
import com.projetoautomacao.drogaria.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, UsuarioNewDTO> {


	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoUsuario.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoUsuario.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

//		Usuario aux = repo.findByEmail(objDto.getEmail());
//		if (aux != null) {
//			list.add(new FieldMessage("email", "Email já existente"));
//		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

