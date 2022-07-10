package com.algaworks.algafood.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.exception.StorageException;
import com.algaworks.algafood.service.FotoStorageService;
import com.algaworks.algafood.storage.StorageProperties;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

@Service
public class S3FotoStorageServiceImpl implements FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeAquivo());
			
			var objectMetadata = new ObjectMetadata();
			
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(),
					caminhoArquivo,
					novaFoto.getInputStream(),
					objectMetadata);
			
			amazonS3.putObject(putObjectRequest);
		 } catch (Exception e) {
			 throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
		}
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}


	@Override
	public void remover(String nomeArquivo) {
	
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
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
	}

}
