package br.com.sistemalanchonete.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public final class RelatorioUtils {
	
	private static final String PATH_ARQUIVO = "src\\main\\resources\\relatorios\\";
	

	public static final ResponseEntity<byte[]> gerarRelatorioEmPDF(String nomeArquivo, Map<String, Object> parametrosRelatorio) {
		try {
			File arquivo = ResourceUtils.getFile(PATH_ARQUIVO + nomeArquivo + ".jrxml");
			
			JasperReport jasperReport = JasperCompileManager.compileReport(arquivo.getAbsolutePath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametrosRelatorio, new JREmptyDataSource());
			
			byte[] relatorio = JasperExportManager.exportReportToPdf(jasperPrint);
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
		} 
		catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
