package com.luis.sebolivros.domain.common.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.repository.EditoraRepository;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.enums.Condicao;
import com.luis.sebolivros.domain.livro.enums.Estado;
import com.luis.sebolivros.domain.livro.repository.LivroRepository;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SeboRepository seboRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void instaciaDB(){

        Autor autor01 = new Autor("Homero", null);
        Autor autor02 = new Autor("William Shakespeare", null);
        Autor autor03 = new Autor("Machado de Assis", null);
        Autor autor04 = new Autor("George Orwell", null);
        Autor autor05 = new Autor("J. R. R. Tolkien", null);
        Autor autor06 = new Autor("Clarice Lispector", null);
        Autor autor07 = new Autor("Franz Kafka", null);
        Autor autor08 = new Autor("Agatha Christie", null);

        Editora editora01 = new Editora("Penguin-Companhia", null);
        Editora editora02 = new Editora("Record", null);
        Editora editora03 = new Editora("HarperCollins", null);
        Editora editora04 = new Editora("Companhia das Letras", null);
        Editora editora05 = new Editora("Rocco", null);
        Editora editora06 = new Editora("Martins Fontes", null);

        Sebo sebo01 = new Sebo(null, "Maravilha", "maravilha@gmail.com", encoder.encode("123"), "01001-000");
        Sebo sebo02 = new Sebo(null, "Sebo Central", "central@gmail.com", encoder.encode("123"), "20040-020");
        Sebo sebo03 = new Sebo(null, "Sebo Antiguidades", "antigo@gmail.com", encoder.encode("123"), "30140-110");
        Sebo sebo04 = new Sebo(null, "Sebo Universitário", "uni@gmail.com", encoder.encode("123"), "70040-010");
        Sebo sebo05 = new Sebo(null, "Sebo Vintage", "vintage@gmail.com", encoder.encode("123"), "80010-000");

        Livro livro01 = new Livro(null, "A Odisseia", 2, autor01, 2014, editora01, "9788563560271", Condicao.CONSERVADO, sebo01);
        Livro livro02 = new Livro(null, "Hamlet", 2, autor02, 2012, editora02, "9788503012304", Condicao.CONSERVADO, sebo01);
        Livro livro03 = new Livro(null, "Dom Casmurro", 4, autor03, 2019, editora04, "9788535925697", Condicao.CONSERVADO, sebo01);
        Livro livro04 = new Livro(null, "1984", 5, autor04, 2009, editora03, "9788535914844", Condicao.CONSERVADO, sebo01);
        Livro livro05 = new Livro(null, "O Senhor dos Anéis: A Sociedade do Anel", 2, autor05, 2019, editora05, "9788533613374", Condicao.CONSERVADO, sebo01);
        Livro livro06 = new Livro(null, "A Hora da Estrela", 3, autor06, 2020, editora04, "9788535929770", Condicao.CONSERVADO, sebo03);
        Livro livro07 = new Livro(null, "A Metamorfose", 4, autor07, 2017, editora06, "9788571648496", Condicao.CONSERVADO, sebo04);
        Livro livro08 = new Livro(null, "Assassinato no Expresso do Oriente", 2, autor06, 2011, editora02, "9788503011345", Condicao.CONSERVADO, sebo05);
        Livro livro09 = new Livro(null, "Ilíada", 2, autor01, 2015, editora01, "9788563560288", Condicao.CONSERVADO, sebo05);
        Livro livro10 = new Livro(null, "O Hobbit", 3, autor05, 2019, editora05, "9788533613381", Condicao.CONSERVADO, sebo02);
        Livro livro11 = new Livro(null, "Capitães da Areia", 2, autor03, 2018, editora04, "9788535921118", Condicao.CONSERVADO, sebo02);
        Livro livro12 = new Livro(null, "Memórias Póstumas de Brás Cubas", 4, autor03, 2019, editora04, "9788535922222", Condicao.CONSERVADO, sebo02);
        Livro livro13 = new Livro(null, "Crime e Castigo", 2, autor07, 2017, editora06, "9788571648502", Condicao.CONSERVADO, sebo02);

        Cliente cliente01 = new Cliente(null, "Marcos", "marcos@gmail.com", encoder.encode("123"));
        Cliente cliente02 = new Cliente(null, "Ana Paula", "ana.paula@gmail.com", encoder.encode("123"));
        Cliente cliente03 = new Cliente(null, "João Pedro", "joao.pedro@gmail.com", encoder.encode("123"));
        Cliente cliente04 = new Cliente(null, "Lucas Silva", "lucas.silva@gmail.com", encoder.encode("123"));
        Cliente cliente05 = new Cliente(null, "Beatriz Santos", "beatriz.santos@gmail.com", encoder.encode("123"));

        seboRepository.saveAll(Arrays.asList(
                sebo01, sebo02, sebo03,
                sebo04, sebo05));

        autorRepository.saveAll(Arrays.asList(
                autor01, autor02, autor03, autor04,
                autor05, autor06, autor07, autor08));

        editoraRepository.saveAll(Arrays.asList(
                editora01, editora02, editora03,
                editora04, editora05, editora06));

        livroRepository.saveAll(Arrays.asList(
                livro01, livro02, livro03, livro04,
                livro05, livro06, livro07, livro08,
                livro09, livro10, livro11, livro12,
                livro13));

        clienteRepository.saveAll(Arrays.asList(
                cliente01, cliente02,
                cliente03, cliente04,
                cliente05));
    }
}
