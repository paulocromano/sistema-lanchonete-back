package br.com.sistemalanchonete.utils;


public final class RegexUtils {

	public static final String DATA = "(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}";
	public static final String HORA_MINUTO = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
	
	public static final String EMAIL = "^[-a-zA-Z0-9][-.a-zA-Z0-9]*@[-.a-zA-Z0-9]+(\\.[-.a-zA-Z0-9]+)*\\."
			+ "(com|edu|info|gov|int|mil|net|org|biz|name|museum|coop|aero|pro|tv|[a-zA-Z]{2})$";
	public static final String SENHA = "((?=.*\\d)(?=.*[A-Z])(?=.*\\W).{6,20})";
	
	public static final String TELEFONE = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
	public static final String CEP = "[0-9]{5}-[0-9]{3}";
	public static final String CNPJ = "(\\d{2})\\.(\\d{3})\\.(\\d{3})\\/(\\d{4})-(\\d{2})";
}
