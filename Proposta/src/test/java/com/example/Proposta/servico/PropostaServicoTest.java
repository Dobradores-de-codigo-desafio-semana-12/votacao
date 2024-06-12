package com.example.Proposta.servico;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.excecao.PropostaJaDesativadaException;
import com.example.Proposta.excecao.PropostaJaExisteException;
import com.example.Proposta.excecao.PropostaNaoEncontradoException;
import com.example.Proposta.repositorio.PropostaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class PropostaServicoTest {
    @Spy
    private PropostaRepositorio propostaRepositorio;

    @InjectMocks
    private PropostaServico propostaServico;

    @Test
    @Transactional
    public void testSalvarProposta() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Nova Proposta");
        when(propostaRepositorio.save(any(Proposta.class))).thenReturn(proposta);
        Proposta propostaSalva = propostaServico.salvar(proposta);
        assertEquals(proposta, propostaSalva);
        verify(propostaRepositorio, times(1)).save(proposta);
    }

    @Test
    @Transactional
    public void testSalvarProposta_DeveLancarPropostaJaExisteException() {
        Proposta proposta = new Proposta();
        proposta.setTitulo("Proposta Existente");
        when(propostaRepositorio.save(any(Proposta.class))).thenThrow(new DataIntegrityViolationException(""));
        assertThrows(PropostaJaExisteException.class, () -> propostaServico.salvar(proposta));
    }

    @Test
    public void buscarPorId_DeveRetornarProposta_QuandoIdExistir() {
        Long id = 1L;
        Proposta proposta = new Proposta();
        proposta.setId(id);
        proposta.setTitulo("Proposta Existente");
        when(propostaRepositorio.findById(id)).thenReturn(Optional.of(proposta));
        Proposta resultado = propostaServico.buscarPorId(id);
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Proposta Existente", resultado.getTitulo());
    }

    @Test
    public void buscarPorId_DeveLancarPropostaNaoEncontradoException_QuandoIdNaoExistir() {
        Long id = 1L;
        when(propostaRepositorio.findById(id)).thenReturn(Optional.empty());
        assertThrows(PropostaNaoEncontradoException.class, () -> propostaServico.buscarPorId(id));
    }

    @Test
    public void desabilitarProposta_DeveDesabilitarProposta_QuandoIdExistir() {
        Long id = 1L;
        Proposta proposta = new Proposta();
        proposta.setId(id);
        proposta.setTitulo("Proposta Ativa");
        proposta.setAtivo(true);
        when(propostaRepositorio.findById(id)).thenReturn(Optional.of(proposta));
        propostaServico.desabilitarProposta(id);
        assertFalse(proposta.getAtivo());
    }

    @Test
    public void desabilitarProposta_DeveLancarPropostaJaDesativadaException_QuandoPropostaJaEstiverDesativada() {
        Long id = 1L;
        Proposta proposta = new Proposta();
        proposta.setId(id);
        proposta.setTitulo("Proposta Desativada");
        proposta.setAtivo(false);
        when(propostaRepositorio.findById(id)).thenReturn(Optional.of(proposta));
        assertThrows(PropostaJaDesativadaException.class, () -> propostaServico.desabilitarProposta(id));
    }

    @Test
    public void desabilitarProposta_DeveLancarPropostaNaoEncontradoException_QuandoIdNaoExistir() {
        Long id = 1L;
        when(propostaRepositorio.findById(id)).thenReturn(Optional.empty());
        assertThrows(PropostaNaoEncontradoException.class, () -> propostaServico.desabilitarProposta(id));
    }

    @Test
    public void buscarTodos_DeveRetornarListaDePropostas() {
        Proposta proposta1 = new Proposta();
        proposta1.setId(1L);
        proposta1.setTitulo("Proposta 1");
        proposta1.setAtivo(true);
        Proposta proposta2 = new Proposta();
        proposta2.setId(2L);
        proposta2.setTitulo("Proposta 2");
        proposta2.setAtivo(true);
        List<Proposta> propostas = Arrays.asList(proposta1, proposta2);
        when(propostaRepositorio.findAll()).thenReturn(propostas);
        List<Proposta> resultado = propostaServico.buscarTodos();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Proposta 1", resultado.get(0).getTitulo());
        assertEquals("Proposta 2", resultado.get(1).getTitulo());
    }


    @Test
    public void buscarTituloPorId_DeveRetornarTitulo_QuandoIdExistir() {
        Long id = 1L;
        Proposta proposta = new Proposta();
        proposta.setId(id);
        proposta.setTitulo("Proposta Existente");
        when(propostaRepositorio.findById(id)).thenReturn(Optional.of(proposta));
        String titulo = propostaServico.buscarTituloPorId(id);
        assertNotNull(titulo);
        assertEquals("Proposta Existente", titulo);
    }

    @Test
    public void buscarTituloPorId_DeveLancarPropostaNaoEncontradoException_QuandoIdNaoExistir() {
        Long id = 1L;
        when(propostaRepositorio.findById(id)).thenReturn(Optional.empty());
        assertThrows(PropostaNaoEncontradoException.class, () -> propostaServico.buscarTituloPorId(id));
    }
}
