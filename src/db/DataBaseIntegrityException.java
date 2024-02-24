package db;

/*
 * Exceção personalizada para erro de Integridade de dados
 * caso haja uma transação com a possibilidade de erro de integridade
 * essa exceção será chamada
 * */

public class DataBaseIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	public DataBaseIntegrityException(String msg) {
		super(msg);
	}
	
}
