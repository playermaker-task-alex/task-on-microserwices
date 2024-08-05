package playermaker.com.playermakertask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerParticipationCount {
    private String playerName;
    private int count;
}
