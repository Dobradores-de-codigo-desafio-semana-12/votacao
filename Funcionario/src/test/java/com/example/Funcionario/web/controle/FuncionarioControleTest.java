package com.example.Funcionario.web.controle;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.servico.FuncionarioServico;
import com.example.Funcionario.web.controle.FuncionarioControle;
import com.example.Funcionario.web.dto.FuncionarioCriarDto;
import com.example.Funcionario.web.dto.FuncionarioEmailDto;
import com.example.Funcionario.web.dto.FuncionarioRespostaDto;
import com.example.Funcionario.web.dto.mapa.FuncionarioMapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FuncionarioControleTest {

    @Autowired
    private Environment environment;

    @Test
    void testProperty() {
        String property = environment.getProperty("test.property");
        assertEquals("hello-world", property);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioServico funcionarioServico;

    // @InjectMocks
    private FuncionarioControle funcionarioControle;

    @BeforeEach
    public void setup() {
        //MockitoAnnotations.openMocks(this);
        funcionarioControle = new FuncionarioControle(funcionarioServico);
        assertNotNull(funcionarioServico);
    }

    @Test
    void testCadastrarFuncionario() {
        FuncionarioCriarDto dto = new FuncionarioCriarDto();
        dto.setNome("John Doe");
        dto.setEmail("john.doe@example.com");

        Funcionario fun = new Funcionario();
        fun.setId(1L);
        fun.setNome("John Doe");
        fun.setEmail("john.doe@example.com");

        when(funcionarioServico.salvar(FuncionarioMapa.paraFuncionario(dto))).thenReturn(fun);

        ResponseEntity<FuncionarioRespostaDto> response = funcionarioControle.cadastrarFuncionario(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("john.doe@example.com", response.getBody().getEmail());
    }

    @Test
    void testBuscarFuncionario() {

        Long id = 1L;
        Funcionario fun = new Funcionario();
        fun.setId(id);
        fun.setNome("John Doe");
        fun.setEmail("john.doe@example.com");

        when(funcionarioServico.buscarPorId(id)).thenReturn(fun);

        ResponseEntity<FuncionarioRespostaDto> response = funcionarioControle.buscarFuncionario(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("john.doe@example.com", response.getBody().getEmail());
    }

    @Test
    void testAtualizarEmail() {

        Long id = 1L;
        FuncionarioEmailDto dto = new FuncionarioEmailDto();
        dto.setNovoEmail("john.doe.new@example.com");

        Funcionario fun = new Funcionario();
        fun.setId(id);
        fun.setNome("John Doe");
        fun.setEmail("john.doe.new@example.com");

        when(funcionarioServico.editarEmail(id, dto.getNovoEmail())).thenReturn(fun);

        ResponseEntity<FuncionarioRespostaDto> response = funcionarioControle.atualizarEmail(id, dto);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("john.doe.new@example.com", response.getBody().getEmail());
    }

    @Test
    void testDesabilitarFuncionario() {

        Long id = 1L;

        ResponseEntity<Void> response = funcionarioControle.desabilitarFuncionario(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAll() {

        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario(1L, "John Doe", "john.doe@example.com", true),
                new Funcionario(2L, "Jane Doe", "jane.doe@example.com", true)
        );

        when(funcionarioServico.buscarTodos()).thenReturn(funcionarios);

        ResponseEntity<List<FuncionarioRespostaDto>> response = funcionarioControle.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}
