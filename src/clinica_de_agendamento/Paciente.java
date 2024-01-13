package clinica_de_agendamento;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Paciente {

	private String nome;
	private String telefone;
	private String especialidade;
	
	int chave = 1;

	
	//Map<Integer,Paciente> listaPaciente = new TreeMap();

	public Paciente(String nome, String telefone,String especialidade) {
		this.nome = nome;
		this.telefone = telefone;
		this.especialidade = especialidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

	@Override
	public String toString() {
		return nome + " / Telefone: " + telefone + "/ Especialidade: " + especialidade;
	}

}
