package playermaker.com.playermakertask.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;
import playermaker.com.playermakertask.service.DuplicateRemovalService;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private DuplicateRemovalService duplicateRemovalService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String OUTPUT_TOPIC = "player.participation.cleaned";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "player.participation.raw", groupId = "duplicate-removal-service")
    public void consume(String message) {
        log.info("Received message: {}", message);

        try {
            TopPlayersRequest request = objectMapper.readValue(message, TopPlayersRequest.class);
            TopPlayersResponse response = duplicateRemovalService.removeDuplicates(request);

            String responseMessage = objectMapper.writeValueAsString(response);
            kafkaTemplate.send(OUTPUT_TOPIC, responseMessage);
            log.info("Sent cleaned message to topic {}: {}", OUTPUT_TOPIC, responseMessage);
        } catch (JsonProcessingException e) {
            log.error("Error processing message: {}", message, e);
        }
    }
}
