package db;

/*
 * Exceção personalizada criada exclusivamente para a conexão com o Banco de Dados
 * uma forma prática de detecção de erro
 * */

public class DataBaseException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	
	public DataBaseException(String msg) {
		super(msg);
	}
	
}
