package com.luis.sebolivros.domain.common.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.repository.EditoraRepository;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import com.luis.sebolivros.domain.estoque.repository.EstoqueRepository;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.repository.LivroRepository;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EstoqueRepository estoqueRepository;

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

        Sebo sebo01 = new Sebo(null, "Maravilha", "maravilha@gmail.com", encoder.encode("123"), "01001-000", "12345678000195");
        Sebo sebo02 = new Sebo(null, "Sebo Central", "central@gmail.com", encoder.encode("123"), "20040-020", "98765432000110");
        Sebo sebo03 = new Sebo(null, "Sebo Antiguidades", "antigo@gmail.com", encoder.encode("123"), "30140-110", "11222333000181");
        Sebo sebo04 = new Sebo(null, "Sebo Universitário", "uni@gmail.com", encoder.encode("123"), "70040-010", "55666777000102");
        Sebo sebo05 = new Sebo(null, "Sebo Vintage", "vintage@gmail.com", encoder.encode("123"), "80010-000", "44555666000177");

        Livro livro01 = new Livro(null, "A Odisseia",  autor01, 2014, editora01, "9788563560271");
        Livro livro02 = new Livro(null, "Hamlet", autor02, 2012, editora02, "9788503012304");
        Livro livro03 = new Livro(null, "Dom Casmurro", autor03, 2019, editora04, "9788535925697");
        Livro livro04 = new Livro(null, "1984", autor04, 2009, editora03, "9788535914844");
        Livro livro05 = new Livro(null, "O Senhor dos Anéis: A Sociedade do Anel", autor05, 2019, editora05, "9788533613374");
        Livro livro06 = new Livro(null, "A Hora da Estrela", autor06, 2020, editora04, "9788535929770");
        Livro livro07 = new Livro(null, "A Metamorfose", autor07, 2017, editora06, "9788571648496");
        Livro livro08 = new Livro(null, "Assassinato no Expresso do Oriente", autor06, 2011, editora02, "9788503011345");
        Livro livro09 = new Livro(null, "Ilíada", autor01, 2015, editora01, "9788563560288");
        Livro livro10 = new Livro(null, "O Hobbit", autor05, 2019, editora05, "9788533613381");
        Livro livro11 = new Livro(null, "Capitães da Areia", autor03, 2018, editora04, "9788535921118");
        Livro livro12 = new Livro(null, "Memórias Póstumas de Brás Cubas", autor03, 2019, editora04, "9788535922222");
        Livro livro13 = new Livro(null, "Crime e Castigo", autor07, 2017, editora06, "9788571648502");

        livro01.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/AOdisseia.jpg");
        livro02.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/hamlet.jpg");
        livro03.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/dom-casmurro.jpg");
        livro04.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/1984.jpg");
        livro05.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/senhor-dos-aneis.jpg"); // confere o nome
        livro06.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/AHoradaEstrela.jpg");
        livro07.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/metamorfose.jpg");
        livro08.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/AssassinatonoExpresso.jpg");
        livro09.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/iliada.jpg"); // corrige se necessário
        livro10.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/OHobbit.jpg");
        livro11.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/capitaes-da-areia.jpg"); // precisa existir
        livro12.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/MemoriasPostumas.jpg");
        livro13.setImageUrl("https://utskkhxqkzwawkhnoxmq.supabase.co/storage/v1/object/public/livros/CrimeeCastigo.jpg");

        Estoque estoque01 = new Estoque(null, 55.54, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro01,  sebo01);
        Estoque estoque02 = new Estoque(null, 39.90, 1, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro02, sebo01);
        Estoque estoque03 = new Estoque(null, 29.90, 3, com.luis.sebolivros.domain.estoque.enums.Condicao.USADO, livro03, sebo01);
        Estoque estoque04 = new Estoque(null, 45.00, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro04, sebo01);
        Estoque estoque05 = new Estoque(null, 79.90, 1, com.luis.sebolivros.domain.estoque.enums.Condicao.NOVO, livro05, sebo01);

        Estoque estoque06 = new Estoque(null, 25.50, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.USADO, livro06, sebo03);
        Estoque estoque07 = new Estoque(null, 34.90, 1, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro07, sebo04);
        Estoque estoque08 = new Estoque(null, 42.00, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro08, sebo05);
        Estoque estoque09 = new Estoque(null, 37.80, 1, com.luis.sebolivros.domain.estoque.enums.Condicao.USADO, livro09, sebo05);

        Estoque estoque10 = new Estoque(null, 49.90, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.NOVO, livro10, sebo02);
        Estoque estoque11 = new Estoque(null, 28.75, 3, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro11, sebo02);
        Estoque estoque12 = new Estoque(null, 31.20, 2, com.luis.sebolivros.domain.estoque.enums.Condicao.CONSERVADO, livro12, sebo02);
        Estoque estoque13 = new Estoque(null, 44.60, 1, com.luis.sebolivros.domain.estoque.enums.Condicao.USADO, livro13, sebo02);

        Cliente cliente01 = new Cliente(null, "Marcos", "marcos@gmail.com", encoder.encode("123"), "11144477735");
        Cliente cliente02 = new Cliente(null, "Ana Paula", "ana.paula@gmail.com", encoder.encode("123"), "12345678909");
        Cliente cliente03 = new Cliente(null, "João Pedro", "joao.pedro@gmail.com", encoder.encode("123"), "98765432100");
        Cliente cliente04 = new Cliente(null, "Lucas Silva", "lucas.silva@gmail.com", encoder.encode("123"), "52998224725");
        Cliente cliente05 = new Cliente(null, "Beatriz Santos", "beatriz.santos@gmail.com", encoder.encode("123"), "32165498708");

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

        estoqueRepository.saveAll(Arrays.asList(
                estoque01, estoque02, estoque03, estoque04,
                estoque05, estoque06, estoque07, estoque08,
                estoque09, estoque10, estoque11, estoque12,
                estoque13));

        clienteRepository.saveAll(Arrays.asList(
                cliente01, cliente02,
                cliente03, cliente04,
                cliente05));
    }
}
