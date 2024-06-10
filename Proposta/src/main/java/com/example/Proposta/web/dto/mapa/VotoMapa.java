package com.example.Proposta.web.dto.mapa;
import com.example.Proposta.cliente.ClienteFuncionario;
import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.servico.PropostaServico;
import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@NoArgsConstructor
public class VotoMapa {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Voto paraVoto(VotoCriarDto dto, PropostaServico propostaServico, ClienteFuncionario funcionario){
        Voto voto = new Voto();
        Proposta proposta = propostaServico.buscarPorId(dto.getPropostaId());
        voto.setPropostaTitulo(proposta);
        voto.setFuncionarioId(funcionario.pegarFuncionario(dto.getFuncionarioId()).id());
        return modelMapper.map(voto, Voto.class);
    }
    public static VotoRespostaDto paraDto(Voto voto, int aprovar, int rejeitar){
        VotoRespostaDto dto = modelMapper.map(voto, VotoRespostaDto.class);
        dto.setPropostaTitulo(voto.getPropostaTitulo().getTitulo());
        dto.setAprovar(aprovar);
        dto.setRejeitar(rejeitar);
        return dto;
    }
}
