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
		private String contentType;
		private Long size;
		private InputStream inputStream;
	}
	
	@Builder @Getter
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;
		
		public boolean inputStream() {
			return inputStream != null;
		}
		
		public boolean temUrl() {
			return url != null;
		}
		
	}

	void remover(String nomeArquivo);

	void substituir(NovaFoto novaFoto, String nomeArquivoExistente);

	FotoRecuperada recuperar(String nomeArquivo);
}
