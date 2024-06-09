package com.example.Proposta.web.dto.mapa;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.servico.PropostaServico;
import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import org.modelmapper.ModelMapper;


public class VotoMapa {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Voto paraVoto(VotoCriarDto dto, PropostaServico propostaServico){
        Voto voto = new Voto();
        voto.setPropostaTitulo(propostaServico.buscarPorId(dto.getPropostaId()));
        return modelMapper.map(voto, Voto.class);
    }
    public static VotoRespostaDto paradto(Voto voto, int aprovar, int rejeitar){
        VotoRespostaDto dto = modelMapper.map(voto, VotoRespostaDto.class);
        dto.setPropostaTitulo(voto.getPropostaTitulo().getTitulo());
        dto.setAprovar(aprovar);
        dto.setRejeitar(rejeitar);
        return dto;
    }
}
