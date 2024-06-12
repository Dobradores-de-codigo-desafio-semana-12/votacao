package com.example.Proposta.web.controle;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.servico.PropostaServico;
import com.example.Proposta.web.controle.PropostaControle;
import com.example.Proposta.web.dto.PropostaCriarDto;
import com.example.Proposta.web.dto.PropostaRespostaDto;
import com.example.Proposta.web.dto.mapa.PropostaMapa;
import com.example.Proposta.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropostaControleTest {

    @InjectMocks
    private PropostaControle propostaControle;

    @Mock
    private PropostaServico propostaServico;

    @Test
    void cadastrarProposta_Sucesso() {

        PropostaCriarDto dto = new PropostaCriarDto();
        Proposta proposta = new Proposta();
        when(propostaServico.salvar(PropostaMapa.paraProposta(dto))).thenReturn(proposta);

        ResponseEntity<PropostaRespostaDto> response = propostaControle.cadastrarProposta(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void cadastrarProposta_ErroValidacao() {

        PropostaCriarDto dto = new PropostaCriarDto();
        when(propostaServico.salvar(PropostaMapa.paraProposta(dto))).thenThrow(new RuntimeException("Erro de validação"));

        ResponseEntity<?> response = propostaControle.cadastrarProposta(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorMessage errorMessage = (ErrorMessage) response.getBody();
        assertNotNull(errorMessage);
        assertEquals("Erro de validação", errorMessage.getMessage());
    }


    @Test
    void cadastrarProposta_ErroInterno() {

        PropostaCriarDto dto = new PropostaCriarDto();
        when(propostaServico.salvar(PropostaMapa.paraProposta(dto))).thenThrow(new RuntimeException("Erro interno no servidor"));

        ResponseEntity<PropostaRespostaDto> response = propostaControle.cadastrarProposta(dto);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void buscarProposta_Sucesso() {

        Long id = 1L;
        Proposta proposta = new Proposta();
        when(propostaServico.buscarPorId(id)).thenReturn(proposta);

        ResponseEntity<PropostaRespostaDto> response = propostaControle.buscarProposta(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void buscarProposta_NaoEncontrado() {

        Long id = 1L;
        when(propostaServico.buscarPorId(id)).thenReturn(null);

        ResponseEntity<PropostaRespostaDto> response = propostaControle.buscarProposta(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void desabilitarProposta_Sucesso() {

        Long id = 1L;
        propostaServico.desabilitarProposta(id);

        ResponseEntity<Void> response = propostaControle.desabilitarProposta(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void desabilitarProposta_Erro() {

        Long id = 1L;
        doThrow(new RuntimeException("Erro ao desabilitar proposta")).when(propostaServico).desabilitarProposta(id);

        try {
            propostaControle.desabilitarProposta(id);
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Erro ao desabilitar proposta", e.getMessage());
        }
    }

    @Test
    void getAll_Sucesso() {

        List<Proposta> propostas = Arrays.asList(new Proposta(), new Proposta());
        when(propostaServico.buscarTodos()).thenReturn(propostas);

        ResponseEntity<List<PropostaRespostaDto>> response = propostaControle.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAll_ErroInterno() {

        when(propostaServico.buscarTodos()).thenThrow(new RuntimeException("Erro interno no servidor"));

        try {
            propostaControle.getAll();
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Erro interno no servidor", e.getMessage());
        }
    }
}
