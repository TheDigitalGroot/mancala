package com.game.mancala;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main Game Service.
 */
@Service
public class MancalaGameService {
    public static final int PLAYER_ONE_BIGPIT_IDENTIFIER = 7;
    public static final int PLAYER_TWO_BIG_PIT_IDENTIFIER = 14;
    public static final int EMPTY_PIT = 0;
    private GameState gameState;
    private final Map<Integer, Integer> oppositePits;

    public MancalaGameService() {
        gameState = new GameState();
        oppositePits = getOppositePits();
    }

    private Map<Integer, Integer> getOppositePits() {
        Map<Integer, Integer> oppositePitMap = new HashMap<>();
        oppositePitMap.put(1, 13);
        oppositePitMap.put(2, 12);
        oppositePitMap.put(3, 11);
        oppositePitMap.put(4, 10);
        oppositePitMap.put(5, 9);
        oppositePitMap.put(6, 8);
        Map<Integer, Integer> reverseMap = oppositePitMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        oppositePitMap.putAll(reverseMap);
        oppositePitMap.put(7, null);
        oppositePitMap.put(14, null);
        return oppositePitMap;
    }

    public GameState getGameBoard() {
        return gameState;
    }

    public GameState pickAndFillPits(int startPit, Player player) {
        Map<Integer, Integer> currentPitMap = this.gameState.getPitMap();
        gameState.setWinningPlayer(Player.NONE);
        Integer countOfStones = currentPitMap.get(startPit);
        if (countOfStones > EMPTY_PIT) {
            currentPitMap.put(startPit, EMPTY_PIT);
            int lastPitToFill = getTargetPitToFill(startPit, countOfStones, currentPitMap);
            traversePit(startPit, player, currentPitMap, countOfStones, lastPitToFill);
            findWinningPlayer(currentPitMap);
        }

        return this.gameState;
    }

    private void traversePit(int pitIndex, Player player, Map<Integer, Integer> currentPitMap, Integer countOfStones, int finalPitToFill) {
        int currentPitToFill = 1;
        do {
            int calculatedPitToFill = getTargetPitToFill(pitIndex, currentPitToFill, currentPitMap);
            countOfStones = checkPitAndFillForPlayer(currentPitMap, countOfStones, finalPitToFill, calculatedPitToFill, player);
            currentPitToFill++;
        } while (currentPitToFill <= countOfStones);
    }

    private Integer checkPitAndFillForPlayer(Map<Integer, Integer> currentPitMap, Integer countOfStones, int finalPitToFill, int calculatedPitToFill, Player player) {
        if (isCurrentPitIsForbiddenToFill(calculatedPitToFill, player)) {
            countOfStones++;
        }
        fillPitForPlayer(currentPitMap, calculatedPitToFill, finalPitToFill, player);
        return countOfStones;
    }

    private boolean isCurrentPitIsForbiddenToFill(int calculatedPitToFill, Player player) {
        return (Player.ONE.equals(player) && calculatedPitToFill == PLAYER_TWO_BIG_PIT_IDENTIFIER)
                || (Player.TWO.equals(player) && calculatedPitToFill == PLAYER_ONE_BIGPIT_IDENTIFIER);
    }

    private void fillPitForPlayer(Map<Integer, Integer> currentPitMap, int calculatedPitToFill, Integer finalPitToFill, Player player) {
        if (isDestinationPitEmpty(currentPitMap, finalPitToFill, calculatedPitToFill, getPlayersForbiddenBigPit(player))
                && isPlayerInOwnPitArea(calculatedPitToFill, player)) {
            fillPitWhenEmpty(currentPitMap, calculatedPitToFill, player);
        } else if (isDestinationPitPlayersBigPit(finalPitToFill, calculatedPitToFill, getPlayersBigPit(player))
                && isPlayerInOwnPitArea(calculatedPitToFill, player)) {
            fillBigPit(currentPitMap, player);
        } else {
            fillPit(currentPitMap, calculatedPitToFill, player);
        }
    }

    private void fillPit(Map<Integer, Integer> currentPitMap, int calculatedPitToFill, Player player) {
        if (calculatedPitToFill != getPlayersForbiddenBigPit(player)) {
            currentPitMap.put(calculatedPitToFill, currentPitMap.get(calculatedPitToFill) + 1);
        }
        gameState.setNextPlayer(getNextPlayer(calculatedPitToFill, player));
    }

    private void fillBigPit(Map<Integer, Integer> currentPitMap, Player player) {
        currentPitMap.put(getPlayersBigPit(player), currentPitMap.get(getPlayersBigPit(player)) + 1);
        gameState.setNextPlayer(getPlayerWhenFillingOwnBigPit(player));
    }

