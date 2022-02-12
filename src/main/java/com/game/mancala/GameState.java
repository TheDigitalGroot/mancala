package com.game.mancala;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Data
public class GameState {
    private final Map<Integer, Integer> pitMap;

    private Player nextPlayer;
    private Player winningPlayer;

    public GameState() {
        pitMap = new TreeMap<>();
        initPitMappings();
        nextPlayer = Player.ONE;
    }

    private void initPitMappings() {
        IntStream.range(1, 15).forEach(pitIndex ->
        {
            if (pitIndex == 7 || pitIndex == 14)
                pitMap.put(pitIndex, 0);
            else pitMap.put(pitIndex, 6);

        });
    }

    public Map<Integer, Integer> getPitMap() {
        return pitMap;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }
}
