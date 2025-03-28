package io.github.guilhermerodrigues17.mscreditevaluator.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.guilhermerodrigues17.mscreditevaluator.domain.model.CardIssuanceSolicitationData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceSolicitationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuance;

    public void requestCard(CardIssuanceSolicitationData data) throws JsonProcessingException {
        String json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueCardIssuance.getName(),json);
    }

    private String convertIntoJson(CardIssuanceSolicitationData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}
