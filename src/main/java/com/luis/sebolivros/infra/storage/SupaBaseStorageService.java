package com.luis.sebolivros.infra.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Service
public class SupaBaseStorageService {

    private final String SUPABASE_URL = "https://utskkhxqkzwawkhnoxmq.supabase.co";

    @Value("${supabase.api.key}")
    private String API_KEY;

    private final String BUCKET = "livros";

    public String uploadImagem(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String url = SUPABASE_URL + "/storage/v1/object/" + BUCKET + "/" + fileName;

            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", API_KEY);
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            HttpEntity<byte[]> entity = new HttpEntity<>(file.getBytes(), headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return SUPABASE_URL + "/storage/v1/object/public/" + BUCKET + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar imagem", e);
        }
    }
}
