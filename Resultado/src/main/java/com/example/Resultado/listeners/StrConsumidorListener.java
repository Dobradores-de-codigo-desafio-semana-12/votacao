package com.example.Resultado.listeners;

;
import com.example.Resultado.dto.VotoRespostaDto;
import com.example.Resultado.repositorio.ResultadoRepositorio;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StrConsumidorListener {

    private final ResultadoRepositorio repositorio;


    @KafkaListener(topics = "Resultado", groupId = "Resultado", containerFactory = "factoryContainerListenerKafka")
    public void consumidor(VotoRespostaDto dto) {
        log.info("Resultado da proposta {}, recebido com sucesso", dto);
        try{
            repositorio.save(dto);
            log.info("Voto {} salvo com sucesso", dto.getPropostaTitulo());
        }catch (Exception e){
            log.error("Erro ao salvar o resultado dos votos para {}", dto.getPropostaTitulo(), e);
        }
    }



}
