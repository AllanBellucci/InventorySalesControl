package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db.DB;
import db.DBException;
import model.dao.ClientDao;
import model.entity.Client;

/**
 * @author ALLAN
 *
 */
public class ClientDaoJDBC implements ClientDao {

	private Connection conn;

	/**
	 * @param conn
	 */
	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Client client) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO inventorysalescontrol.clients (cli_name) VALUE (?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, client.getName());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					client.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DBException("Unexpected Error, No rows Affected!");
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closePrepareStatement(ps);
		}

	}

	@Override
	public void update(Client client) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE clients SET clients.cli_name=? where clients.cli_id=?");
			ps.setString(1, client.getName());
			ps.setInt(2, client.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closePrepareStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete from clients WHERE cli_id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closePrepareStatement(ps);
		}

	}

	@Override
	public Client findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * from clients where clients.cli_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Client client = instantiateClient(rs);
				return client;
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closePrepareStatement(ps);
			DB.closeResultSet(rs);
		}

	}

	private Client instantiateClient(ResultSet rs) throws SQLException {
		Client client = new Client();
		client.setId(rs.getInt("cli_id"));
		client.setName(rs.getString("cli_name"));
		return client;
	}

	@Override
	public List<Client> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * from clients order by cli_id");
			rs = ps.executeQuery();
			List<Client> list = new ArrayList<Client>();
			while (rs.next()) {
				Client client = instantiateClient(rs);
				list.add(client);
			}
			return list;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closePrepareStatement(ps);
			DB.closeResultSet(rs);
		}

	}

}
