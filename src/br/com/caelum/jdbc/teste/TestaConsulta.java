package br.com.caelum.jdbc.teste;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaConsulta {

	public static void main(String[] args) {

		ContatoDao dao = new ContatoDao();

		Contato contato = dao.getContatoById(1);

		System.out.println(contato.getNome());
		System.out.println(contato.getEmail());
		System.out.println(contato.getEndereco());

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = df.format(contato.getDataNascimento().getTime());
		System.out.println(dataNascimento);

	}

}
