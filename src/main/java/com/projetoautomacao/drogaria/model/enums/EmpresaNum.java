package com.projetoautomacao.drogaria.model.enums;

public enum EmpresaNum {

	EMPRESA1(1, "Loja - 1"),
	EMPRESA2(2, "Loja - 2");
	
	private int cod;
	private String descricao;
	
	private EmpresaNum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static EmpresaNum toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (EmpresaNum x : EmpresaNum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}