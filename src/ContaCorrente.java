import java.util.logging.Logger;

public class ContaCorrente extends Conta {
	private static final Logger LOGGER = Logger.getLogger(ContaCorrente.class.getName());
	public ContaCorrente() {
		super(Conta.CONTA_CORRENTE);
	}

	@Override
	public void imprimirExtrato() {
		LOGGER.info("###### Extrato Conta Corrente #########"+
		informacoesComuns());
	}
	
	@Override
	public void render() {
		this.depositar(this.getSaldo()*0.1);		
	}

}
