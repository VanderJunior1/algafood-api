package com.algaworks.algafood.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.exception.StorageException;
import com.algaworks.algafood.service.FotoStorageService;

@Service
public class FotoStorageServiceImpl implements FotoStorageService {

	@Value("${storage.local.diretorio-fotos}")
	private Path diretorioFotos;

	@Override
	public void remover(String nomeArquivo) {
		Path path = getArquivoPath(nomeArquivo);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Não foi possivel excluir o arquivo.", e);
		}
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {

		Path path = getArquivoPath(novaFoto.getNomeAquivo());

		try {
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(path));
		} catch (IOException e) {
			throw new StorageException("Não foi possivel armazenar arquivo.", e);
		}
	}

	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}

	@Override
	public String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	@Override
	public void substituir(NovaFoto novaFoto, String nomeArquivoExistente) {
		this.armazenar(novaFoto);
		if (nomeArquivoExistente != null) {
			remover(nomeArquivoExistente);
		}
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);

			return Files.newInputStream(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar arquivo.", e);
		}
	}
}
