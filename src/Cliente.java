import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Cliente {
	private static Logger LOGGER = Logger.getLogger(Cliente.class.getName());
	private String nome;
	private List<Conta> contas = new ArrayList<Conta>();

	public Cliente(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Conta> getContas(){
		return this.contas;
	}

	public void abrirConta(Character tipoConta) {
		switch (tipoConta) {
		case 'P': {
			contas.add(new ContaPoupanca());
			break;
		}
		case 'C': {
			contas.add(new ContaCorrente());
			break;
		}
		default: {
			LOGGER.severe("Tipo de conta inexistente: " + tipoConta);
			break;
		}
		}
	}
}
