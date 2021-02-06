package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {

	private Connection con;

	public ContatoDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos" + "(nome,email,endereco,dataNascimento)" + "values(?,?,?,?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.con.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			contatos = new ArrayList<Contato>();

			while (rs.next()) {
				Contato contato = new Contato();
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				contatos.add(contato);
			}

			rs.close();
			stmt.close();

			return contatos;

		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	public Contato getContatoById(int id) {
		try {
			Contato contato = new Contato();
			PreparedStatement stmt = this.con.prepareStatement("select * from contatos where id = ?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				contato = new Contato();
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}

			rs.close();
			stmt.close();

			return contato;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
