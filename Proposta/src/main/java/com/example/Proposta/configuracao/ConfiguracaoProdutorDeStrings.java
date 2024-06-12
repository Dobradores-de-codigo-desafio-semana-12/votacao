package com.example.Proposta.configuracao;

import com.example.Proposta.web.dto.VotoRespostaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@RequiredArgsConstructor
@Configuration
public class ConfiguracaoProdutorDeStrings {

    private final KafkaProperties properties;

    @Bean
    public ProducerFactory<String, VotoRespostaDto> fabricaDeProdutores() {
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, VotoRespostaDto> kafkaTemplate(ProducerFactory<String, VotoRespostaDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}

