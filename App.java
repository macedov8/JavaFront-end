import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Connection conexao = MySQLConnector.conectar();
        System.out.println(conexao);
    }
}
