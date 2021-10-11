package conversaojava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import buscarEndereco.Endereco;

public class ServicoDeCEP {

	static String webService = "http://viacep.com.br/ws/";
	static int codigoSucesso = 200;

	public static Endereco buscaEnderecoPelo(String cep) throws Exception {
		String formatacaoParaURL = webService + cep + "/json";

		try {
			URL url = new URL(formatacaoParaURL);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			if (conexao.getResponseCode() != codigoSucesso)
				throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

			else {

				BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));

				Gson gson = new Gson();
				Endereco endereco = gson.fromJson(resposta, Endereco.class);

				return endereco;

			}
		} catch (Exception e) {
			throw new Exception("ERRO: " + e);
		}
	}
}
