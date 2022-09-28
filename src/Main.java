
public class Main {

	public static void main(String[] args) {
		Banco banco = Banco.getInstance();
		banco.cadastrarCliente("Victor");
		banco.cadastrarCliente("James Bond");
		
		banco.getClientes().get(0).abrirConta(Conta.CONTA_POUPANCA);
		banco.getClientes().get(0).getContas().get(0).depositar(100);
		banco.getClientes().get(0).getContas().get(0).render();
		banco.getClientes().get(0).getContas().get(0).imprimirExtrato();
		
		banco.getClientes().get(0).abrirConta(Conta.CONTA_CORRENTE);
		banco.getClientes().get(0).getContas().get(1).depositar(50);
		
		banco.getClientes().get(0).getContas().get(0).transferir(30, banco.getClientes().get(0).getContas().get(1));
		
		banco.getClientes().get(0).getContas().get(0).imprimirExtrato();
		banco.getClientes().get(0).getContas().get(1).imprimirExtrato();
		

	}

}
