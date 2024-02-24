package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {

	
	private static Connection conn = null;
	
	//Método que cria um conexão com o Banco de Dados
	public static Connection getConnection() {
		if(conn == null) {
			
			try {
				Properties props = loadProperties();
				
				conn = DriverManager.getConnection(props.getProperty("dburl"), props);
			}
			catch(SQLException e ) {
				throw new DataBaseException(e.getMessage());
			}
			
		}
		
		return conn;
	}
	
	
	//Método que fecha a conexão com o Banco de Dados se ela existir
	public static void closeConnection() {
		if(conn != null) {
			
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new DataBaseException(e.getMessage());
			}
		}
	}
	
	//Método que carrega e retorna as propriedas do arquivo db.properties
	private static Properties loadProperties() {
		
		try {
			FileInputStream fs = new FileInputStream("src//db.properties");
			Properties props = new Properties();
			props.load(fs);
			
			return props;
		}
		catch(IOException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	//Método que fecha um Statement se ele for != null
	public static void closeStatement(Statement stt) {
		if(stt != null) {
			try {
				stt.close();
			}
			catch(SQLException e) {
				throw new DataBaseException(e.getMessage());
			}
		}
	}
	
	//Método que fecha um ResultSet se ele for != null
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}
			catch(SQLException e) {
				throw new DataBaseException(e.getMessage());
			}
		}
	}
}
