package playermaker.com.playermakertask.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import playermaker.com.playermakertask.dto.TopPlayersRequest;
import playermaker.com.playermakertask.dto.TopPlayersResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DuplicateRemovalServiceImpl implements DuplicateRemovalService {

    @Override
    public TopPlayersResponse removeDuplicates(TopPlayersRequest request) {
        log.info("Removing duplicates from participatedPlayers");

        List<List<String>> participatedPlayers = request.getParticipatedPlayers();
        Set<List<String>> uniquePlayers = new HashSet<>(participatedPlayers);

        TopPlayersResponse response = new TopPlayersResponse(request.getRequiredTopPlayers(), new ArrayList<>(uniquePlayers));
        return response;
    }
}