    private void fillPitWhenEmpty(Map<Integer, Integer> currentPitMap, int calculatedIndex, Player player) {
        int oppositePitIndex = oppositePits.get(calculatedIndex);
        int oppositePitStoneCount = currentPitMap.get(oppositePitIndex);
        currentPitMap.put(oppositePitIndex, EMPTY_PIT);
        currentPitMap.put(getPlayersBigPit(player), currentPitMap.get(getPlayersBigPit(player)) + 1 + oppositePitStoneCount);
        gameState.setNextPlayer(getPlayerWhenFillingOwnBigPit(player));
    }

    private Player getNextPlayer(int calculatedPitToFill, Player player) {
        if (Player.ONE.equals(player) && calculatedPitToFill != getPlayersBigPit(player))
            return Player.TWO;
        else return Player.ONE;
    }

    private Player getPlayerWhenFillingOwnBigPit(Player player) {
        if (Player.TWO.equals(player)) {
            return Player.TWO;
        } else {
            return Player.ONE;
        }
    }

    private int getPlayersForbiddenBigPit(Player player) {
        if (Player.TWO.equals(player)) {
            return PLAYER_ONE_BIGPIT_IDENTIFIER;
        } else {
            return PLAYER_TWO_BIG_PIT_IDENTIFIER;
        }
    }

    private int getPlayersBigPit(Player player) {
        if (Player.TWO.equals(player)) {
            return PLAYER_TWO_BIG_PIT_IDENTIFIER;
        } else {
            return PLAYER_ONE_BIGPIT_IDENTIFIER;
        }
    }

    private void findWinningPlayer(Map<Integer, Integer> currentPitMap) {
        AtomicInteger firstPlayerEmptyPits = checkPlayerOneStatus(currentPitMap);
        AtomicInteger secondPlayerEmptyPits = checkPlayerTwoStatus(currentPitMap);
        if (firstPlayerEmptyPits.get() == 6 || secondPlayerEmptyPits.get() == 6) {
            gameState.setNextPlayer(Player.NONE);
            if (currentPitMap.get(PLAYER_ONE_BIGPIT_IDENTIFIER) > currentPitMap.get(PLAYER_TWO_BIG_PIT_IDENTIFIER)) {
                gameState.setWinningPlayer(Player.ONE);
            } else if (currentPitMap.get(PLAYER_TWO_BIG_PIT_IDENTIFIER) > currentPitMap.get(PLAYER_ONE_BIGPIT_IDENTIFIER)) {
                gameState.setWinningPlayer(Player.TWO);
            }
        }
    }

    private AtomicInteger checkPlayerOneStatus(Map<Integer, Integer> currentPitMap) {
        AtomicInteger firstPlayerEmptyPits = new AtomicInteger();
        IntStream.range(1, 8).forEach(firstPlayerIndex ->
        {
            if (currentPitMap.get(firstPlayerIndex) == 0) {
                firstPlayerEmptyPits.getAndIncrement();
            }
        });
        return firstPlayerEmptyPits;
    }

    private AtomicInteger checkPlayerTwoStatus(Map<Integer, Integer> currentPitMap) {
        AtomicInteger secondPlayerEmptyPits = new AtomicInteger();
        IntStream.range(8, 15).forEach(secondPlayerIndex ->
        {
            if (currentPitMap.get(secondPlayerIndex) == 0) {
                secondPlayerEmptyPits.getAndIncrement();
            }
        });
        return secondPlayerEmptyPits;
    }

    private boolean isPlayerInOwnPitArea(int calculatedPitToFill, Player player) {
        if (Player.TWO.equals(player)) {
            return calculatedPitToFill >= 8 && calculatedPitToFill <= 14;
        } else {
            return calculatedPitToFill >= 1 && calculatedPitToFill <= 7;
        }
    }

    private boolean isDestinationPitPlayersBigPit(Integer finalPitToFill, int calculatedPitToFill, Integer playersPit) {
        return finalPitToFill == calculatedPitToFill && calculatedPitToFill == playersPit;
    }

    private boolean isDestinationPitEmpty(Map<Integer, Integer> currentPitMap, Integer finalPitToFill, int calculatedPitToFill, Integer forbiddenPit) {
        return finalPitToFill == calculatedPitToFill && currentPitMap.get(calculatedPitToFill) == EMPTY_PIT
                && calculatedPitToFill != forbiddenPit
                && Objects.nonNull(oppositePits.get(calculatedPitToFill));
    }


    private Integer getTargetPitToFill(int pitIndex, int totalPitsToCover, Map<Integer, Integer> currentPitMap) {
        int targetPitToFill;
        if (pitIndex + totalPitsToCover <= currentPitMap.size()) {
            targetPitToFill = pitIndex + totalPitsToCover;
        } else {
            targetPitToFill = pitIndex + totalPitsToCover - currentPitMap.size();
        }
        return targetPitToFill;
    }

    public void reset() {
        gameState = new GameState();
    }
}
