package br.com.sistemalanchonete.fornecedor.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.sistemalanchonete.exception.custom.FileException;

@Service
public class ImagemLancheService {

	public static final List<String> EXTENSOES_PERMITIDAS = Arrays.asList("png", "jpg");
	
	
	public String encodeBase64(MultipartFile imagem) {
		verificarSeImagemEValida(imagem);
		verificarTamanhoImagem(imagem);
		
		try {
			byte[] bytesImagem = imagem.getBytes();
			return Base64.getEncoder().encodeToString(bytesImagem);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		throw new FileException("Erro ao codificar Imagem!");
	}
	
	
	private void verificarSeImagemEValida(MultipartFile imagem) {
		String extensao = imagem.getOriginalFilename().split("\\.")[1];
		
		if (!EXTENSOES_PERMITIDAS.contains(extensao)) {
			String extensoesPermitidas = EXTENSOES_PERMITIDAS.stream().collect(Collectors.joining(", "));
			throw new FileException("Somente extensões " + extensoesPermitidas + " são permitidas!");
		}
		
		if (imagem.getSize() > 500000L) {
			throw new FileException("O tamanho da Imagem deve ter no máximo 0.5MB!");
		}
	}
	
	private void verificarTamanhoImagem(MultipartFile imagem) {
		try {
			BufferedImage bufferedImage = ImageIO.read(imagem.getInputStream());
			
			if (bufferedImage.getHeight() > 500 || bufferedImage.getWidth() > 500) {
				throw new FileException("A resolução da Imagem deve ser no máximo x bufferedImage.getWidth()!");
			}
		} 
		catch (IOException e) {
			throw new FileException("Erro ao ler a imagem!");
		}
	}
}
