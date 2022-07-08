package com.algaworks.algafood.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);

	String gerarNomeArquivo(String nomeOriginal);

	@Builder
	@Getter
	class NovaFoto {

		private String nomeAquivo;
		private InputStream inputStream;

	}

	void remover(String nomeArquivo);

	void substituir(NovaFoto novaFoto, String nomeArquivoExistente);

	InputStream recuperar(String nomeArquivo);
}
