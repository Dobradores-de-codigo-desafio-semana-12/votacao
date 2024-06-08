package com.example.Funcionario.web.dto.mapa;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.web.dto.FuncionarioCriarDto;
import com.example.Funcionario.web.dto.FuncionarioRespostaDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioMapa {

    public static Funcionario paraFuncionario(FuncionarioCriarDto dto){
        return new ModelMapper().map(dto, Funcionario.class);
    }
    public static FuncionarioRespostaDto paraDto(Funcionario funcionario){
        return new ModelMapper().map(funcionario, FuncionarioRespostaDto.class);
    }

    public static List<FuncionarioRespostaDto> paraListaDto(List<Funcionario> funcionarios){
        return funcionarios.stream().map(fun -> paraDto(fun)).collect(Collectors.toList());
    }
}
