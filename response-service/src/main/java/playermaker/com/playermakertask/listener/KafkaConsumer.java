package playermaker.com.playermakertask.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersResponse;
import playermaker.com.playermakertask.service.ResponseService;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ResponseService responseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "player.top.calculated", groupId = "response-service")
    public void consume(String message) {
        log.info("Received message: {}", message);

        try {
            TopPlayersResponse response = objectMapper.readValue(message, TopPlayersResponse.class);
            responseService.processResponse(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing message: {}", message, e);
        }
    }
}
