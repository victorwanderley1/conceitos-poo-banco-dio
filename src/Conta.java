import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	private static int sequencial = 1;
	private static final int AGENCIA_PADRAO = 1;
	public static final Character CONTA_POUPANCA = 'P';
	public static final Character CONTA_CORRENTE = 'C';
	
	private int agencia;
	private int numero;
	private double saldo;
	private Character tipoConta;
	private List<String> movimentacoes = new ArrayList<>();
	
	protected Conta(Character tipoConta) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = Conta.sequencial++;
		this.tipoConta = tipoConta;
		this.saldo = 0.0;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public void sacar(double valor) {
		efetuarSaque(valor);
		registrarSaque(valor);
	}
	
	private void efetuarSaque(double valor) {
		this.saldo -= valor;
	}
	
	private void registrarSaque(double valor) {
		registrarMovimentacao("Saque R$"+valor);
	}

	@Override
	public void depositar(double valor) {
		efetuarDeposito(valor);
		registrarDeposito(valor);
	}
	private void efetuarDeposito(double valor) {
		this.saldo += valor;
	}
	
	private void registrarDeposito(double valor) {
		registrarMovimentacao("Deposito R$"+valor);
	}

	@Override
	public void transferir(double valor, Conta contaDestino) {
		this.efetuarSaque(valor);
		Banco.getInstance().transferirEntreContas(this, contaDestino, valor);
		registrarTransferencia(valor, contaDestino);
	}
	
	private void registrarTransferencia(double valor, Conta contaDestino) {
		registrarMovimentacao("Tranferencia p/ conta: "+contaDestino.getNumero()+"/"+contaDestino.getAgencia()
		+"R$ "+valor);
	}
	
	public void receberTransferencia(Conta contaRemetente, double valor) {
		efetuarDeposito(valor);
		registrarRecebimentoTransferencia(contaRemetente, valor);
	}
	
	private void registrarRecebimentoTransferencia(Conta contaRemetente, double valor) {
		registrarMovimentacao("Tranferencia vinda da conta: "+contaRemetente.getNumero()+"/"+contaRemetente.getAgencia()
		+"R$ "+valor);
	}
	
	protected String informacoesComuns() {
		return "\nAgência: "+this.getAgencia()
		+"\nConta Num: "+this.getNumero()
		+"\nTipo de conta: "+(this.tipoConta.equals(CONTA_POUPANCA)?"Conta Poupança":"Conta Corrente")
		+"\nSaldo: R$ "+this.getSaldo()
		+"\n\nMovimentações:\n\n"
		+this.getMovimentacoes();
	}
	
	private String getMovimentacoes() {
		StringBuilder sb = new StringBuilder();
		this.movimentacoes.stream().forEach(movimento -> sb.append(movimento+"\n"));
		return sb.toString();
	}
	
	private void registrarMovimentacao(final String movimentacao) {
		this.movimentacoes.add(montarRegistroMovimentacao(movimentacao));
	}
	
	private String montarRegistroMovimentacao(final String movimentacao) {
		StringBuilder sb = new StringBuilder();
		sb.append(dataAtual()).append("     ").append(movimentacao);
		return sb.toString();
	}
	
	private String dataAtual() {
		return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
}