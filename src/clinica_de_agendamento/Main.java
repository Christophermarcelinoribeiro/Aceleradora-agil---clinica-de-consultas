package clinica_de_agendamento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Servico.ListaAgendamento;

public class Main {
	public static void menu() {
		System.out.println("1 - Cadastrar paciente");
		System.out.println("2 - Marcar consulta");
		System.out.println(" ");
	}

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		ListaAgendamento l = new ListaAgendamento();
		ArrayList<String> LitsaTelefone = new ArrayList<String>();

		int escolha = -1;

		while (escolha != 0) {
			menu();
			System.out.println("Escolha um op��o: ");
			escolha = sc.nextInt();

			switch (escolha) {
			case 1 -> {
				System.out.print("Nome completo: ");
				sc.nextLine();
				String nome = sc.nextLine();
				System.out.print("Telefone:");
				String telefone = sc.next();
				if (l.verificaCadastroDuplicado(LitsaTelefone, telefone) == false) {
					System.out.println("Paciente j� cadastado!");
					return;
				} else {
					LitsaTelefone.add(telefone);
				}
				System.out.print("Especialidade:");
				String especialidade = sc.next();
				Paciente p = new Paciente(nome, telefone, especialidade);
				l.cadastroPaciente(p);

			}
			case 2 -> {
				l.agendamento();
				l.mostraAgendamento();
			}

			}

			/*
			 * l.cadastroPaciente(p); l.verificaCadastroDuplicado(telefone);
			 * l.cadastroPaciente(p1);
			 * 
			 * l.agendamento();
			 * 
			 * System.out.println();
			 * 
			 * l.mostraAgendamento();
			 * 
			 * System.out.println();
			 * 
			 * l.agendamento();
			 * 
			 * l.mostraAgendamento();
			 * 
			 * System.out.println();
			 */

			// p.MostraPaciente();
			
		}
	}

}
