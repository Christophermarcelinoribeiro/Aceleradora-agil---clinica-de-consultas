
/*OBS: parai na criação do map para listaagendamento, pois o agendamento tambem tem que ter enumeração*/

package Servico;

import java.rmi.AccessException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import clinica_de_agendamento.Paciente;

public class ListaAgendamento {
	private Paciente paciente;
	private LocalDateTime dataAgendamento;

	Map<Integer, Paciente> listaPaciente = new TreeMap();
	ListaAgendamento agendamento;
	Map<Integer, ListaAgendamento> listaAgendamentoTeste = new TreeMap();
	ArrayList<ListaAgendamento> listaAgendamento = new ArrayList();

	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	int chave = 1;
	int chaveAgendamento = 1;

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
	}

	public void mostraPaciente() {
		if (listaPaciente.isEmpty()) {
			System.out.println("Lista vazia!");

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
		Scanner sc = new Scanner(System.in);
		mostraPaciente();
		System.out.println("Escolha o numero do paciente para marcar agendamento: ");
		int escolha = sc.nextInt();
		sc.nextLine();

		if (!listaPaciente.containsKey(escolha)) {
			System.out.println("Não há paciente com essa numeração!");
		} else {
			System.out.print("Insira a data da consulta: (dd/MM/yyyy HH:mm): ");
			dataAgendamento = LocalDateTime.parse(sc.nextLine(), fmt);
			Date date = new Date();
			LocalDateTime dateTestes = LocalDateTime.now();
			if (dataAgendamento.isBefore(dateTestes)) {
				System.err.println("Não é possivel agendar datas retroativas!");
				return;
			} else {
				if (verificaAgendamentoDuplicado() == false) {
					System.out.println("Consulta já agendada para esse horário!");
				} else {
					agendamento = new ListaAgendamento(listaPaciente.get(escolha), dataAgendamento);
					listaAgendamentoTeste.put(chaveAgendamento, agendamento);
					chaveAgendamento++;
					listaAgendamento.add(agendamento);
				}
			}

		}
	}

	public void mostraAgendamento() {
		if (listaAgendamentoTeste.isEmpty()) {
			System.out.println("Lista vazia!");

		} else {
			for (Integer key : listaPaciente.keySet()) {
				System.out.println(key + ":" + listaAgendamentoTeste.get(key));
			}
		}

	}

	@Override
	public String toString() {
		return paciente.getNome() + " / Data: " + dataAgendamento.format(fmt);
	}
}
