package com.example.Funcionario.servico;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.repositorio.FuncionarioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FuncionarioServicoTest {
    @Spy
    private FuncionarioRepositorio funcionarioRepositorio;

    @InjectMocks
    private FuncionarioServico funcionarioServico;

    @Test
    @Transactional
    public void testSalvarFuncionario() {

        Funcionario fun = new Funcionario();
        fun.setNome("John Doe");
        fun.setEmail("john.doe@example.com");

        when(funcionarioRepositorio.save(any(Funcionario.class))).thenReturn(fun);

        Funcionario funSalvo = funcionarioServico.salvar(fun);

        assertEquals(fun, funSalvo);

        verify(funcionarioRepositorio, times(1)).save(fun);
    }

    @Test
    public void buscarPorId_DeveRetornarFuncionario_QuandoIdExistir() {

        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setEmail("joao@example.com");

        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioServico.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João", resultado.getNome());
        assertEquals("joao@example.com", resultado.getEmail());
    }

    @Test
    public void buscarPorId_DeveLancarRuntimeException_QuandoIdNaoExistir() {

        Long id = 1L;
        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> funcionarioServico.buscarPorId(id));
    }

    @Test
    public void editarEmail_DeveAtualizarEmail_QuandoIdExistir() {

        Long id = 1L;
        String novoEmail = "joao.novo@example.com";
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setEmail("joao@example.com");

        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioServico.editarEmail(id, novoEmail);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João", resultado.getNome());
        assertEquals(novoEmail, resultado.getEmail());
    }

    @Test
    public void editarEmail_DeveLancarRuntimeException_QuandoIdNaoExistir() {
        
        Long id = 1L;
        String novoEmail = "joao.novo@example.com";
        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> funcionarioServico.editarEmail(id, novoEmail));
    }

    @Test
    public void desabilitarFuncionario_DeveDesabilitarFuncionario_QuandoIdExistir() {

        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setEmail("joao@example.com");
        funcionario.setAtivo(true);

        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.of(funcionario));

        funcionarioServico.desabilitarFuncionario(id);

        assertFalse(funcionario.getAtivo());
    }

    @Test
    public void desabilitarFuncionario_DeveLancarRuntimeException_QuandoIdNaoExistir() {
        
        Long id = 1L;
        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> funcionarioServico.desabilitarFuncionario(id));
    }

    @Test
    public void buscarTodos_DeveRetornarListaDeFuncionarios() {
        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario(1L, "João", "joao@example.com", true),
                new Funcionario(2L, "Maria", "maria@example.com", true)
        );

        when(funcionarioRepositorio.findAll()).thenReturn(funcionarios);

        List<Funcionario> resultado = funcionarioServico.buscarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());
    }

    @Test
    public void habilitarFuncionario_DeveHabilitarFuncionario_QuandoIdExistir() {

        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome("João");
        funcionario.setEmail("joao@example.com");
        funcionario.setAtivo(false);

        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.of(funcionario));

        funcionarioServico.habilitarFuncionario(id);

        assertTrue(funcionario.getAtivo());
    }

    @Test
    public void habilitarFuncionario_DeveLancarRuntimeException_QuandoIdNaoExistir() {

        Long id = 1L;
        when(funcionarioRepositorio.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> funcionarioServico.habilitarFuncionario(id));
    }
}
