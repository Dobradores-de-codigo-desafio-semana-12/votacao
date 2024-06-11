package com.example.Proposta.servico;



import com.example.Proposta.cliente.ClienteFuncionario;
import com.example.Proposta.cliente.Funcionario;
import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.excecao.FuncionarioJaVotouException;
import com.example.Proposta.excecao.FuncionarioNaoEncontradoException;
import com.example.Proposta.excecao.PropostaInativadaException;
import com.example.Proposta.repositorio.VotoRepositorio;
import com.example.Proposta.web.dto.PropostaRespostaDto;
import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

@Log4j2
@Getter
@RequiredArgsConstructor
@Service
public class VotoServico {

    private final PropostaServico propostaServico;
    private final VotoRepositorio votoRepositorio;
    private final ClienteFuncionario funcionario;
    private final KafkaTemplate<String, VotoRespostaDto> kafkaTemplate;

    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorId(Long id) {
        Funcionario fun = funcionario.pegarFuncionario(id);
        if (fun == null) {
            throw new FuncionarioNaoEncontradoException(id);
        }
        return fun;
    }

    @Transactional(readOnly = true)
    public void iniciarVotos(Long id) {
        Proposta proposta = propostaServico.buscarPorId(id);
        if (!proposta.getAtivo()) {
            throw new PropostaInativadaException(id);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {  VotoRespostaDto respostaDto = new VotoRespostaDto();
                respostaDto.setPropostaTitulo(propostaServico.buscarTituloPorId(id));
                respostaDto.setAprovar(votoRepositorio.countByVotar(Voto.Votar.APROVAR));
                respostaDto.setRejeitar(votoRepositorio.countByVotar(Voto.Votar.REJEITAR));
                enviarMensagem(respostaDto);
            }
        }, proposta.getTempo() * 60000L);
    }

    @Transactional
    public VotoRespostaDto votar(VotoCriarDto dto){
        if(votoRepositorio.existsByFuncionarioId(dto.getFuncionarioId())){
            throw new FuncionarioJaVotouException(dto.getFuncionarioId());
        }

        Voto voto = new Voto();
        voto.setPropostaTitulo(propostaServico.buscarPorId(dto.getPropostaId()));
        voto.setFuncionarioId(buscarFuncionarioPorId(dto.getFuncionarioId()).id());
        voto.setVotar(dto.getVotar());
        votoRepositorio.save(voto);

        VotoRespostaDto respostaDto = new VotoRespostaDto();
        respostaDto.setPropostaTitulo(voto.getPropostaTitulo().getTitulo());
        respostaDto.setAprovar(votoRepositorio.countByVotar(Voto.Votar.APROVAR));
        respostaDto.setRejeitar(votoRepositorio.countByVotar(Voto.Votar.REJEITAR));

        return respostaDto;
    }
    public void enviarMensagem(VotoRespostaDto dto){
        kafkaTemplate.send("Resultado", dto).whenComplete((sendResult, e) ->
        {
            if(e == null){
                log.info("Mensagem enviada com sucesso: {}", sendResult.getProducerRecord().value().getPropostaTitulo());
                log.info("Participação: {}", sendResult.getProducerRecord().partition());
                log.info("Offset: {}", sendResult.getRecordMetadata().offset());
            }
            else{
                log.error("Erro ao enviar a mensagem", e);
            }
        });
    }

}
