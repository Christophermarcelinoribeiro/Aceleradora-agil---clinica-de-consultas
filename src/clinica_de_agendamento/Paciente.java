package clinica_de_agendamento;


public class Paciente {

	private String nome;
	private String telefone;
	private String especialidade;
	

	public Paciente(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
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


	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}


	@Override
	public String toString() {
		return nome + " / Telefone: " + telefone;
	}

}
