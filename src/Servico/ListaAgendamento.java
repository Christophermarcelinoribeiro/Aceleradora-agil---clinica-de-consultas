package Servico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private String especialidade;
	int chave = 1;
	int chaveAgendamento = 1;
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	Map<Integer, Paciente> listaPaciente = new TreeMap<Integer, Paciente>();
	ListaAgendamento agendamento;
	Map<Integer, ListaAgendamento> listaAgendamento = new TreeMap<Integer, ListaAgendamento>();
	ArrayList<ListaAgendamento> listaAgend = new ArrayList<>();

	public ListaAgendamento() {
	}

	public ListaAgendamento(Paciente paciente, LocalDateTime dataAgendamento, String especialidade) {
		this.paciente = paciente;
		this.dataAgendamento = dataAgendamento;
		this.especialidade = especialidade;
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
		System.out.println("Paciente cadastrado!");
		System.out.println();
	}

	public boolean mostraPaciente() {
		if (listaPaciente.isEmpty()) {
			System.out.println("Lista de paciente vazia!");
			return false;
		} else {
			System.out.println("Lista de pacientes cadastrados: ");
			for (Integer key : listaPaciente.keySet()) {
				System.out.println(key + ":" + listaPaciente.get(key));
			}
		}
		return true;
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
		if (mostraPaciente() == false) {
			return;
		} else {
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
						System.err.println("Consulta já agendada para esse horário!");
					} else {
						System.out.print("Epscialidade: ");
						String especialidade = sc.next();
						agendamento = new ListaAgendamento(listaPaciente.get(escolha), dataAgendamento, especialidade);
						listaAgendamento.put(chaveAgendamento, agendamento);
						chaveAgendamento++;
						listaAgend.add(agendamento);
						System.out.println("Consulta agendada com sucesso!");

					}
				}

			}
		}

	}

	public void mostraAgendamento() {
		if (listaAgendamento.isEmpty()) {
			System.out.println("Lista de agendamento vazia!");

		} else {
			for (Integer key : listaPaciente.keySet()) {
				if (listaAgendamento.get(key) == null) {
					System.out.println("");
				} else {
					System.out.println(key + ":" + listaAgendamento.get(key));
				}

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
				if (key.equals(escolha)) {
					listaAgendamento.remove(escolha, agendamento);
					System.out.println("Consulta cancelada!");
					return;
				}
			}
			System.out.println("Não há numero de agendamento com o qual foi especificado!");
		}
	}

	/*
	 * ESSAS 2 FUNÇÕES QUE EU DEIXEI COMENTADAS TINHAM O OBEJIVO DE SE COMPORTAR
	 * COMO UM "BANCO DE DADOS" USANDO FUNÇÕES DE LEITURA E ESCRITA DE UM ARQUIVO
	 * TXT, PORÉM COMO O TEMPO DE ENTREGA ESTAVA ACABANDO NÃO CONSEGUI TERMINAR.
	 * ENTÃO DEIXEI COMENTADA PARA QUE EU POSSA SUBIR PARA O MEU GIT E AO LONGO DA
	 * SEMANA ARRUME AS FUNCÕES GIT: https://github.com/Christophermarcelinoribeiro
	 */
	/*
	 * public void escreveInformacoes() { String caminho =
	 * "C:\\Users\\Windows\\eclipse-workspace\\curso udemy java\\clinica_de_agendamento\\src\\clinica_de_agendamento\\informacoes_pacientes.txt"
	 * ; String[] lines = new String[] { listaAgendamento.toString() };
	 * 
	 * try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true))) {
	 * for (String line : lines) { bw.write(line.replace("{", "").replace("=",
	 * ",").replace("/ Data:", ",") .replace("/ Especialidade:", ",").replace("}",
	 * ""));
	 * 
	 * bw.newLine(); }
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * public void leInformacoes() { String caminho =
	 * "C:\\Users\\Windows\\eclipse-workspace\\curso udemy java\\clinica_de_agendamento\\src\\clinica_de_agendamento\\informacoes_pacientes.txt"
	 * ;
	 * 
	 * try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
	 * String info = br.readLine();
	 * 
	 * while (info != null) { String[] fields = info.split(","); String chave =
	 * fields[0].trim(); String nome = fields[1].trim(); String data =
	 * fields[2].trim(); String especialidade = fields[3].trim(); LocalDateTime
	 * dateTime = LocalDateTime.parse(data, fmt);
	 * 
	 * Paciente p = new Paciente(nome, " ", especialidade); agendamento = new
	 * ListaAgendamento(p,dateTime); /* Paciente p = new Paciente(fields[1], " ",
	 * fields[2]); // dataAgendamento = LocalDateTime.parse(fields[2], fmt);
	 * agendamento = new ListaAgendamento(p, LocalDateTime.parse(fields[2], fmt));
	 * 
	 * listaAgendamento.put(Integer.parseInt(fields[0]), agendamento);
	 * 
	 * info = br.readLine(); System.out.println(agendamento);
	 * 
	 * } } catch (IOException e) { System.out.println("Error: " + e.getMessage()); }
	 * 
	 * /* System.out.println("Super teste:"); for (Integer key :
	 * listaPaciente.keySet()) { System.out.println(key + ":" +
	 * listaAgendamento.get(key)); }
	 * 
	 * }
	 */
	@Override
	public String toString() {
		return paciente.getNome() + " / Data: " + dataAgendamento.format(fmt) + " / Especialidade: "
				+ this.especialidade;
	}
}
