package br.com.sistemalanchonete.lanche.dto;

import lombok.Getter;

@Getter
public class ImagemLancheBase64DTO {

	private String imagemBase64;
	
	
	public ImagemLancheBase64DTO(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}
}
