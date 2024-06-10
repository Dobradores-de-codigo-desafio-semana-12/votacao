package com.example.Proposta.web.dto.mapa;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.web.dto.PropostaCriarDto;
import com.example.Proposta.web.dto.PropostaRespostaDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PropostaMapa {

    public static Proposta paraProposta(PropostaCriarDto dto){
        return new ModelMapper().map(dto, Proposta.class);
    }
    public static PropostaRespostaDto paraDto(Proposta proposta){
        return new ModelMapper().map(proposta, PropostaRespostaDto.class);
    }
    public static List<PropostaRespostaDto> paraListaDto(List<Proposta> proposta){
        return proposta.stream().map(pro -> paraDto(pro)).collect(Collectors.toList());
    }
}
