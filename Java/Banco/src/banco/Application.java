package banco;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logica.Agencia;
import logica.Cartao;
import logica.CartaoCredito;
import logica.Cliente;
import logica.Conta;
import logica.ContaAPrazo;
import logica.ContaPoupanca;
import logica.Movimento;
import persistencia.AgenciaDAO;
import persistencia.CartaoDAO;
import persistencia.ClienteDAO;
import persistencia.ContaDAO;
import persistencia.MovimentoDAO;

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
				listarClientes();
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
		} while (selection != opcoesMenu.length - 1);

	} // Fim do método: MenuPrincipal()
	

	
	public static void menuOptCliente() {
		int selection;
		String menuTitle = "====== MENU OPÇÕES CLIENTE =====";
		String[] opcoesMenu = {"Editar os dados do cliente", "Apagar um cliente", "Criar conta", "Listar contas do cliente", 
				"Alterar dados da conta", "Apagar conta", "Criar cartões", "Listar cartoes do cliente", "Alterar dados do cartão",
				"Apagar cartão", "Menu anterior" };
		
		do {
			selection = displayMenu(menuTitle, opcoesMenu);

			switch (selection) {
			case 0:
				updateCliente();
				break;
			case 1:
				deleteCliente();
				break;
			case 2:
				insereConta();
				break;
			case 3:
				listaContasCliente();
				break;
			case 4:
				updateConta();
				break;
			case 5:
				deleteConta();
				break;
			case 6:
				insereCartao();
				break;
			case 7:
				listaCartoesCliente();
				break;
			case 8:
				updateCartao();
				break;
			case 9:
				deleteCartao();
				break;
			default:
				System.out.println("Opção inválida!");
			case 10:
				break;
			}
		} while (selection != opcoesMenu.length - 1);

	} // Fim do método: MenuOptCliente()

	
	public static void menuOptAgencia() {
		int selection;
		String menuTitle = "====== MENU OPÇÕES AGÊNCIA =====";
		String[] opcoesMenu = {"Editar os dados da agência", "Apagar uma agência", 
				"Listar clientes da agência",  "Menu anterior" };
		
		do {
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
		} while (selection != opcoesMenu.length - 1);
	} // Fim do método: MenuOptAgencia()

	/*
	 * MENU Registo de operações
	 */
	public static void menuRegistoOperacoes() {
		int selection;
		String menuTitle = "====== MENU REGISTO DE OPERAÇÕES =====";
		String[] opcoesMenu = {"Listar movimentos da conta", "Nova transferência", 
				"Novo levantamento", "Novo depósito", "Avançar um período", "Apagar um movimento", "Menu anterior" };
		
		do {
			selection = displayMenu(menuTitle, opcoesMenu);

			switch (selection) {
			case 0:
				listarMovimentosConta();
				break;
			case 1:
				criaTransferencia();
				break;
			case 2:
				criaLevantamento();
				break;
			case 3:
				criaDeposito();
				break;
			case 4:
				avancaPeriodo();
				break;
			case 5:
				deleteMovimento();
				break;
			default:
				System.out.println("Opção inválida!");
			case 6:
				break;
			}
		} while (selection != opcoesMenu.length - 1);
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
		
		do {
			System.out.println("Digite o numero da agência (Digite 0 para cancelar):");
			agenciaID = parseInteger(userInput.nextLine());
		} while (agenciaID < 0);

		if (agenciaID == 0) {
			return;
		}
		
		System.out.println("Digite o nome da agência:");
		nome = userInput.nextLine();

		System.out.println("Digite a morada da agência:");
		morada = userInput.nextLine();

		System.out.println("Digite o telefone da agência:");
		telefone = userInput.nextLine();

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

		System.out.println("Fim da lista. Prima Enter para continuar...");
		Scanner userInput = new Scanner(System.in);
		userInput.nextLine();
		System.out.println();
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
		agenciaID = parseInteger(userInput.nextLine());

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
			nome = userInput.nextLine();

			morada = agencia.getMorada();
			System.out.println("Digite a nova morada da agência ["+morada+"]:");
			morada = userInput.nextLine();

			telefone = agencia.getTelefone();
			System.out.println("Digite o novo telefone da agência ["+telefone+"]:");
			telefone = userInput.nextLine();

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar.");
			dadosOkay = userInput.nextLine();
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
		
		do {
			System.out.println("Digite o numero da agência a eliminar (Digite 0 para cancelar):");
			agenciaID = parseInteger(userInput.nextLine());
		} while (agenciaID < 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}		

		Agencia agencia;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agencia = agenciaDao.consultaAgencia(agenciaID);
		
		if (agencia == null) {
			System.out.println("Agência não encontrada!");
			return;
		}
		
		displayAgencia(agencia);

		System.out.println("Inserir \"s\" ou \"S\" para confirmar a eliminação.");
		confirmDelete = userInput.nextLine();

		if ("S".equalsIgnoreCase(confirmDelete)) {
			agenciaDao.apagaAgencia(agenciaID);
		}
		
		System.out.println();
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
	 * consulta de todos os clientes de uma agencia
	 */
	public static void consultaClientesAgencia() {
		int agenciaID;
		
		System.out.println("\n[5-2.Listar clientes da agência");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência:");
		agenciaID = parseInteger(userInput.nextLine());

		Agencia agencia;
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		agencia = agenciaDao.consultaAgencia(agenciaID);
		
		if (agencia == null) {
			System.out.println("Agência não encontrada!");
			return;
		}

		System.out.println("Lista de clientes da agência ["+agencia.getAgenciaID()+":"+agencia.getNome()+"]");

		System.out.println("Resultados encontrados:");
		
		ClienteDAO clienteDao  =  new ClienteDAO();
		List<Cliente> clientes = clienteDao.listarClientes(agencia.getAgenciaID());

		for( Cliente cliente : clientes) {
			displayCliente(cliente);
			System.out.println("--------------------------------------------------");
		}

		System.out.println("Fim da lista. Prima Enter para continuar...");
		userInput.nextLine();
		System.out.println();

	}
	
	// display cliente com indicação da agencia
	public static void displayClienteFull(Cliente cliente) {
		System.out.println("  Agencia: " + cliente.getAgencia().getAgenciaID() + " (" +
							cliente.getAgencia().getNome() + ")");
		displayCliente(cliente);
	}
	 // display dos dados de um cliente sem agencia
	public static void displayCliente(Cliente cliente) {
		System.out.println("NºCliente: " + cliente.getNumeroCliente());
		String tipoDescr = cliente.getTipo() == 'N' ? "Normal" : "VIP";
		System.out.println("     Tipo: " + tipoDescr);
		System.out.println("     Nome: " + cliente.getNome());
		System.out.println("C.Cidadão: " + cliente.getCartaoCidadao());
		System.out.println("   Morada: " + cliente.getMorada());
		System.out.println(" Telefone: " + cliente.getTelefone());
		System.out.println("    Email: " + cliente.getEmail());
		System.out.println("NascidoEm: " + cliente.getDataNascimento());
		System.out.println("Profissão: " + cliente.getProfissao());
		System.out.println("DtCriacao: " + cliente.getDataCriacao());
	}

	/*
	 * Insere um novo cliente
	 */
	public static void insereCliente() {
		int agenciaID, numeroCliente;
		char tipo;
		String nome, morada, telefone, email, profissao, cartaoCidadao, dataCriacao, dataNascimento;
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
			agenciaID = parseInteger(userInput.nextLine());

			if (agenciaID != 0) {
				agencia = agenciaDao.consultaAgencia(agenciaID);				
				if (agencia == null) {
					System.out.println("Agência não encontrada!");
				} else {
					System.out.println("Agência selecionada ["+agencia.getAgenciaID()+":"+agencia.getNome()+"]");
				}
			}
		} while (agencia == null & agenciaID != 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}

		do {
			do {
				System.out.println("Digite o numero de cliente:");
				numeroCliente = parseInteger(userInput.nextLine());
			}while (numeroCliente <= 0);

			do {
				System.out.println("Digite o tipo de cliente (\"N\"-Normal, \"V\"-VIP): ");
				tipo = userInput.nextLine().toUpperCase().charAt(0);
				//ainda não é possivel ter clientes VIP
				if (tipo == 'V') {
					System.out.println("A opção de cliente VIP não está disponivel! Vou continuar como Normal");
					tipo = 'N';
				}
			} while ( (tipo != 'N') & (tipo != 'V') );
			
			System.out.println("Digite o nome do cliente:");
			nome = userInput.nextLine();

			System.out.println("Digite o numero do cartão de cidadão do cliente:");
			cartaoCidadao = userInput.nextLine();

			System.out.println("Digite a data de nascimento do cliente (yyyy-mm-dd):");
			dataNascimento = userInput.nextLine();

			System.out.println("Digite a morada do cliente:");
			morada = userInput.nextLine();

			System.out.println("Digite o telefone do cliente:");
			telefone = userInput.nextLine();

			System.out.println("Digite o email do cliente:");
			email = userInput.nextLine();

			System.out.println("Digite a profissão do cliente:");
			profissao = userInput.nextLine();

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
			dadosOkay = userInput.nextLine();
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		LocalDate myDate = LocalDate.now();
		dataCriacao = myDate.toString();
		Cliente cliente = new Cliente(numeroCliente, agencia, tipo, nome, cartaoCidadao, morada, telefone, email, dataCriacao, dataNascimento, profissao);
		
		ClienteDAO clienteDao  =  new ClienteDAO();
		clienteDao.insereCliente(cliente);

		/*
		 * automaticamente vai atribuir uma conta à ordem ao cliente criado
		 */
		criaContaOrdem(cliente, "À Ordem Base", true);
	
	}
	
	/*
	 * 2-00.Editar os dados do cliente
	 */
	public static void updateCliente() {
		int agenciaID, numeroCliente;
		char tipo;
		String nome, morada, telefone, email, profissao, cartaoCidadao, dataCriacao, dataNascimento;
		String dadosOkay;
		
		System.out.println("\n[2-00.Editar os dados do cliente]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("-----------------------");
		System.out.println("EDITAR DADOS DO CLIENTE");
		System.out.println("-----------------------");

		Cliente cliente = null;
		ClienteDAO clienteDao  =  new ClienteDAO();

		do {
			System.out.println("Digite o numero da agência (Digite 0 para cancelar):");
			agenciaID = Integer.parseInt(userInput.nextLine());

			if (agenciaID != 0) {
				System.out.println("Digite o numero de cliente:");
				numeroCliente = Integer.parseInt(userInput.nextLine());

				// Vai ler o cliente
				cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);				
				if (cliente == null) {
					System.out.println("Cliente não encontrado!");
				}
			}
		} while (cliente == null & agenciaID != 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		dataCriacao = cliente.getDataCriacao();
		System.out.println("Cliente criado a "+dataCriacao);
		
		do {
			nome = cliente.getNome();
			tipo = cliente.getTipo();
			morada = cliente.getMorada();
			cartaoCidadao = cliente.getCartaoCidadao();
			telefone = cliente.getTelefone();
			email = cliente.getEmail();
			dataNascimento = cliente.getDataNascimento();
			profissao = cliente.getProfissao();

			do {
				System.out.println("Digite o tipo de cliente (\"N\"-Normal, \"V\"-VIP) ["+tipo+"]: ");
				tipo = userInput.nextLine().toUpperCase().charAt(0);
				//ainda não é possivel ter clientes VIP
				if (tipo == 'V') {
					System.out.println("A opção de cliente VIP não está disponivel! Vou continuar como Normal");
					tipo = 'N';
				}
			} while ( (tipo != 'N') & (tipo != 'V') );

			System.out.println("Digite o novo nome do cliente ["+nome+"]:");
			nome = userInput.nextLine();

			System.out.println("Digite o novo numero do cartão de cidadão do cliente ["+cartaoCidadao+"]:");
			cartaoCidadao = userInput.nextLine();

			System.out.println("Digite a nova data de nascimento do cliente ["+dataNascimento+"]:");
			dataNascimento = userInput.nextLine();

			System.out.println("Digite a nova morada do cliente ["+morada+"]:");
			morada = userInput.nextLine();

			System.out.println("Digite o novo telefone do cliente ["+telefone+"]:");
			telefone = userInput.nextLine();

			System.out.println("Digite o novo email do cliente ["+email+"]:");
			email = userInput.nextLine();

			System.out.println("Digite a nova profissão do cliente ["+profissao+"]:");
			profissao = userInput.nextLine();

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
			dadosOkay = userInput.nextLine();
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		cliente.setTipo(tipo);
		cliente.setNome(nome);
		cliente.setCartaoCidadao(cartaoCidadao);
		cliente.setMorada(morada);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
		cliente.setProfissao(profissao);
		cliente.setDataNascimento(dataNascimento);
		
		clienteDao.alteraCliente(cliente);

	}
	
	/*
	 * 2-01.Apagar um cliente
	 */
	public static void deleteCliente() {
		int agenciaID, numeroCliente=0;
		String confirmDelete;
		
		System.out.println("\n[2-01.Apagar um cliente]");
		
		Scanner userInput = new Scanner(System.in);
		
		Cliente cliente = null;
		ClienteDAO clienteDao  =  new ClienteDAO();

		System.out.println("-------------------------------------------------------");
		System.out.println("              DADOS DO CLIENTE A ELIMINAR              ");
		System.out.println("-------------------------------------------------------");
		do {
			do {
				System.out.println("Digite o numero da agência (Digite 0 para cancelar):");
				agenciaID = parseInteger(userInput.nextLine());
			} while (agenciaID < 0);

			if (agenciaID != 0) {
				do {
					System.out.println("Digite o numero de cliente (Digite 0 para cancelar):");
					numeroCliente = parseInteger(userInput.nextLine());
				} while (numeroCliente < 0);

				if (numeroCliente != 0) {
					// Vai ler o cliente
					cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);
					if (cliente == null) {
						System.out.println("Cliente não encontrado!");
					}
				} else {
					agenciaID = 0;
				}
			}
		} while (cliente == null & agenciaID != 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
				
		displayClienteFull(cliente);

		System.out.println("Inserir \"s\" ou \"S\" para confirmar a eliminação.");
		confirmDelete = userInput.nextLine();

		if ("S".equalsIgnoreCase(confirmDelete)) {
			clienteDao.apagaCliente(agenciaID, numeroCliente);
		}
		
		System.out.println();
	}
	
	/*
	 * CRIA CONTA À ORDEM (criada automáticamente a quando da criacao de um cliente)
	 */
	public static void criaContaOrdem(Cliente cliente, String descricao, boolean criarCartao) {
		// vai à tabela de agencias buscar a ultimaconta e atualiza-a
		
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		int ultimaConta;
		
		cliente.setAgencia(agenciaDao.consultaAgencia(cliente.getAgencia().getAgenciaID()));
		ultimaConta = cliente.getAgencia().incrementaUltimaConta();
		agenciaDao.atualizaUltimaConta(cliente.getAgencia().getAgenciaID(), ultimaConta);

		/*
		 * Criar uma conta à ordem
		 */
		LocalDate dataHoje = LocalDate.now();
		Conta conta = new Conta(ultimaConta, cliente, "ORDEM", descricao, dataHoje.toString(), 0.0, 0);

		ContaDAO contaDao = new ContaDAO();
		contaDao.insereConta(conta);
		
		System.out.println(conta.toString());

		/*
		 * deve atribuir automáticamente tambem um cartao de débito (se vem da criacao de cliente)
		 */
		if (criarCartao) {
			criaCartaoDebito(conta);
		}
	}

	/*
	 * 2-02.Cria conta (poupança, prazo ou investimento)
	 */
	public static void insereConta() {
		int agenciaID = 0, numeroCliente = 0, numeroConta = 0;
		String selTipo, descricao, dataCriacao;
		String tipo ="";
		double saldo, valorInicial = 0.0;
		int prazoAnos = 0, ultimoMovimento = 0;
		String dadosOkay;
		
		System.out.println("\n[2-02.Criar uma conta]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("--------------------");
		System.out.println("CRIAR UMA NOVA CONTA");
		System.out.println("--------------------");

		// *** Selecionar o cliente da nova conta
		ClienteDAO clienteDao  =  new ClienteDAO();
		Cliente cliente = null;
		
		do {
			do {
				System.out.println("Digite o numero da agência (Digite 0 para cancelar, -1 p/Ver lista cliente da agencia):");
				agenciaID = parseInteger(userInput.nextLine());
				if (agenciaID == -1) {
					consultaClientesAgencia();
				}

			} while (agenciaID < 0);

			if (agenciaID != 0) {
				do {
					System.out.println("Digite o numero de cliente (Digite 0 para cancelar):");
					numeroCliente = parseInteger(userInput.nextLine());
				} while (numeroCliente < 0);

				if (numeroCliente != 0) {
					// Vai ler o cliente
					cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);
					if (cliente == null) {
						System.out.println("Cliente não encontrado!");
					}
				} else {
					agenciaID = 0;
				}
			}
		} while (cliente == null & agenciaID != 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		
		System.out.println("--------------------------------------------------");
		System.out.println("Cliente selecionado:");
		displayClienteFull(cliente);
		
		ContaDAO contaDao = new ContaDAO();
		Conta conta = null;
		ContaPoupanca contaPoupanca = null;
		ContaAPrazo contaAPrazo = null;
		
		// Obriga a que cada cliente tenha uma conta à ordem, antes de qualquer outra conta
		// por isso vai ler se existe uma.
		conta = contaDao.consultaConta(agenciaID, numeroCliente, "ORDEM");
		
		if (conta != null) {
			NumberFormat formata = NumberFormat.getCurrencyInstance();
			String currency = formata.format(conta.getSaldo());
			System.out.println("Saldo disponível da conta à ordem: "+ currency);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Preenchimento dos dados da conta:");
		
		do {
			System.out.println("Selecione o tipo de conta (0-\"À ordem\", 1-\"Poupança\", 2-\"A prazo\"): ");
			selTipo = userInput.nextLine();
		} while (!selTipo.matches("0|1|2"));

		switch (selTipo) {
		case "0":
			tipo = "ORDEM";
			break;
		case "1":
			tipo = "POUPANCA";
			break;
		case "2":
			tipo = "APRAZO";
			break;
		}

		if ("ORDEM".equals(tipo) && conta != null) {
			/* Se quer criar uma conta do tipo = "ORDEM", verifica se ja tem uma, pois
			 * cada cliente só pode ter uma conta à ordem.
			 */
				System.out.println("Para o cliente selecionado, já existe uma conta à ordem! Vou terminar.");
				userInput.nextLine();
				return;
		} else if ("APRAZO".equals(tipo) && conta.getSaldo() <= 0) {
			// Para criar uma conta a prazo, tem que ter obrigatóriament saldo positivo
			// na conta à ordem, para poder transferir o valor inicial da conta a prazo.
			System.out.println("Primeiro deposite fundos na sua conta à ordem para poder criar uma conta a prazo!\nVou terminar!");
			userInput.nextLine();
			return;
		}

		System.out.println("Digite uma descrição para esta conta: ");
		descricao = userInput.nextLine();

		if ("APRAZO".equals(tipo)) {
			do {
				System.out.println("Selecione o prazo em anos desta conta (Mimino 1 e máximo 5 anos): ");
				prazoAnos = parseInteger(userInput.nextLine());
			} while ((prazoAnos <= 0) || (prazoAnos > 5));

			do {
				System.out.println("Digite o montante de constituição da conta: ");
				valorInicial = parseDouble(userInput.nextLine());
				if(valorInicial > conta.getSaldo()) {
					System.out.println("A sua conta à ordem não tem saldo suficiente! (Saldo disponível:"+conta.getSaldo()+")");
				}
			} while ((valorInicial <= 0.0)|| (valorInicial > conta.getSaldo()));
		}

		System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar a criação da conta.");
		dadosOkay = userInput.nextLine();
		if (!"S".equalsIgnoreCase(dadosOkay)) {
			System.out.println("\nOperação interrompida pelo utilizador!\n");
			return;
		}
	
		ultimoMovimento = 0;
		saldo = 0.0;
		LocalDate dataHoje = LocalDate.now();
		dataCriacao = dataHoje.toString();

		if ("ORDEM".equals(tipo)) {
			criaContaOrdem(cliente, descricao, false);
			System.out.println("AVISO IMPORTANTE: Não se esqueça de associar um cartão de débito a esta conta!");
			userInput.nextLine();
			return;
		}
				
		// vai à tabela de agencias buscar o numero da ultimaconta e atualiza-o		
		AgenciaDAO agenciaDao  =  new AgenciaDAO();
		int ultimaConta;
		
		cliente.setAgencia(agenciaDao.consultaAgencia(cliente.getAgencia().getAgenciaID()));
		ultimaConta = cliente.getAgencia().incrementaUltimaConta();
		agenciaDao.atualizaUltimaConta(cliente.getAgencia().getAgenciaID(), ultimaConta);
		
		numeroConta = ultimaConta;

		if ("POUPANCA".equals(tipo)) {
			contaPoupanca = new ContaPoupanca(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento);
			contaDao.insereConta(contaPoupanca);
			System.out.println(contaPoupanca.toString());
		} else if ("APRAZO".equals(tipo)) {
			// Para o Deposito inicial precisa de ter um cartao associado ao movimento
			Cartao cartao = null;
			do {
				cartao = selecionaCartao();
				if (cartao == null) {
					System.out.println("Nenhum cartão selecionado! Operação interrompida: nenhuma conta criada!");
					return;
				}
			    if (cartao.getConta().getCliente().getAgencia().getAgenciaID() != cliente.getAgencia().getAgenciaID() ||
					cartao.getConta().getCliente().getNumeroCliente() != cliente.getNumeroCliente()) {
			    	System.out.println("O cartão selecionado não pertence ao cliente atual!");
			    }
			} while (cartao.getConta().getCliente().getAgencia().getAgenciaID() != cliente.getAgencia().getAgenciaID() ||
					cartao.getConta().getCliente().getNumeroCliente() != cliente.getNumeroCliente());
				
			
			contaAPrazo = new ContaAPrazo(numeroConta, cliente, tipo, descricao, dataCriacao, saldo, ultimoMovimento, prazoAnos);
			contaDao.insereConta(contaAPrazo);
			System.out.println(contaAPrazo.toString());
			/*
			 * FAZ AQUI O DEPOSITO INICIAL (TRANSFERENCIA DA CONTA ORDEM PARA ESTA CONTA A PRAZO)
			 */
			criaTransferencia(cartao, contaAPrazo, "TRFOUT", valorInicial, "ABERTURA");
		}

	}

	/*
	 * 2-04.Alterar dados da conta
	 */
	public static void updateConta() {
		System.out.println("\n[2-04.Alterar dados da conta] : opção ainda não implementada...\n\n");
	}
	
	/*
	 * 2-05.Apagar conta
	 */
	public static void deleteConta() {
//		System.out.println("\n[2-05.Apagar conta] : opção ainda não implementada...\n\n");
		int agenciaID, numeroConta=0;
		String confirmDelete;
		
		System.out.println("\n[2-05.Apagar conta]");
		
		Scanner userInput = new Scanner(System.in);
		
		Conta conta = null;
		ContaDAO contaDao  =  new ContaDAO();

		System.out.println("-------------------------------------------------------");
		System.out.println("               DADOS DO CONTA A ELIMINAR               ");
		System.out.println("-------------------------------------------------------");
		do {
			System.out.println("Digite o numero da agência  (Digite 0 para cancelar):");
			agenciaID = Integer.parseInt(userInput.nextLine());

			if (agenciaID != 0) {
				System.out.println("Digite o numero da conta:");
				numeroConta = Integer.parseInt(userInput.nextLine());

				// Vai ler a conta
				conta = contaDao.consultaConta(agenciaID, numeroConta);				
				if (conta == null) {
					System.out.println("Conta não encontrada!");
				}
			}
		} while (conta == null & agenciaID != 0);

		if (agenciaID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
				
		displayConta(conta);

		System.out.println("Inserir \"s\" ou \"S\" para confirmar a eliminação.");
		confirmDelete = userInput.nextLine();

		if ("S".equalsIgnoreCase(confirmDelete)) {
			contaDao.apagaConta(agenciaID, numeroConta);
		}
		
		System.out.println();
	}

	 // display dos dados de uma conta
	public static void displayConta(Conta conta) {
		System.out.println("Nº da CONTA: " + conta.getNumeroConta());
		System.out.println("    Agencia: " + conta.getCliente().getAgencia().getAgenciaID() + " (" +
				conta.getCliente().getAgencia().getNome() + ")");
		System.out.println("    Cliente: " + conta.getCliente().getNumeroCliente() + " (" + 
				conta.getCliente().getNome() + ")");
		System.out.println("       Tipo: " + conta.getTipo());
		System.out.println("  Descrição: " + conta.getDescricao());
		System.out.println("DataCriacao: " + conta.getDataCriacao());
		
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		String currency = formata.format(conta.getSaldo());
		System.out.println("\n    SALDO: " + currency + "\n");
	}
	
	public static void displayConta(ContaPoupanca contaPoupanca) {
		displayConta((Conta) contaPoupanca);
		
		NumberFormat formata = NumberFormat.getPercentInstance();
		String taxa = formata.format(contaPoupanca.getTaxaRemuneracao());
		System.out.println("Remuneração: " + taxa);
		System.out.println("Pagam.Juros: " + contaPoupanca.getPeriocidadeJuros() + " dias.");
	}
	
	
	public static void displayConta(ContaAPrazo contaAPrazo) {
		displayConta((ContaPoupanca) contaAPrazo);
		
		System.out.println("      Prazo: " + contaAPrazo.getPrazoAnos() + " ano(s).");
	}

	
	/*
	 * Lista todos os clientes
	 */
	public static void listarClientes() {
		ClienteDAO clienteDao  =  new ClienteDAO();
		List<Cliente> clientes = clienteDao.listarClientes();
				
		System.out.println("\n[1.Listar os clientes]");
		System.out.println("Resultados encontrados:");
		
		for( Cliente cliente : clientes) {
			displayClienteFull(cliente);
			System.out.println("--------------------------------------------------");
		}
		System.out.println("Fim da lista. Prima Enter para continuar...");
		Scanner userInput = new Scanner(System.in);
		userInput.nextLine();
		System.out.println();
	}
	
	/*
	 * Listar contas do cliente
	 */
	public static void listaContasCliente() {
		int agenciaID, numeroCliente;
		
		System.out.println("\n[2-3.Listar contas do cliente]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência:");
		agenciaID = Integer.parseInt(userInput.nextLine());

		System.out.println("Digite o numero de cliente:");
		numeroCliente = Integer.parseInt(userInput.nextLine());

		ClienteDAO clienteDao  =  new ClienteDAO();
		Cliente cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);
		
		if (cliente == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}

		System.out.println("----------------------------------------------------------------------");
		System.out.println("Lista de contas do cliente No.\t" + cliente.getNumeroCliente()+"-"+cliente.getNome());
		System.out.println("\t\t\tAgencia "+cliente.getAgencia().getAgenciaID() + "-" + cliente.getAgencia().getNome()+".");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Resultados encontrados:");
		System.out.println("NºCONTA\tTIPO    \tDESCRIÇÃO\t\t\tSALDO");
		
		ContaDAO contaDao = new ContaDAO();
		List<Conta> contas = contaDao.listarContas(agenciaID, numeroCliente);

		for( Conta conta: contas) {
			System.out.println(conta.getNumeroConta()+"\t"+conta.getTipo()+"\t"+conta.getDescricao()+"\t\t\t"+conta.getSaldo());
//			System.out.println("----------------------------------------------------------------------");
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Fim da lista. Prima Enter para continuar...");
		userInput.nextLine();
		System.out.println();

	}

	
	/*
	 * Listar cartoes do cliente
	 */
	public static void listaCartoesCliente() {
		int agenciaID, numeroCliente;
		
		System.out.println("\n[2-7.Listar cartões do cliente]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Digite o numero da agência:");
		agenciaID = parseInteger(userInput.nextLine());

		System.out.println("Digite o numero de cliente:");
		numeroCliente = parseInteger(userInput.nextLine());

		ClienteDAO clienteDao  =  new ClienteDAO();
		Cliente cliente = clienteDao.consultaCliente(agenciaID, numeroCliente);
		
		if (cliente == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}

		// Vai buscar todas as contas do cliente selecionado
		ContaDAO contaDao = new ContaDAO();
		List<Conta> contas = contaDao.listarContas(agenciaID, numeroCliente);
		
		//vai obter uma lista de todos os cartoes das contas do cliente
		CartaoDAO cartaoDao = new CartaoDAO();
		List<Cartao> cartoes = new ArrayList<>();
		for (Conta conta : contas) {
			cartoes.addAll(cartaoDao.listaCartoes(conta.getCliente().getAgencia().getAgenciaID(), conta.getNumeroConta()));
		}
			
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Lista de cartoes do cliente No.\t" + cliente.getNumeroCliente()+"-"+cliente.getNome());
		System.out.println("\t\t\tAgencia "+cliente.getAgencia().getAgenciaID() + "-" + cliente.getAgencia().getNome()+".");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Resultados encontrados:");
		System.out.println("N.CARTAO TIPO\tDESCRIÇÃO\t\tVALIDADE");
		

		for(Cartao cartao: cartoes) {
			System.out.println(cartao.getCartaoID()+"\t "+cartao.getTipo()+"\t"+cartao.getDescricao()+"\t\t"+cartao.getDataValidade());
//			System.out.println("----------------------------------------------------------------------");
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Fim da lista. Prima Enter para continuar...");
		userInput.nextLine();
		System.out.println();

	}


	/*
	 * CRIA UM CARTAO DE DEBITO (Cria automáticamente a quando da criacao de um cliente / Conta à ordem)
	 */
	public static void criaCartaoDebito(Conta conta) {
		char tipo = 'D';
		LocalDate dataHoje = LocalDate.now();
		LocalDate dataValidadeTemp = dataHoje.plusYears(2);
		String dataValidade = String.valueOf(dataValidadeTemp.getYear() + "-" + dataValidadeTemp.getMonthValue() + "-"+ dataValidadeTemp.lengthOfMonth());
		
		String[] nomes = conta.getCliente().getNome().split(" ");
		String nomeNoCartao;
		if (nomes.length>1) {
			nomeNoCartao = nomes[0] + " " + nomes[nomes.length - 1];
		} else {
			nomeNoCartao = nomes[0];
		}
		
		Cartao cartao = new Cartao(0, "",nomeNoCartao, dataHoje.toString(), dataValidade, tipo, conta);	

		CartaoDAO cartaoDao = new CartaoDAO();
		int lastId = cartaoDao.insereCartao(cartao);
        
		cartao.setCartaoID(lastId);

		System.out.println(cartao.toString());
	}


	/*
	 * 2-06.Cria cartao 
	 */
	public static void insereCartao() {
		int agenciaID, numeroConta;
		char tipo;
		String selTipo;
		String descricao;
		String nomeNoCartao;
		double plafondMensal = 0.0;
		double plafondDisponivel = 0.0;
		int diaInicioExtrato = 0;
		int condicoesPagamento = 0;
		String dadosOkay;
		
		System.out.println("\n[2-06.Criar cartao]");
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("-------------------");
		System.out.println("INSERIR NOVO CARTÃO");
		System.out.println("-------------------");

		ContaDAO contaDao  =  new ContaDAO();
		Conta conta = null;
		
		do {
			do {
				do {
					System.out.println("Digite o numero da agência (Digite 0 para Cancelar):");
					agenciaID = parseInteger(userInput.nextLine());
				} while (agenciaID < 0);

				if (agenciaID == 0) {
					System.out.println("Operação cancelada pelo utilizador.");
					return;
				}

				do {
					System.out.println("Digite o numero da conta:");
					numeroConta = parseInteger(userInput.nextLine());
				} while (numeroConta <= 0);

				conta = contaDao.consultaConta(agenciaID, numeroConta);

				if (conta == null) {
					System.out.println("Conta não encontrada!");
				}

			} while (agenciaID != 0 && conta == null);

			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("Conta selecionada:\tConta Nº " + conta.getNumeroConta()+ " - " + conta.getDescricao() +
					" ("+ conta.getTipo() + ")\t(Saldo disponível: "+conta.getSaldo()+")");
			System.out.println("\t\t\tAgencia "+conta.getCliente().getAgencia().getAgenciaID() + " - " + conta.getCliente().getAgencia().getNome()+".");
			System.out.println("\t\t\tCliente "+conta.getCliente().getNumeroCliente() + " - " + conta.getCliente().getNome()+".");
			System.out.println("------------------------------------------------------------------------------------------------");

			do {
				System.out.println("Selecione o tipo do cartão (Digite \"C\" para Crédito, \"D\" para Débito): ");
				selTipo = userInput.nextLine().trim();
			} while (!"C".equalsIgnoreCase(selTipo) && !"D".equalsIgnoreCase(selTipo));
			tipo = selTipo.toUpperCase().charAt(0);

			System.out.println("Digite uma descrição para o novo cartão (Deixe em branco p/descrição predefinida): ");
			descricao = userInput.nextLine();
			if (descricao.isEmpty()) {
				if (tipo == 'D') {
					descricao = "CARTÃO DÉBITO";
				} else {
					descricao = "CARTÃO CRÉDITO";
				}
				System.out.println("Descrição do novo cartão: \"" + descricao + "\".");
			}

			System.out.println("Digite o nome do portador do cartão: ");
			nomeNoCartao = userInput.nextLine();
			
			if (tipo == 'C') {
				System.out.println("Digite o valor do plafond mensal: ");
				plafondMensal = parseDouble(userInput.nextLine());
				plafondDisponivel = plafondMensal;
				
				do {
					System.out.println("Digite o dia do mês em que inicia cada extrato [1-28]: ");
					diaInicioExtrato = parseInteger(userInput.nextLine());
				} while (diaInicioExtrato <= 0 || diaInicioExtrato > 28);

				System.out.println("Digite qual o prazo de pagamento de extrato do novo cartão (em dias): ");
				condicoesPagamento = parseInteger(userInput.nextLine());
			}

			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar, \"C\" para cancelar.");
			dadosOkay = userInput.nextLine();
		} while (!"S".equalsIgnoreCase(dadosOkay) && !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		LocalDate dataHoje = LocalDate.now();
		LocalDate dataValidadeTemp = dataHoje.plusYears(2);
		String dataValidade = String.valueOf(dataValidadeTemp.getYear() + "-" + dataValidadeTemp.getMonthValue() + "-"+ dataValidadeTemp.lengthOfMonth());

		CartaoDAO cartaoDao = new CartaoDAO();
		if (tipo == 'D') {
			Cartao cartao = new Cartao(0, "",nomeNoCartao, dataHoje.toString(), dataValidade, tipo, conta);	
			int lastId = cartaoDao.insereCartao(cartao);
			cartao.setCartaoID(lastId);
			System.out.println(cartao.toString());			
		} else {
			CartaoCredito cartaoCredito = new CartaoCredito(0, descricao, nomeNoCartao, dataHoje.toString(), dataValidade, tipo, conta,
					condicoesPagamento, diaInicioExtrato, plafondMensal, plafondDisponivel);	
			int lastId = cartaoDao.insereCartao(cartaoCredito);
			cartaoCredito.setCartaoID(lastId);
			System.out.println(cartaoCredito.toString());
		}

	}

	/* 
	 * 2-08.Altera dados do cartao
	 */
	public static void updateCartao() {
		System.out.println("\n[2-08.Altera dados do cartao] : opção ainda não implementada...\n\n");
	}
	
	/*
	 * 2-09.Apagar cartao
	 */
	public static void deleteCartao() {
		int cartaoID;
		String confirmDelete;
		
		System.out.println("\n[2-09.Apagar cartao]");
		
		Scanner userInput = new Scanner(System.in);
		
		Cartao cartao = null;
		CartaoDAO cartaoDao  =  new CartaoDAO();

		System.out.println("-------------------------------------------------------");
		System.out.println("               DADOS DO CARTÃO A ELIMINAR              ");
		System.out.println("-------------------------------------------------------");
		do {
			do {
				System.out.println("Digite o numero (ID) do cartão a eliminar (Digite 0 para cancelar):");
				cartaoID = parseInteger(userInput.nextLine());
			} while (cartaoID < 0);


			if (cartaoID != 0) {
				// Vai ler o cartao
				cartao = cartaoDao.consultaCartao(cartaoID);				
				if (cartao == null) {
					System.out.println("Cartão não encontrado!");
				}
			}
		} while (cartao == null & cartaoID != 0);

		if (cartaoID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
				
		displayCartao(cartao);

		System.out.println("Inserir \"s\" ou \"S\" para confirmar a eliminação.");
		confirmDelete = userInput.nextLine();

		if ("S".equalsIgnoreCase(confirmDelete)) {
			cartaoDao.apagaCartao(cartaoID);
		}
		
		System.out.println();
	}

	 // display dos dados de uma conta
	public static void displayCartao(Cartao cartao) {
		System.out.println("Nº DO CARTÃO: " + cartao.getCartaoID());
		System.out.println("       Conta: " + cartao.getConta().getNumeroConta());
		System.out.println("     Agencia: " + cartao.getConta().getCliente().getAgencia().getAgenciaID() + " (" +
				cartao.getConta().getCliente().getAgencia().getNome() + ")");
		System.out.println("     Cliente: " + cartao.getConta().getCliente().getNumeroCliente() + " (" + 
				cartao.getConta().getCliente().getNome() + ")");
		String tipoDescr = cartao.getTipo() == 'D' ? "DÉBITO" : "CRÉDITO";
		System.out.println("        Tipo: " + tipoDescr);
		System.out.println("Data Criacao: " + cartao.getDataCriacao());
		System.out.println("   Descrição: " + cartao.getDescricao());
		System.out.println("NomeNoCartao: " + cartao.getNomeNoCartao());
		System.out.println("    Validade: " + cartao.getDataValidade());
	}

	public static void displayCartao(CartaoCredito cartaoCredito) {
		displayCartao((Cartao) cartaoCredito);
		System.out.println();
		
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		String plafondMensal = formata.format(cartaoCredito.getPlafondMensal());
		String plafondDisponivel = formata.format(cartaoCredito.getPlafondDisponivel());
		System.out.println("  Plafond : " + plafondMensal + "(Mensal)\t" + plafondDisponivel + "(Disponivel)");
		System.out.println("Condições pagamento: " + cartaoCredito.getDiasPrazoPagamento() + " dias após emissão extrato.");
		System.out.println("Dia de inicio do extrato é a " + cartaoCredito.getDiaInicioExtrato() + " de cada mês.");
	}
	
	/*
	 * Selecao do cartao
	 */
	public static Cartao selecionaCartao() {
		int cartaoID;
		Cartao cartao = null;
		CartaoDAO cartaoDao  =  new CartaoDAO();

		Scanner userInput = new Scanner(System.in);
		
		do {
			System.out.println("Digite o numero (ID) do cartão da conta a movimentar (Digite 0 para cancelar, -1 p/Consultar cartoes do cliente):");
			cartaoID = parseInteger(userInput.next());
			
			if (cartaoID == -1) {
				listaCartoesCliente();
			} else {

				if (cartaoID != 0) {
					// Vai ler o cartao
					cartao = cartaoDao.consultaCartao(cartaoID);				
					if (cartao == null) {
						System.out.println("Cartão inexistente!");
					} else if (cartao.getTipo() != 'D') {
						System.out.println("O cartão selecionado não é um cartão de débito!");
						cartao = null;
					}
				}
			}
		} while (cartao == null & cartaoID != 0);

		if (cartaoID == 0) {
			System.out.println("Operação cancelada pelo utilizador.");
			return null;
		}
		
		cartao.toString();
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Cartão selecionado: \tCartão Nº \t[" + cartao.getCartaoID() + "]");
		System.out.println("\t\t\tConta Nº \t[" + cartao.getConta().getNumeroConta()+"]");
		System.out.println("\t\t\tAgencia Nº \t["+cartao.getConta().getCliente().getAgencia().getAgenciaID() + "-" + cartao.getConta().getCliente().getAgencia().getNome()+"]");
		System.out.println("\t\t\tCliente Nº \t["+cartao.getConta().getCliente().getNumeroCliente() + "],Nome [" + cartao.getConta().getCliente().getNome()+"]");
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		String currency = formata.format(cartao.getConta().getSaldo());
		System.out.println("\t\t\tSaldo Disponível[" + currency + "]");
		System.out.println("------------------------------------------------------------------------------------------------");

		return cartao;
	}
	
	/*
	 * 6.0 - Listar movimentos da conta
	 */
	public static void listarMovimentosConta() {
		int agenciaID, numeroConta;
		
		System.out.println("\n[6-0.Listar movimentos da conta]");
		
		Scanner userInput = new Scanner(System.in);
		
		do {
			System.out.println("Digite o numero da agência:");
			agenciaID = parseInteger(userInput.nextLine());
		} while (agenciaID <= 0);

		do {
			System.out.println("Digite o numero da conta:");
			numeroConta = parseInteger(userInput.nextLine());
		} while (numeroConta <= 0);

		ContaDAO contaDao  =  new ContaDAO();
		Conta conta = contaDao.consultaConta(agenciaID, numeroConta);
		
		if (conta == null) {
			System.out.println("Conta não encontrada!");
			return;
		}

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Lista de movimentos da conta Nº " + conta.getNumeroConta()+ " - " + conta.getDescricao() +
				" ("+ conta.getTipo() + ")\t(Saldo disponível: "+conta.getSaldo()+")");
		System.out.println("\t\t\tAgencia "+conta.getCliente().getAgencia().getAgenciaID() + " - " + conta.getCliente().getAgencia().getNome()+".");
		System.out.println("\t\t\tCliente "+conta.getCliente().getNumeroCliente() + " - " + conta.getCliente().getNome()+".");
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Resultados encontrados:");
		System.out.println("NºMOVIM. TIPO-DESCR.\tDESCRIÇÃO\t\tVALOR\t\tCARTAO\tREF:AGENCIA/CONTA/NºMOVIM.");
		
		MovimentoDAO movimentoDao = new MovimentoDAO();
		List<Movimento> movimentos = movimentoDao.listaMovimentosConta(agenciaID, numeroConta);

		StringBuilder sbmov = new StringBuilder();
		for( Movimento movimento: movimentos) {
			sbmov.append(String.valueOf(movimento.getNumeroMovimento()));
			sbmov.append("\t ");
			sbmov.append(movimento.getTipo());
			sbmov.append(" - ");
			sbmov.append(movimento.getTipoDescr());
			sbmov.append("\t");
			sbmov.append(movimento.getDescricao());
			sbmov.append("\t\t");
			NumberFormat formata = NumberFormat.getCurrencyInstance();
			String currency;
			if (movimento.getTipo() == 'D') {
				currency = formata.format(movimento.getValor() * -1);
			} else {
				currency = formata.format(movimento.getValor());
			}
			sbmov.append(currency);
			sbmov.append("\t");
			sbmov.append(String.valueOf(movimento.getCartao().getCartaoID()));
			if (movimento.getContaReferencia() != null) {
				sbmov.append("\t");
				sbmov.append(String.valueOf(movimento.getContaReferencia().getCliente().getAgencia().getAgenciaID()));
				sbmov.append("/");
				sbmov.append(String.valueOf(movimento.getContaReferencia().getNumeroConta()));
				sbmov.append("/");
				sbmov.append(String.valueOf(movimento.getRefNumeroMovimento()));
			} else {
				sbmov.append("\t-");
			}
			System.out.println(sbmov);
			sbmov.delete(0, sbmov.length());
		}
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Fim da lista. Prima Enter para continuar...");
		userInput.nextLine();
		System.out.println();

	}
	
	
	/*
	 * 6.1 - Nova transferencia
	 */
	public static void criaTransferencia() {
		double valor = 0.0;
		int refAgenciaID = 0;
		int refNumeroConta = 0;
		String descricao = "TRF";
		String dadosOkay; 
		
		System.out.println("\n[6-1.Nova transferencia]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("---------------------");
		System.out.println("DADOS DA TRANSFERENCIA");
		System.out.println("---------------------");

		Cartao cartao = selecionaCartao();
		if (cartao == null) {
			return;
		}
		
		System.out.println("A conta do cartão selecionado é a conta de destino ? Digite \"s\" ou \"S\" para confirmar:");
		dadosOkay = userInput.nextLine();
		/*
		 * se conta do cartao é a conta de destino, entao trata-se de uma transferencia 
		 * entre contas do mesmo cliente.
		 */
		String tipoDescr = "TRFOUT";
		String origemDestino = "destino";
		boolean isMesmoCliente = false;
		if("S".equalsIgnoreCase(dadosOkay)) {
			origemDestino = "origem";
			isMesmoCliente = true;
			tipoDescr = "TRFIN";
		} else if (cartao.getConta().getSaldo() <= 0) {
			System.out.println("Primeiro deposite fundos na sua conta à ordem para poder efetuar uma transferência!\nVou terminar!");
			userInput.nextLine();
			return;
		}

		ContaDAO contaDao  =  new ContaDAO();
		Conta contaReferencia = null;

		do {
			do {
				if (isMesmoCliente) {
					refAgenciaID = cartao.getConta().getCliente().getAgencia().getAgenciaID();
				} else {
					System.out.println("Digite o numero da agência da conta de "+origemDestino+" (Digite 0 p/Cancelar): ");
					refAgenciaID = parseInteger(userInput.nextLine());
				}

				if (refAgenciaID > 0) {
					do {
						System.out.println("Digite o numero da conta de "+origemDestino+" (Digite 0 p/Cancelar): ");
						refNumeroConta = parseInteger(userInput.nextLine());
						
						if (cartao.getConta().getCliente().getAgencia().getAgenciaID() == refAgenciaID &&
								cartao.getConta().getNumeroConta() == refNumeroConta) {
							System.out.println("Conta de destino e conta de origem devem ser diferentes!");
							refNumeroConta = -1;
						}
					} while (refNumeroConta < 0);

					if (refNumeroConta == 0) {
						System.out.println("Operação cancelada pelo utilizador.");
						return;
					}
					
					contaReferencia = contaDao.consultaConta(refAgenciaID, refNumeroConta);

					if (contaReferencia == null) {
						System.out.println("Conta não encontrada!");
					} else {
						System.out.println("Conta selecionada:   Nº ["+contaReferencia.getNumeroConta()+
								"-"+contaReferencia.getDescricao()+"], Tipo ["+contaReferencia.getTipo()+"]");
						System.out.println("\t\tAgencia ["+	contaReferencia.getCliente().getAgencia().getAgenciaID()+
								"-" + contaReferencia.getCliente().getAgencia().getNome() + "]");
						System.out.println("\t\tCliente [" + contaReferencia.getCliente().getNumeroCliente() +
								"-" + contaReferencia.getCliente().getNome() + "]");
						
						if (isMesmoCliente && cartao.getConta().getCliente().getNumeroCliente() != contaReferencia.getCliente().getNumeroCliente()) {
							System.out.println("Não permitido! Conta não pertence ao cliente selecionado.");
							contaReferencia = null;
						} 
						
						if (isMesmoCliente == false) {
							if (cartao.getConta().getCliente().getAgencia().getAgenciaID() == contaReferencia.getCliente().getAgencia().getAgenciaID() &&
									cartao.getConta().getCliente().getNumeroCliente() == contaReferencia.getCliente().getNumeroCliente()) {
								if ("ORDEM".equals(contaReferencia.getTipo())) {
									System.out.println("A conta de "+origemDestino+" não pode ser do tipo \"ORDEM\".");
									contaReferencia = null;
								} else {
									isMesmoCliente = true;
								}
							}
							// se for uma conta de outro cliente tem que ser conta à ordem
							if ((isMesmoCliente == false) && !("ORDEM".equals(contaReferencia.getTipo()))) {
									System.out.println("A conta destino deve ser do tipo \"ORDEM\".");
									contaReferencia = null;
							}

						}
					}

				}
			} while (refAgenciaID != 0 && contaReferencia == null);

			if (refAgenciaID == 0) {
				dadosOkay = "C";
			} else {
				do {
					System.out.println("Digite o valor a transferir: ");
					valor = parseDouble(userInput.nextLine());
					if (valor > cartao.getConta().getSaldo()) {
						System.out.println("Não tem saldo suficiente na sua conta");
					}
				} while (valor <= 0);

				System.out.println("Digite uma descrição para este movimento (opcional): ");
				descricao = userInput.nextLine();

				System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
				dadosOkay = userInput.nextLine();
			}
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}

		criaTransferencia(cartao, contaReferencia, tipoDescr, valor, descricao);
	}
	
	/*
	 * cria transferencia ja´com dados do cartao da conta à ordem, contaDestino e valor a transferir 
	 */
	public static void criaTransferencia(Cartao cartao, Conta contaReferencia, String tipoDescr, double valor, String descricao) {
		/*
		 * cartao -> é sempre referente à conta à ordem do cliente que realiza a tranferencia
		 * tipoDescr : 	TRFIN 	-> entrada na conta à ordem (apenas as mobilizacao de contas do cliente)
		 * 				TRFOUT 	-> saida da conta à ordem (reforço/constituição contas poupanca, 
		 * 						ou transferencia para conta outro cliente)
		 * contaReferencia 	-> se for uma entrada (TRFIN) corresponde à conta origem da transferencia
		 *  				-> se for uma saída (TRFOUT) corresponde à conta de destino da transferencia
		 */
		
		System.out.println("A criar transferencia! Aguarde...");
		
		Scanner userInput = new Scanner(System.in);
		
		/*
		 * Se a conta destino é do mesmo cliente, usa o mesmo cartao nas duas contas;
		 * Se a conta destino é de outro cliente, cria um cartao fictício "em branco"
		 */
		Cartao tempCartao = null;
		if (cartao.getConta().getCliente().getAgencia().getAgenciaID() == contaReferencia.getCliente().getAgencia().getAgenciaID() &&
				cartao.getConta().getCliente().getNumeroCliente() == contaReferencia.getCliente().getNumeroCliente()) {
			tempCartao = new Cartao(cartao.getCartaoID(), cartao.getDescricao(), cartao.getNomeNoCartao(), cartao.getDataCriacao(),
					cartao.getDataValidade(), cartao.getTipo(), contaReferencia);
		} else {
			tempCartao = new Cartao(0, "", "", "1900-01-01", LocalDate.MAX.toString(), 'D', contaReferencia);
		}
		
		//Debug
		System.out.println(cartao.toString());
		System.out.println(tempCartao.toString());
		//userInput.nextLine();
		// ** ** ** ** ** ** ** ** ** ** ** ** ** ** **
		
		if ("TRFOUT".equals(tipoDescr)) {
			//boolean isTransfereOk = cartao.levanta(valor);
			boolean isTransfereOk = cartao.tranfere(contaReferencia, valor);
			if (isTransfereOk == false) {
				System.out.println("Operação cancelada! Verifique os dados p.f.");
				userInput.nextLine();
				return;			
			}
		} else {
			boolean isTransfereOk = tempCartao.tranfere(cartao.getConta(), valor);
			if (isTransfereOk == false) {
				System.out.println("Operação cancelada! Verifique os dados p.f.");
				userInput.nextLine();
				return;			
			}
		}

		/*----------------------------------------------------------------
		 * Atualiza na CONTA À ORDEM ORIGEM o saldo e o ultimo movimento
		 */
		int agenciaID = cartao.getConta().getCliente().getAgencia().getAgenciaID();
		int numeroConta = cartao.getConta().getNumeroConta();
		
		int ultimoMovimento = cartao.getConta().getUtimoMovimento() + 1;
		double saldo = cartao.getConta().getSaldo();
		
		ContaDAO contaDao = new ContaDAO();		
		contaDao.atualizaSaldoUltimoMovimento(agenciaID, numeroConta, saldo, ultimoMovimento);

		cartao.getConta().setUltimoMovimento(ultimoMovimento);
		
		/*----------------------------------------------------------------
		 * Atualiza na CONTA DE REFERENCIA o saldo e o ultimo movimento
		 */
		agenciaID = tempCartao.getConta().getCliente().getAgencia().getAgenciaID();
		numeroConta = tempCartao.getConta().getNumeroConta();
		
		int ultimoMovimentoRef = tempCartao.getConta().getUtimoMovimento() + 1;
		saldo = tempCartao.getConta().getSaldo();
		
		contaDao.atualizaSaldoUltimoMovimento(agenciaID, numeroConta, saldo, ultimoMovimento);

		tempCartao.getConta().setUltimoMovimento(ultimoMovimentoRef);

		/*
		 *----------------------------------------------------------------
		 */
		LocalDateTime today = LocalDateTime.now();
		String dataMovimento = today.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		char tipoA, tipoB;
		String tipoDescrA, tipoDescrB;
		if (tipoDescr == "TRFOUT") {
			tipoA = 'D';
			tipoDescrA = "TRFOUT";
			tipoB = 'C';
			tipoDescrB = "TRFIN";
		} else {
			tipoA = 'C';
			tipoDescrA = "TRFIN";
			tipoB = 'D';
			tipoDescrB = "TRFOUT";
		}

		Movimento movimentoA = new Movimento(ultimoMovimento, dataMovimento, valor, tipoA, tipoDescrA, descricao, cartao, contaReferencia, ultimoMovimentoRef);
		Movimento movimentoB = new Movimento(ultimoMovimentoRef, dataMovimento, valor, tipoB, tipoDescrB, descricao, tempCartao, cartao.getConta(), ultimoMovimento);
		MovimentoDAO movimentoDao = new MovimentoDAO();
		movimentoDao.insereMovimento(movimentoA);
		movimentoDao.insereMovimento(movimentoB);

	}
	
	
	/*
	 * 6.2 - Novo levantamento
	 */
	public static void criaLevantamento() {
		double valor;
		String descricao, dadosOkay; 
		
		System.out.println("\n[6-2.Novo levantamento]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("---------------------");
		System.out.println("DADOS DO LEVANTAMENTO");
		System.out.println("---------------------");

		Cartao cartao = selecionaCartao();
		
		if (cartao == null) {
			return;
		}
		
		do {
			do {
				System.out.println("Digite o valor do levantamento: ");
				valor = parseDouble(userInput.nextLine());
			} while (valor <= 0);

			System.out.println("Digite uma descrição para este movimento (opcional): ");
			descricao = userInput.nextLine();
			
			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
			dadosOkay = userInput.nextLine();
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}

		boolean isLevantaOk = cartao.levanta(valor);
		if (isLevantaOk == false) {
			System.out.println("Operação cancelada! Verifique os dados p.f.");
			userInput.nextLine();
			return;			
		}
		
		//System.out.println(cartao.toString());
		/*
		 * atualiza na conta o saldo e o ultimo movimento
		 */
		int agenciaID = cartao.getConta().getCliente().getAgencia().getAgenciaID();
		int numeroConta = cartao.getConta().getNumeroConta();
		
		int ultimoMovimento = cartao.getConta().getUtimoMovimento() + 1;
		double saldo = cartao.getConta().getSaldo();
		
		ContaDAO contaDao = new ContaDAO();		
		contaDao.atualizaSaldoUltimoMovimento(agenciaID, numeroConta, saldo, ultimoMovimento);
		/*
		 * criar aqui o movimento
		 */
		cartao.getConta().setUltimoMovimento(ultimoMovimento);
		
		LocalDateTime today = LocalDateTime.now();
		String dataMovimento = today.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Movimento movimento = new Movimento(ultimoMovimento, dataMovimento, valor, 'D', "LEV", descricao, cartao, null, -1);
		
		MovimentoDAO movimentoDao = new MovimentoDAO();
		movimentoDao.insereMovimento(movimento);
		
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		String currency = formata.format(cartao.getConta().getSaldo());
		System.out.println("\n****************************************");
		System.out.println("NOVO SALDO : " + currency);
		System.out.println("****************************************\n");
	}

	/*
	 * 6.3 - Novo deposito
	 */
	public static void criaDeposito() {
		double valor;
		String descricao, dadosOkay; 
		
		System.out.println("\n[6-3.Novo deposito]");

		Scanner userInput = new Scanner(System.in);
		
		System.out.println("-----------------");
		System.out.println("DADOS DO DEPÓSITO");
		System.out.println("-----------------");

		Cartao cartao = selecionaCartao();
		
		if (cartao == null) {
			return;
		}
		
		do {
			do {
				System.out.println("Digite o valor a depositar: ");
				valor = parseDouble(userInput.nextLine());
			} while (valor <= 0);
			
			System.out.println("Digite uma descrição para este movimento (opcional): ");
			descricao = userInput.nextLine();
			
			System.out.println("Os dados estão corretos? Inserir \"s\" ou \"S\" para confirmar. (\"C\" para cancelar)");
			dadosOkay = userInput.nextLine();
		} while (!"S".equalsIgnoreCase(dadosOkay) & !"C".equalsIgnoreCase(dadosOkay));

		if ("C".equalsIgnoreCase(dadosOkay)) {
			System.out.println("Operação cancelada pelo utilizador.");
			return;
		}
		
		cartao.deposita(valor);
		System.out.println(cartao.toString());
		/*
		 * atualiza na conta o saldo e o ultimo movimento
		 */
		int agenciaID = cartao.getConta().getCliente().getAgencia().getAgenciaID();
		int numeroConta = cartao.getConta().getNumeroConta();
		
		int ultimoMovimento = cartao.getConta().getUtimoMovimento() + 1;
		double saldo = cartao.getConta().getSaldo();
		
		ContaDAO contaDao = new ContaDAO();		
		contaDao.atualizaSaldoUltimoMovimento(agenciaID, numeroConta, saldo, ultimoMovimento);
		/*
		 * criar aqui o movimento
		 */
		cartao.getConta().setUltimoMovimento(ultimoMovimento);
		
		LocalDateTime today = LocalDateTime.now();
		String dataMovimento = today.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		Movimento movimento = new Movimento(ultimoMovimento, dataMovimento, valor, 'C', "DEP", descricao, cartao, null, -1);
		
		MovimentoDAO movimentoDao = new MovimentoDAO();
		movimentoDao.insereMovimento(movimento);
		
		NumberFormat formata = NumberFormat.getCurrencyInstance();
		String currency = formata.format(cartao.getConta().getSaldo());
		System.out.println("\n****************************************");
		System.out.println("NOVO SALDO : " + currency);
		System.out.println("****************************************\n");
	}
	
	private static double parseDouble(String s){
	    if(s == null || s.isEmpty()) 
	        return 0.0;
	    else
	        return Double.parseDouble(s);
	}	
	private static int parseInteger(String s){
	    if(s == null || s.isEmpty() || !s.matches("[0-9]*")) 
	        return -1;
	    else
	        return Integer.parseInt(s);
	}	
	
	/*
	 * 6.4 - Avancar um periodo
	 */
	public static void avancaPeriodo() {
		System.out.println("\n[6-4.Avancar um periodo] : opção ainda não implementada...\n\n");
	}

	/*
	 * 6.5 - Apagar um movimento
	 */
	public static void deleteMovimento() {
		System.out.println("\n[6-5.Apagar um movimento] : opção ainda não implementada...\n\n");
	}


}