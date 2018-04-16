package banco;

import java.util.List;
import java.util.Scanner;

import logica.Agencia;
import persistencia.AgenciaDAO;

public class Application {

	public static void main(String[] args) {
		MenuPrincipal();
	}

	
	public static void MenuPrincipal() {
		int selection;
		String menuTitle = "======== MENU PRINCIPAL ========";
		String[] opcoesMenu = {"Criar um cliente", "Listar os clientes", "Opções do cliente", 
				"Criar uma agência", "Listar as agências", "Opções da agência", "Sair" };

		do {
		selection = DisplayMenu(menuTitle, opcoesMenu);		

		
		switch (selection) {
		case 0:
			System.out.println("Create insertActor = new Create() ");
			break;
		case 1:
			System.out.println("Read()");
			break;
		case 2:
			MenuOptCliente();
			break;
		case 3:			
			insereAgencia();
			break;
		case 4:
			listarAgencias();
			break;
		case 6:
			System.out.println("Bye.");
			break;

		default:
			System.out.println("Opção inválida!");
			break;
		}
		} while (selection != 6);

	} // Fim do método: MenuPrincipal()
	
	

	
	public static void MenuOptCliente() {
		int selection;
		String menuTitle = "====== MENU OPÇÕES CLIENTE =====";
		String[] opcoesMenu = {"Atualizar cliente", "Apagar cliente", "Listar contas", 
				"Listar cartoes", "Listar movimentos", "Opções de conta", "Menu anterior" };
		selection = DisplayMenu(menuTitle, opcoesMenu);

		switch (selection) {
		case 0:
			System.out.println("UpdateCliente()");
			break;
		case 1:
			System.out.println("DeleteCliente()");
			break;
		case 2:
			System.out.println("ReadContas()");
			break;
		case 3:
			System.out.println("ReadCartoes()");
			break;
		case 4:
			System.out.println("ReadMovimentos");
			break;
		case 5:
			System.out.println("MenuOptConta()");
			break;
		default:
			System.out.println("Opção inválida!");
		case 6:
			Application.MenuPrincipal();
			break;

		}
	} // Fim do método: MenuOptCliente()

	
	public static int DisplayMenu(String menuTitle, String[] opcoesMenu) {
		Scanner userInput = new Scanner(System.in);
		int readOption;
		
		System.out.println("** Sistema de Gestão Bancária **");
		System.out.println(menuTitle);
		System.out.println("Opções:                         ");
		for(int i = 0; i < opcoesMenu.length; i++) {
			System.out.println("       "+i+"."+opcoesMenu[i]);
		}

		System.out.println();
		System.out.print("Escolha uma opção:");
		do {
			readOption = userInput.nextInt();			
		} while (readOption >= opcoesMenu.length);
		
		return readOption;
	}


	/*
	 * Insere uma nova agencia
	 */
	public static void insereAgencia() {
		int agenciaID;
		String nome;
		String morada;
		String telefone;
		int ultimoClienteID = 0;

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("\nINSERIR NOVA AGÊNCIA");
		System.out.println("----------------------");
		
		System.out.println("Digite o numero da agência:");
		agenciaID = Integer.parseInt(userInput.next());
		
		System.out.println("Digite o nome da agência:");
		nome = userInput.next();

		System.out.println("Digite a morada da agência:");
		morada = userInput.next();

		System.out.println("Digite o telefone da agência:");
		telefone = userInput.next();

		Agencia agencia = new Agencia(agenciaID, nome, morada, telefone, ultimoClienteID);
		
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agenciaDao.insereAgencia(agencia);
	}
	
	/*
	 * Lista todas as agencias
	 */
	public static void listarAgencias() {
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		List<Agencia> agencias = agenciaDao.listarAgencias();
				
		System.out.println("Lista de todas as agencias:");
		System.out.println("---------------------------");
		
		for( Agencia agencia : agencias) {
			System.out.println(agencia.toString());
		}
		System.out.println("Fim da lista.");
	}
}