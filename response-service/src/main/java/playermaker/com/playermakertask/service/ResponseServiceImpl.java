package playermaker.com.playermakertask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String OUTPUT_TOPIC = "player.response.final";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void processResponse(TopPlayersResponse response) {

        try {
            String responseMessage = objectMapper.writeValueAsString(response);
            kafkaTemplate.send(OUTPUT_TOPIC, responseMessage);
        } catch (JsonProcessingException e) {
            log.error("Error converting response to JSON", e);
        }
    }
}
