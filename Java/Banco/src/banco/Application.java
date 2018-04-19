package banco;

import java.util.List;
import java.util.Scanner;

import logica.Agencia;
import logica.Cliente;
import persistencia.AgenciaDAO;

public class Application {

	public static void main(String[] args) {
		menuPrincipal();
	}

	
	public static void menuPrincipal() {
		int selection;
		String menuTitle = "======== MENU PRINCIPAL ========";
		String[] opcoesMenu = {"Criar um cliente", "Listar os clientes", "Opções do cliente", 
				"Criar uma agência", "Listar as agências", "Opções da agência", "Registo de operações", "Sair" };

		do {
			selection = displayMenu(menuTitle, opcoesMenu);		

			switch (selection) {
			case 0:
				insereCliente();
				break;
			case 1:
				System.out.println("Listar os clientes: ainda não implementado");
				break;
			case 2:
				menuOptCliente();
				break;
			case 3:			
				insereAgencia();
				break;
			case 4:
				listarAgencias();
				break;
			case 5:
				menuOptAgencia();
				break;
			case 6:
				menuRegistoOperacoes();
				break;
			case 7:
				System.out.println("Bye.");
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}
		} while (selection != 7);

	} // Fim do método: MenuPrincipal()
	

	
	public static void menuOptCliente() {
		int selection;
		String menuTitle = "====== MENU OPÇÕES CLIENTE =====";
		String[] opcoesMenu = {"Editar os dados do cliente", "Apagar um cliente", "Criar conta", "Listar contas do cliente", 
				"Alterar dados da conta", "Apagar conta", "Criar cartões", "Listar cartoes do cliente", "Alterar dados do cartão",
				"Apagar cartão", "Menu anterior" };
		selection = displayMenu(menuTitle, opcoesMenu);

		switch (selection) {
		case 0:
			System.out.println("updateCliente()");
			break;
		case 1:
			System.out.println("deleteCliente()");
			break;
		case 2:
			System.out.println("insereConta()");
			break;
		case 3:
			System.out.println("listaContasCliente()");
			break;
		case 4:
			System.out.println("updateConta()");
			break;
		case 5:
			System.out.println("deleteConta()");
			break;
		case 6:
			System.out.println("insereCartao()");
			break;
		case 7:
			System.out.println("listaCartoesCliente()");
			break;
		case 8:
			System.out.println("updateCartao()");
			break;
		case 9:
			System.out.println("deleteCartao()");
			break;
		default:
			System.out.println("Opção inválida!");
		case 10:
			break;
		}
	} // Fim do método: MenuOptCliente()

	
	public static void menuOptAgencia() {
		int selection;
		String menuTitle = "====== MENU OPÇÕES AGÊNCIA =====";
		String[] opcoesMenu = {"Editar os dados da agência", "Apagar uma agência", 
				"Listar clientes da agência",  "Menu anterior" };
		selection = displayMenu(menuTitle, opcoesMenu);

		switch (selection) {
		case 0:
			updateAgencia();
			break;
		case 1:
			deleteAgencia();
			break;
		case 2:
			consultaClientesAgencia();
			break;
		default:
			System.out.println("Opção inválida!");
		case 3:
			break;
		}
	} // Fim do método: MenuOptAgencia()

