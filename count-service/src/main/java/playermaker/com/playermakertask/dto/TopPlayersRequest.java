package playermaker.com.playermakertask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopPlayersRequest {
    private int requiredTopPlayers;
    private List<List<String>> participatedPlayers;
}