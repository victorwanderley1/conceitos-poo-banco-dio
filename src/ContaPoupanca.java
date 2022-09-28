import java.util.logging.Logger;

public class ContaPoupanca extends Conta {
	private static final Logger LOGGER = Logger.getLogger(ContaCorrente.class.getName());
	public ContaPoupanca() {
		super(Conta.CONTA_POUPANCA);
	}
	
	@Override
	public void imprimirExtrato() {
		LOGGER.info("###### Extrato Conta Poupança #########"+
		informacoesComuns());
	}
	
	@Override
	public void render() {
		this.depositar(this.getSaldo()*0.5);		
	}
	
}
