package br.com.sistemalanchonete.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;


public final class ConversaoUtils {
	
	public static final String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime))
			throw new NullPointerException("LocalDateTime não pode ser nulo!");
		
		try {
			return localDateTime.format(FormatacaoUtils.FORMATADOR_DATA_HORA_MINUTO);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data e/ou Hora inválido para conversão em String!");
		}
	}
	
	
	public static final String converterLocalDateTimeParaFrontEndEmString(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime))
			throw new NullPointerException("LocalDateTime não pode ser nulo!");
		
		try {
			return converterLocalDateParaString(localDateTime.toLocalDate()) + " às " 
					+ converterLocalTimeParaString(localDateTime.toLocalTime()) + "h";
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data e/ou Hora inválido para conversão em String!");
		}
	}
	
	
	public static final String converterLocalDateTimeParaStringDataHoraMinuto(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime))
			throw new NullPointerException("LocalDateTime não pode ser nulo!");
		
		try {
			return localDateTime.format(FormatacaoUtils.FORMATADOR_DATA_HORA_MINUTO);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data e/ou Hora inválido para conversão em String!");
		}
	}
	
	
	public static final String converterLocalTimeParaString(LocalTime localTime) {
		if (Objects.isNull(localTime))
			throw new NullPointerException("LocalTime não pode ser nulo!");
		
		try {
			return FormatacaoUtils.FORMATADOR_HORA_MINUTO.format(localTime);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Hora inválido para conversão em String!");
		}
	}
	
	
	public static final String converterLocalDateParaString(LocalDate localDate) {
		if (Objects.isNull(localDate))
			throw new NullPointerException("LocalDate não pode ser nulo!");
		
		try {
			return localDate.format(FormatacaoUtils.FORMATADOR_DATA);
		}
		catch (DateTimeException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em String!");
		}
	}
	
	
	public static final LocalDate converterStringParaLocalDate(String data) {
		if (Objects.isNull(data))
			throw new NullPointerException("Data não pode ser nula!");
		
		try {
			return LocalDate.parse(data, FormatacaoUtils.FORMATADOR_DATA);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em LocalDate!");
		}
	}
	
	
	public static final LocalDate converterStringParaLocalDate(String data, DateTimeFormatter formatter) {
		if (Objects.isNull(data))
			throw new NullPointerException("Data não pode ser nula!");
		
		try {
			return LocalDate.parse(data, formatter);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Data inválido para conversão em LocalDate!");
		}
	}
	
	
	public static final LocalTime converterStringParaLocalTime(String hora) {
		if (Objects.isNull(hora))
			throw new NullPointerException("Hora não pode ser nula!");
		
		try {
			return LocalTime.parse(hora, FormatacaoUtils.FORMATADOR_HORA);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Hora inválido para conversão em LocalTime!");
		}
	}
	
	
	public static final LocalTime converterStringParaLocalTimeHoraMinuto(String hora) {
		if (Objects.isNull(hora))
			throw new NullPointerException("Hora não pode ser nula!");
		
		try {
			return LocalTime.parse(hora, FormatacaoUtils.FORMATADOR_HORA_MINUTO);
		}
		catch (DateTimeParseException e) {
			throw new DateTimeException("Formato de Hora inválido para conversão em LocalTime!");
		}
	}
}
