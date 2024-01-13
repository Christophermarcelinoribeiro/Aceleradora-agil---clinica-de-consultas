package Servico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import clinica_de_agendamento.Paciente;

public class ListaAgendamento {
	Scanner sc = new Scanner(System.in);
	private Paciente paciente;
	private LocalDateTime dataAgendamento;
	int chave = 1;
	int chaveAgendamento = 1;
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	Map<Integer, Paciente> listaPaciente = new TreeMap<Integer, Paciente>();
	ListaAgendamento agendamento;
	Map<Integer, ListaAgendamento> listaAgendamento = new TreeMap<Integer, ListaAgendamento>();

	public ListaAgendamento() {
	}

	public ListaAgendamento(Paciente paciente, LocalDateTime dataAgendamento) {
		this.paciente = paciente;
		this.dataAgendamento = dataAgendamento;
	}

	public boolean verificaCadastroDuplicado(ArrayList<String> LitsaTelefone, String telefone) {
		for (int i = 0; i < LitsaTelefone.size(); i++) {
			if (LitsaTelefone.get(i).equals(telefone)) {
				return false;
			}
		}
		return true;

	}

	public void cadastroPaciente(Paciente p) {
		listaPaciente.put(chave, p);
		chave++;
		System.out.println("Paciente cadastado!");
		System.out.println();
	}

	public void mostraPaciente() {
		if (listaPaciente.isEmpty()) {
			System.out.println("Lista de paciente vazia!");
		} else {
			System.out.println("Lista de pacientes cadastrados: ");
			for (Integer key : listaPaciente.keySet()) {
				System.out.println(key + ":" + listaPaciente.get(key));
			}
		}

	}

	public boolean verificaAgendamentoDuplicado() {
		if (this.agendamento == null) {
			return true;
		} else {
			if (agendamento.dataAgendamento.equals(dataAgendamento)) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void agendamento() {
		mostraPaciente();
		System.out.println("Escolha o numero do paciente para marcar agendamento: ");
		int escolha = sc.nextInt();
		sc.nextLine();

		if (!listaPaciente.containsKey(escolha)) {
			System.out.println("Não há paciente com essa numeração!");
		} else {
			System.out.print("Insira a data da consulta: (dd/MM/yyyy HH:mm): ");
			dataAgendamento = LocalDateTime.parse(sc.nextLine(), fmt);
			LocalDateTime dateAtual = LocalDateTime.now();
			if (dataAgendamento.isBefore(dateAtual)) {
				System.err.println("Não é possivel agendar datas retroativas!");
			} else {
				if (verificaAgendamentoDuplicado() == false) {
					System.out.println("Consulta já agendada para esse horário!");
				} else {
					agendamento = new ListaAgendamento(listaPaciente.get(escolha), dataAgendamento);
					listaAgendamento.put(chaveAgendamento, agendamento);
					chaveAgendamento++;
					System.out.println("Consulta agendada com sucesso!");

				}
			}

		}
	}

	public void mostraAgendamento() {
		if (listaAgendamento.isEmpty()) {
			System.out.println("Lista de agendamento vazia!");

		} else {
			for (Integer key : listaPaciente.keySet()) {
				System.out.println(key + ":" + listaAgendamento.get(key));
			}
		}

	}

	public void cancelaAgendamento() {

		if (listaAgendamento.isEmpty()) {
			System.out.println("Não há pacientes agendados!");
		} else {
			mostraAgendamento();
			System.out.println("Escolha o numero do paciente para cancelar o agendamento");
			int escolha = sc.nextInt();
			for (Integer key : listaPaciente.keySet()) {
				if(key.equals(escolha)) {
					listaAgendamento.remove(escolha, agendamento);
					System.out.println("Consulta cancelada!");
					return;
				}
			}
			System.out.println("Não há numero de agendamento com o qual foi especificado!");
		}
	}

	@Override
	public String toString() {
		return paciente.getNome() + " / Data: " + dataAgendamento.format(fmt);
	}
}
