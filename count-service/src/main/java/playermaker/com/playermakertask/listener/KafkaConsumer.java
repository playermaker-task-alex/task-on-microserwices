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
import playermaker.com.playermakertask.service.CountService;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private CountService countService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String OUTPUT_TOPIC = "player.participation.counted";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "player.participation.cleaned", groupId = "count-service")
    public void consume(String message) {

        try {
            TopPlayersRequest request = objectMapper.readValue(message, TopPlayersRequest.class);
            TopPlayersResponse response = countService.countParticipations(request);

            String responseMessage = objectMapper.writeValueAsString(response);
            kafkaTemplate.send(OUTPUT_TOPIC, responseMessage);
        } catch (JsonProcessingException e) {
            log.error("Error processing message: {}", message, e);
        }
    }
}
