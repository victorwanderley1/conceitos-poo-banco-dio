import java.util.ArrayList;
import java.util.List;

public class Banco {
	private String nome;
	private static Banco INSTANCE;
	private List<Cliente> clientes;
	
	public Banco() {
		this.nome = "Banco Supremo";
		this.clientes = new ArrayList<>();
	}
	
	public static Banco getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Banco();
		}
		return INSTANCE;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void transferirEntreContas(Conta remetente, Conta destino, double valor) {
		destino.receberTransferencia(remetente, valor);
	}
	
	public void cadastrarCliente(String nome) {
		this.clientes.add(new Cliente(nome));
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
}
