package com.luis.sebolivros.domain.common.Service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepository;

    public void instaciaDB(){

        Autor autor01 = new Autor("Homero", null);
        Autor autor02 = new Autor("William Shakespeare", null);
        Autor autor03 = new Autor("Machado de Assis", null);
        Autor autor04 = new Autor("George Orwell", null);
        Autor autor05 = new Autor("J. R. R. Tolkien", null);
        Autor autor06 = new Autor("Clarice Lispector", null);
        Autor autor07 = new Autor("Franz Kafka", null);
        Autor autor08 = new Autor("Agatha Christie", null);

        autorRepository.saveAll(Arrays.asList(
                autor01, autor02, autor03, autor04,
                autor05, autor06, autor07, autor08));
    }
}
