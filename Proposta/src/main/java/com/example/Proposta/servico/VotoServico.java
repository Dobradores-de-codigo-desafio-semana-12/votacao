package com.example.Proposta.servico;



import com.example.Proposta.cliente.ClienteFuncionario;
import com.example.Proposta.cliente.Funcionario;
import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.repositorio.VotoRepositorio;
import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

@Getter
@RequiredArgsConstructor
@Service
public class VotoServico {

    private final PropostaServico propostaServico;
    private final VotoRepositorio votoRepositorio;
    private final ClienteFuncionario funcionario;

    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorId(Long id) {
        Funcionario fun = funcionario.pegarFuncionario(id);
        return fun;
    }


    @Transactional(readOnly = true)
    public void iniciarVotos (Long id){
        Proposta proposta = new Proposta();
        propostaServico.buscarPorId(id);
        if(!proposta.getAtivo()){
            throw new RuntimeException(String.format("Proposta inativa"));
        }
        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, proposta.getTempo()*60000L);
    }
    @Transactional
    public VotoRespostaDto votar(VotoCriarDto dto){
        if(votoRepositorio.existsByFuncionarioId(dto.getFuncionarioId())){
            throw new RuntimeException("Funcionario já votou");
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

}
