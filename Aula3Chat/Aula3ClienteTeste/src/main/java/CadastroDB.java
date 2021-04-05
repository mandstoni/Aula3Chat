import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroDB {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public CadastroDB() {
        connection = Conexao.getConnection();
    }

    public boolean inserir(Message message) throws SQLException {

        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("INSERT INTO AGENDA (nome, mensagem) values (?, ?)");

            stmt.setString(1, message.getUsuario());
            stmt.setString(2, message.getMessage());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {

            connection.close();

        }
        return false;
    }

    public boolean alterar(Message message) throws SQLException {

        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("UPDATE AGENDA SET  mensagem = ? WHERE nome = ?");

            stmt.setString(1, message.getUsuario());
            stmt.setString(2, message.getMessage());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {

            connection.close();

        }
        return false;
    }

    public List<Message> getAll() {

        List<Message> lstCadastro = new ArrayList<>();
        try {
            ps = this.connection.prepareStatement("SELECT nome, mensagem FROM MESSAGE");
            rs = ps.executeQuery();

            while (rs.next()) {
                lstCadastro.add(new Message(rs.getString("nome"), rs.getString("mensagem")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public boolean excluir(Message message) throws SQLException {

        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("DELETE FROM MESSAGE WHERE NOME = ?");

            stmt.setString(1, message.getMessage());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {

            connection.close();

        }
        return false;
    }
}