	/*
	 * MENU Registo de operações
	 */
	public static void menuRegistoOperacoes() {
		int selection;
		String menuTitle = "====== MENU REGISTO DE OPERAÇÕES =====";
		String[] opcoesMenu = {"Listar movimentos da conta", "Nova transferência", 
				"Novo levantamento", "Novo depósito", "Avançar um período", "Menu anterior" };
		selection = displayMenu(menuTitle, opcoesMenu);

		switch (selection) {
		case 0:
			System.out.println("consultaMovimentosConta()");
			break;
		case 1:
			System.out.println("criaTransferencia()");
			break;
		case 2:
			System.out.println("criaLevantamento()");
			break;
		case 3:
			System.out.println("criaDeposito()");
			break;
		case 4:
			System.out.println("avancaPeriodo()");
			break;
		default:
			System.out.println("Opção inválida!");
		case 5:
			break;
		}
	}
	
	
	public static int displayMenu(String menuTitle, String[] opcoesMenu) {
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
		int ultimaConta = 0;
		
		System.out.println("\n[3.Criar uma agência]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("--------------------");
		System.out.println("INSERIR NOVA AGÊNCIA");
		System.out.println("--------------------");
		
		System.out.println("Digite o numero da agência:");
		agenciaID = Integer.parseInt(userInput.next());
		
		System.out.println("Digite o nome da agência:");
		nome = userInput.next();

		System.out.println("Digite a morada da agência:");
		morada = userInput.next();

		System.out.println("Digite o telefone da agência:");
		telefone = userInput.next();

		Agencia agencia = new Agencia(agenciaID, nome, morada, telefone, ultimaConta);
		
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agenciaDao.insereAgencia(agencia);
	}
	
	/*
	 * Lista todas as agencias
	 */
	public static void listarAgencias() {
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		List<Agencia> agencias = agenciaDao.listarAgencias();
				
		System.out.println("\n[4.Listar as agências]");
		System.out.println("Resultados encontrados:");
		
		for( Agencia agencia : agencias) {
			displayAgencia(agencia);
			System.out.println("--------------------------------------------------");
		}
		System.out.println("Fim da lista.\n");
	}
	
	/*
	 * Editar os dados da agencia
	 */
	public static void updateAgencia() {
		int agenciaID;
		String nome, morada, telefone, dadosOkay;
		
		System.out.println("\n[5-0.Editar os dados da agencia]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência:");
		agenciaID = Integer.parseInt(userInput.next());

		Agencia agencia;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agencia = agenciaDao.consultaAgencia(agenciaID);
		
		if (agencia == null) {
			System.out.println("Agência não encontrada!");
			return;
		}
		
		do {
			nome = agencia.getNome();
			System.out.println("Digite o novo nome da agência ["+nome+"]:");
			nome = userInput.next();

			morada = agencia.getMorada();
			System.out.println("Digite a nova morada da agência ["+morada+"]:");
			morada = userInput.next();

			telefone = agencia.getTelefone();
			System.out.println("Digite o novo telefone da agência ["+telefone+"]:");
			telefone = userInput.next();

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar.");
			dadosOkay = userInput.next();
		} while (!"S".equalsIgnoreCase(dadosOkay));

		agencia.setNome(nome);
		agencia.setMorada(morada);
		agencia.setTelefone(telefone);
		
		agenciaDao.alteraAgencia(agencia);
	}

	/*
	 * Apagar uma agencia
	 */
	public static void deleteAgencia() {
		int agenciaID;
		String confirmDelete;
		
		System.out.println("\n[5-1.Apagar uma agência]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência a eliminar:");
		agenciaID = Integer.parseInt(userInput.next());

		Agencia agencia;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agencia = agenciaDao.consultaAgencia(agenciaID);
		
		if (agencia == null) {
			System.out.println("Agência não encontrada!");
			return;
		}
		
		displayAgencia(agencia);

		System.out.println("Inserir \"s\" ou \"S\" para confirmar a eliminação.");
		confirmDelete = userInput.next();

		if ("S".equalsIgnoreCase(confirmDelete)) {
			agenciaDao.apagaAgencia(agenciaID);
		}
	}

	/*
	 * consulta de todos os clientes de uma agencia
	 */
	public static void consultaClientesAgencia() {
		int agenciaID;
		
		System.out.println("\n[5-2.Listar clientes da agência");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência:");
		agenciaID = Integer.parseInt(userInput.next());

		Agencia agencia;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agencia = agenciaDao.consultaAgencia(agenciaID);
		
		if (agencia == null) {
			System.out.println("Agência não encontrada!");
			return;
		}

		System.out.println("Lista de clientes da agência ["+agencia.getAgenciaID()+":"+agencia.getNome()+"]");

		System.out.println("Resultados encontrados:");
/*
 * implementar aqui o resto do codigo
 */
//		for( Cliente cliente : clientes) {
//			cliente.toString();
//			System.out.println("--------------------------------------------------");
//		}
		System.out.println("Fim da lista.\n");

	}
	
	/*
	 * display dos dados de uma agencia
	 */
	public static void displayAgencia(Agencia agencia) {
		System.out.println("      Id: " + agencia.getAgenciaID());
		System.out.println("    Nome: " + agencia.getNome());
		System.out.println("  Morada: " + agencia.getMorada());
		System.out.println("Telefone: " + agencia.getTelefone());
		System.out.println("UltConta: " + agencia.getUltimaConta());
	}
	
	
	
	

	/*
	 * Insere um novo cliente
	 */
	public static void insereCliente() {
		int agenciaID, numeroCliente;
		char tipo;
		String nome, morada, telefone, email, profissao, cartaoCidadao;
		String dadosOkay;
		
		System.out.println("\n[0.Criar um cliente]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("--------------------");
		System.out.println("INSERIR NOVO CLIENTE");
		System.out.println("--------------------");
		
		// Vai ler a agencia
		Agencia agencia = null;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		
		do {
			System.out.println("Digite o numero da agência (Digite 0 para cancelar):");
			agenciaID = Integer.parseInt(userInput.next());

			if (agenciaID != 0) {
				agencia = agenciaDao.consultaAgencia(agenciaID);				
				if (agencia == null) {
					System.out.println("Agência não encontrada!");
				} else {
					System.out.println("Agência selecionada ["+agencia.getAgenciaID()+":"+agencia.getNome()+"]");
				}
			}
		} while (agencia == null | agenciaID == 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}

		do {
			System.out.println("Digite o numero de cliente:");
			numeroCliente = Integer.parseInt(userInput.next());

			do {
				System.out.println("Digite o tipo de cliente (\"N\"-Normal, \"V\"-VIP): ");
				tipo = userInput.next().toUpperCase().charAt(0);
			} while ( (tipo != 'N') & (tipo != 'V') );
			
			System.out.println("Digite o numero do cartão de cidadão do cliente:");
			cartaoCidadao = userInput.next();

			System.out.println("Digite o nome do cliente:");
			nome = userInput.next();

			System.out.println("Digite a morada do cliente:");
			morada = userInput.next();

			System.out.println("Digite o telefone do cliente:");
			telefone = userInput.next();

			System.out.println("Digite o email do cliente:");
			email = userInput.next();

			System.out.println("Digite a profissão do cliente:");
			profissao = userInput.next();

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
			dadosOkay = userInput.next();
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		/*
		Cliente cliente = new Cliente();
		
		ClienteDAO clienteDao  =  new ClienteDAO();
		clienteDao.insereCliente(cliente);
*/
		/*
		 * Criar uma conta à ordem
		 */
		// codigo aqui!!!
	}
}