package clinica_de_agendamento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Servico.ListaAgendamento;

public class Main {
	public static void menu() {
		
		System.out.println("0 - Sair");
		System.out.println("1 - Cadastrar paciente");
		System.out.println("2 - Marcar consulta");
		System.out.println("3 - Cancelar consulta");
		System.out.println("4 - Consulta paciente");
		System.out.println("5 - Consulta agendamento");

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
			case 0 -> {
				escolha = 0;
			}
			case 1 -> {
				System.out.print("Nome completo: ");
				sc.nextLine();
				String nome = sc.nextLine();
				System.out.print("Telefone:");
				String telefone = sc.next();
				if (l.verificaCadastroDuplicado(LitsaTelefone, telefone) == false) {
					System.out.println("Paciente j� cadastrado!");
					System.out.println();
					break;
				} else {
					LitsaTelefone.add(telefone);
				}
				Paciente p = new Paciente(nome, telefone);
				l.cadastroPaciente(p);

			}
			case 2 -> l.agendamento();
			case 3 -> l.cancelaAgendamento();	
			case 4 -> l.mostraPaciente();
			case 5 -> l.mostraAgendamento();

			}

		}
		sc.close();
	}

}
