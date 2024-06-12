package com.example.Resultado.configuracao;


import com.example.Resultado.dto.VotoRespostaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StringConsumidorConfig {

    private final KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, VotoRespostaDto> factoryConsumidor() {
        var configs =  new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonSerializer.class.getName());
        configs.put(JsonDeserializer.VALUE_DEFAULT_TYPE, VotoRespostaDto.class.getName());
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(VotoRespostaDto.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VotoRespostaDto> factoryContainerListenerKafka(
            ConsumerFactory<String, VotoRespostaDto> factoryConsumidor
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, VotoRespostaDto>();
        factory.setConsumerFactory(factoryConsumidor);
        return factory;
    }

}
