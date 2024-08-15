import java.sql.*;

public class NavegadorDeRegistro {

    public static String[] primeiroRegistro(String db, String tbl) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPrimeiroRegistro = "SELECT * FROM " + db + "." + tbl + " ORDER BY id ASC LIMIT 1;";
        Statement stmSqlPrimeiroRegistro = conexao.createStatement();
        ResultSet rstSqlPrimeiroRegistro = stmSqlPrimeiroRegistro.executeQuery(strSqlPrimeiroRegistro);
        rstSqlPrimeiroRegistro.next();
        String[] resultado = {
            rstSqlPrimeiroRegistro.getString("id"),
            rstSqlPrimeiroRegistro.getString("nome"),
            rstSqlPrimeiroRegistro.getString("email"),
            rstSqlPrimeiroRegistro.getString("senha")
        };
        stmSqlPrimeiroRegistro.close();
        return resultado;
    }

    public static String[] ultimoRegistro(String db, String tbl) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlUltimoRegistro = "SELECT * FROM " + db + "." + tbl + " ORDER BY id DESC LIMIT 1;";
        Statement stmSqlUltimoRegistro = conexao.createStatement();
        ResultSet rstSqlUltimoRegistro = stmSqlUltimoRegistro.executeQuery(strSqlUltimoRegistro);
        rstSqlUltimoRegistro.next();
        String[] resultado = {
            rstSqlUltimoRegistro.getString("id"),
            rstSqlUltimoRegistro.getString("nome"),
            rstSqlUltimoRegistro.getString("email"),
            rstSqlUltimoRegistro.getString("senha")
        };
        stmSqlUltimoRegistro.close();
        return resultado;
    }

    public static String[] registroAnterior(String db, String tbl, String currentId) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlRegistroAnterior = "SELECT * FROM " + db + "." + tbl + " WHERE id < ? ORDER BY id DESC LIMIT 1;";
        PreparedStatement pstRegistroAnterior = conexao.prepareStatement(strSqlRegistroAnterior);
        pstRegistroAnterior.setString(1, currentId);
        ResultSet rstRegistroAnterior = pstRegistroAnterior.executeQuery();
        rstRegistroAnterior.next();
        String[] resultado = {
            rstRegistroAnterior.getString("id"),
            rstRegistroAnterior.getString("nome"),
            rstRegistroAnterior.getString("email"),
            rstRegistroAnterior.getString("senha")
        };
        pstRegistroAnterior.close();
        return resultado;
    }

    public static String[] proximoRegistro(String db, String tbl, String currentId) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlProximoRegistro = "SELECT * FROM " + db + "." + tbl + " WHERE id > ? ORDER BY id ASC LIMIT 1;";
        PreparedStatement pstProximoRegistro = conexao.prepareStatement(strSqlProximoRegistro);
        pstProximoRegistro.setString(1, currentId);
        ResultSet rstProximoRegistro = pstProximoRegistro.executeQuery();
        rstProximoRegistro.next();
        String[] resultado = {
            rstProximoRegistro.getString("id"),
            rstProximoRegistro.getString("nome"),
            rstProximoRegistro.getString("email"),
            rstProximoRegistro.getString("senha")
        };
        pstProximoRegistro.close();
        return resultado;
    }

    public static boolean isPrimeiroRegistro(String db, String tbl, String currentId) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlPrimeiroRegistro = "SELECT id FROM " + db + "." + tbl + " ORDER BY id ASC LIMIT 1;";
        Statement stmSqlPrimeiroRegistro = conexao.createStatement();
        ResultSet rstSqlPrimeiroRegistro = stmSqlPrimeiroRegistro.executeQuery(strSqlPrimeiroRegistro);
        rstSqlPrimeiroRegistro.next();
        boolean isPrimeiro = rstSqlPrimeiroRegistro.getString("id").equals(currentId);
        stmSqlPrimeiroRegistro.close();
        return isPrimeiro;
    }

    public static boolean isUltimoRegistro(String db, String tbl, String currentId) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlUltimoRegistro = "SELECT id FROM " + db + "." + tbl + " ORDER BY id DESC LIMIT 1;";
        Statement stmSqlUltimoRegistro = conexao.createStatement();
        ResultSet rstSqlUltimoRegistro = stmSqlUltimoRegistro.executeQuery(strSqlUltimoRegistro);
        rstSqlUltimoRegistro.next();
        boolean isUltimo = rstSqlUltimoRegistro.getString("id").equals(currentId);
        stmSqlUltimoRegistro.close();
        return isUltimo;
    }

    public static void atualizarRegistro(String db, String tbl, String id, String nome, String email, String senha) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlAtualizar = "UPDATE " + db + "." + tbl + " SET nome = ?, email = ?, senha = ? WHERE id = ?;";
        PreparedStatement pstAtualizar = conexao.prepareStatement(strSqlAtualizar);
        pstAtualizar.setString(1, nome);
        pstAtualizar.setString(2, email);
        pstAtualizar.setString(3, senha);
        pstAtualizar.setString(4, id);
        pstAtualizar.executeUpdate();
        pstAtualizar.close();
    }

    public static void deletarRegistro(String db, String tbl, String id) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlDeletar = "DELETE FROM " + db + "." + tbl + " WHERE id = ?;";
        PreparedStatement pstDeletar = conexao.prepareStatement(strSqlDeletar);
        pstDeletar.setString(1, id);
        pstDeletar.executeUpdate();
        pstDeletar.close();
    }

    public static void adicionarNovoRegistro(String db, String tbl, String nome, String email, String senha) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlAdicionar = "INSERT INTO " + db + "." + tbl + " (nome, email, senha) VALUES (?, ?, ?);";
        PreparedStatement pstAdicionar = conexao.prepareStatement(strSqlAdicionar);
        pstAdicionar.setString(1, nome);
        pstAdicionar.setString(2, email);
        pstAdicionar.setString(3, senha);
        pstAdicionar.executeUpdate();
        pstAdicionar.close();
    }

    public static String[] buscarRegistro(String db, String tbl, String nomeOuEmail) throws Exception {
        Connection conexao = MySQLConnector.conectar();
        String strSqlBuscar = "SELECT * FROM " + db + "." + tbl + " WHERE nome LIKE ? OR email LIKE ?;";
        PreparedStatement pstBuscar = conexao.prepareStatement(strSqlBuscar);
        String pesquisa = "%" + nomeOuEmail + "%";
        pstBuscar.setString(1, pesquisa);
        pstBuscar.setString(2, pesquisa);
        ResultSet rstBuscar = pstBuscar.executeQuery();

        if (rstBuscar.next()) {
            String[] resultado = {
                rstBuscar.getString("id"),
                rstBuscar.getString("nome"),
                rstBuscar.getString("email"),
                rstBuscar.getString("senha")
            };
            pstBuscar.close();
            return resultado;
        } else {
            pstBuscar.close();
            throw new Exception("Nenhum registro encontrado.");
        }
    }
}
