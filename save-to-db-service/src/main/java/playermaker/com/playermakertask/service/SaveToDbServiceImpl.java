package playermaker.com.playermakertask.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersResponse;
import playermaker.com.playermakertask.entity.TopPlayersEntity;
import playermaker.com.playermakertask.repository.DBRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveToDbServiceImpl implements SaveToDbService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final DBRepository ydbRepository;

    @Override
    public void saveTopPlayers(TopPlayersResponse response) {

        String redisKey = "topPlayers:" + response.getRequiredTopPlayers();
        redisTemplate.opsForValue().set(redisKey, response);
        log.info("Saved top players to Redis with key: {}", redisKey);

        TopPlayersEntity entity = new TopPlayersEntity();
        entity.setRequiredTopPlayers(response.getRequiredTopPlayers());
        entity.setMostParticipatedPlayers(response.getMostParticipatedPlayers());
        ydbRepository.save(entity);
    }
}