package br.com.sistemalanchonete.despesa.dto;

import java.util.List;

import br.com.sistemalanchonete.despesa.enums.TipoDespesa;
import br.com.sistemalanchonete.utils.conversao.enums.ConversaoDadosEnum;
import br.com.sistemalanchonete.utils.conversao.enums.DadosEnum;
import lombok.Getter;

@Getter
public class InformacoesCadastroDespesaDTO {

	private List<DadosEnum> tiposDespesa;
	
	
	public InformacoesCadastroDespesaDTO() {
		tiposDespesa = ConversaoDadosEnum.converterDadosEnum(TipoDespesa.values());
	}
}
