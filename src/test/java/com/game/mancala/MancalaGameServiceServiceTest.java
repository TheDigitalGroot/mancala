package com.game.mancala;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
class MancalaGameServiceServiceTest {
    MancalaGameService game;

    @BeforeEach
    void setUp() {
        game = new MancalaGameService();
    }

    @Test
    void initGameBoard() {
        GameState currentGameState = game.getGameBoard();
        GameState expectedGameState = new GameState();
        Assertions.assertEquals(expectedGameState, currentGameState);
        Assertions.assertEquals(Player.ONE, currentGameState.getNextPlayer());
        log.info(String.valueOf(currentGameState.getPitMap()));
    }

    @Test
    void playerOnePicksPit01() {
        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.ONE);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 0);
        expectedPitMap.put(2, 7);
        expectedPitMap.put(3, 7);
        expectedPitMap.put(4, 7);
        expectedPitMap.put(5, 7);
        expectedPitMap.put(6, 7);
        expectedPitMap.put(7, 1);
        log.info(String.valueOf(expectedGameState.getPitMap()));
        GameState newGameState = game.pickAndFillPits(1, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.ONE, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));

        expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 0);
        expectedPitMap.put(2, 0);
        expectedPitMap.put(3, 8);
        expectedPitMap.put(4, 8);
        expectedPitMap.put(5, 8);
        expectedPitMap.put(6, 8);
        expectedPitMap.put(7, 2);
        expectedPitMap.put(8, 7);
        expectedPitMap.put(9, 7);
        log.info(String.valueOf(expectedGameState.getPitMap()));
        newGameState = game.pickAndFillPits(2, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.TWO, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));

    }

    @Test
    void playerTwoPicksPit08() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 0);
        currentPitMap.put(2, 0);
        currentPitMap.put(3, 8);
        currentPitMap.put(4, 8);
        currentPitMap.put(5, 8);
        currentPitMap.put(6, 8);
        currentPitMap.put(7, 2);
        currentPitMap.put(8, 7);
        currentPitMap.put(9, 7);
        currentPitMap.put(10, 6);
        currentPitMap.put(11, 6);
        currentPitMap.put(12, 6);
        currentPitMap.put(13, 6);
        currentPitMap.put(14, 0);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.ONE);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 1);
        expectedPitMap.put(2, 0);
        expectedPitMap.put(3, 8);
        expectedPitMap.put(4, 8);
        expectedPitMap.put(5, 8);
        expectedPitMap.put(6, 8);
        expectedPitMap.put(7, 2);
        expectedPitMap.put(8, 0);
        expectedPitMap.put(9, 8);
        expectedPitMap.put(10, 7);
        expectedPitMap.put(11, 7);
        expectedPitMap.put(12, 7);
        expectedPitMap.put(13, 7);
        expectedPitMap.put(14, 1);

        log.info(String.valueOf(game.getGameBoard().getPitMap()));
        GameState newGameState = game.pickAndFillPits(8, Player.TWO);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.ONE, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }


    @Test
    void playerOneCapturesPit() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 1);
        currentPitMap.put(2, 0);
        currentPitMap.put(3, 8);
        currentPitMap.put(4, 8);
        currentPitMap.put(5, 8);
        currentPitMap.put(6, 8);
        currentPitMap.put(7, 2);
        currentPitMap.put(8, 0);
        currentPitMap.put(9, 8);
        currentPitMap.put(10, 7);
        currentPitMap.put(11, 7);
        currentPitMap.put(12, 7);
        currentPitMap.put(13, 7);
        currentPitMap.put(14, 1);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 0);
        expectedPitMap.put(2, 0);
        expectedPitMap.put(3, 8);
        expectedPitMap.put(4, 8);
        expectedPitMap.put(5, 8);
        expectedPitMap.put(6, 8);
        expectedPitMap.put(7, 10);
        expectedPitMap.put(8, 0);
        expectedPitMap.put(9, 8);
        expectedPitMap.put(10, 7);
        expectedPitMap.put(11, 7);
        expectedPitMap.put(12, 0);
        expectedPitMap.put(13, 7);
        expectedPitMap.put(14, 1);

        log.info(String.valueOf(game.getGameBoard().getPitMap()));

        GameState newGameState = game.pickAndFillPits(1, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.ONE, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }


    @Test
    void playerTwoPicksPit09() {
        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 1);
        expectedPitMap.put(2, 1);
        expectedPitMap.put(3, 9);
        expectedPitMap.put(4, 8);
        expectedPitMap.put(5, 8);
        expectedPitMap.put(6, 8);
        expectedPitMap.put(7, 10);
        expectedPitMap.put(8, 0);
        expectedPitMap.put(9, 0);
        expectedPitMap.put(10, 8);
        expectedPitMap.put(11, 8);
        expectedPitMap.put(12, 1);
        expectedPitMap.put(13, 8);
        expectedPitMap.put(14, 2);

        game.pickAndFillPits(1, Player.ONE);
        game.pickAndFillPits(2, Player.ONE);
        game.pickAndFillPits(8, Player.TWO);
        GameState preGameState = game.pickAndFillPits(1, Player.ONE);
        log.info(String.valueOf(preGameState.getPitMap()));

        GameState newGameState = game.pickAndFillPits(9, Player.TWO);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.ONE, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerOnePicksPit03() {
        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 1);
        expectedPitMap.put(2, 1);
        expectedPitMap.put(3, 0);
        expectedPitMap.put(4, 9);
        expectedPitMap.put(5, 9);
        expectedPitMap.put(6, 9);
        expectedPitMap.put(7, 11);
        expectedPitMap.put(8, 1);
        expectedPitMap.put(9, 1);
        expectedPitMap.put(10, 9);
        expectedPitMap.put(11, 9);
        expectedPitMap.put(12, 2);
        expectedPitMap.put(13, 8);
        expectedPitMap.put(14, 2);

        game.pickAndFillPits(1, Player.ONE);
        game.pickAndFillPits(2, Player.ONE);
        game.pickAndFillPits(8, Player.TWO);
        game.pickAndFillPits(1, Player.ONE);
        GameState preGameState = game.pickAndFillPits(9, Player.TWO);
        log.info(String.valueOf(preGameState.getPitMap()));

        GameState newGameState = game.pickAndFillPits(3, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.TWO, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerTwoPicksPit13() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 1);
        currentPitMap.put(2, 1);
        currentPitMap.put(3, 0);
        currentPitMap.put(4, 9);
        currentPitMap.put(5, 9);
        currentPitMap.put(6, 9);
        currentPitMap.put(7, 11);
        currentPitMap.put(8, 1);
        currentPitMap.put(9, 1);
        currentPitMap.put(10, 9);
        currentPitMap.put(11, 9);
        currentPitMap.put(12, 2);
        currentPitMap.put(13, 8);
        currentPitMap.put(14, 2);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 2);
        expectedPitMap.put(2, 2);
        expectedPitMap.put(3, 1);
        expectedPitMap.put(4, 10);
        expectedPitMap.put(5, 10);
        expectedPitMap.put(6, 10);
        expectedPitMap.put(7, 11);
        expectedPitMap.put(8, 2);
        expectedPitMap.put(9, 1);
        expectedPitMap.put(10, 9);
        expectedPitMap.put(11, 9);
        expectedPitMap.put(12, 2);
        expectedPitMap.put(13, 0);
        expectedPitMap.put(14, 3);

        log.info(String.valueOf(game.getGameBoard().getPitMap()));

        GameState newGameState = game.pickAndFillPits(13, Player.TWO);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.ONE, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerTwoCapturesPit() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 1);
        currentPitMap.put(2, 1);
        currentPitMap.put(3, 5);
        currentPitMap.put(4, 3);
        currentPitMap.put(5, 1);
        currentPitMap.put(6, 2);
        currentPitMap.put(7, 27);
        currentPitMap.put(8, 1);
        currentPitMap.put(9, 2);
        currentPitMap.put(10, 0);
        currentPitMap.put(11, 0);
        currentPitMap.put(12, 5);
        currentPitMap.put(13, 4);
        currentPitMap.put(14, 20);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.NONE);
        expectedGameState.setWinningPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 1);
        expectedPitMap.put(2, 1);
        expectedPitMap.put(3, 0);
        expectedPitMap.put(4, 3);
        expectedPitMap.put(5, 1);
        expectedPitMap.put(6, 2);
        expectedPitMap.put(7, 27);
        expectedPitMap.put(8, 1);
        expectedPitMap.put(9, 0);
        expectedPitMap.put(10, 1);
        expectedPitMap.put(11, 0);
        expectedPitMap.put(12, 5);
        expectedPitMap.put(13, 4);
        expectedPitMap.put(14, 26);

        GameState preGameState = game.getGameBoard();
        log.info(String.valueOf(preGameState.getPitMap()));

        GameState newGameState = game.pickAndFillPits(9, Player.TWO);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.TWO, newGameState.getNextPlayer());
        Assertions.assertEquals(Player.NONE, newGameState.getWinningPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerTwoLandsOnOwnBigPit() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 2);
        currentPitMap.put(2, 1);
        currentPitMap.put(3, 1);
        currentPitMap.put(4, 0);
        currentPitMap.put(5, 10);
        currentPitMap.put(6, 10);
        currentPitMap.put(7, 12);
        currentPitMap.put(8, 2);
        currentPitMap.put(9, 1);
        currentPitMap.put(10, 10);
        currentPitMap.put(11, 10);
        currentPitMap.put(12, 2);
        currentPitMap.put(13, 9);
        currentPitMap.put(14, 2);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.NONE);
        expectedGameState.setWinningPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 2);
        expectedPitMap.put(2, 1);
        expectedPitMap.put(3, 1);
        expectedPitMap.put(4, 0);
        expectedPitMap.put(5, 10);
        expectedPitMap.put(6, 10);
        expectedPitMap.put(7, 12);
        expectedPitMap.put(8, 2);
        expectedPitMap.put(9, 1);
        expectedPitMap.put(10, 10);
        expectedPitMap.put(11, 10);
        expectedPitMap.put(12, 0);
        expectedPitMap.put(13, 10);
        expectedPitMap.put(14, 3);

        GameState preGameState = game.getGameBoard();
        log.info(String.valueOf(preGameState.getPitMap()));

        GameState newGameState = game.pickAndFillPits(12, Player.TWO);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.TWO, newGameState.getNextPlayer());
        Assertions.assertEquals(Player.NONE, newGameState.getWinningPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerOneWins() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 0);
        currentPitMap.put(2, 0);
        currentPitMap.put(3, 0);
        currentPitMap.put(4, 0);
        currentPitMap.put(5, 0);
        currentPitMap.put(6, 6);
        currentPitMap.put(7, 30);
        currentPitMap.put(8, 0);
        currentPitMap.put(9, 2);
        currentPitMap.put(10, 8);
        currentPitMap.put(11, 2);
        currentPitMap.put(12, 4);
        currentPitMap.put(13, 0);
        currentPitMap.put(14, 20);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.NONE);
        expectedGameState.setWinningPlayer(Player.ONE);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 0);
        expectedPitMap.put(2, 0);
        expectedPitMap.put(3, 0);
        expectedPitMap.put(4, 0);
        expectedPitMap.put(5, 0);
        expectedPitMap.put(6, 0);
        expectedPitMap.put(7, 31);
        expectedPitMap.put(8, 1);
        expectedPitMap.put(9, 3);
        expectedPitMap.put(10, 9);
        expectedPitMap.put(11, 3);
        expectedPitMap.put(12, 5);
        expectedPitMap.put(13, 0);
        expectedPitMap.put(14, 20);

        GameState preGameState = game.getGameBoard();
        log.info(String.valueOf(preGameState.getPitMap()));

        GameState newGameState = game.pickAndFillPits(6, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.NONE, newGameState.getNextPlayer());
        Assertions.assertEquals(Player.ONE, newGameState.getWinningPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

    @Test
    void playerOneGoesOverBothBigPits() {
        Map<Integer, Integer> currentPitMap = game.getGameBoard().getPitMap();
        currentPitMap.put(1, 1);
        currentPitMap.put(2, 1);
        currentPitMap.put(3, 1);
        currentPitMap.put(4, 10);
        currentPitMap.put(5, 10);
        currentPitMap.put(6, 10);
        currentPitMap.put(7, 11);
        currentPitMap.put(8, 1);
        currentPitMap.put(9, 9);
        currentPitMap.put(10, 8);
        currentPitMap.put(11, 8);
        currentPitMap.put(12, 0);
        currentPitMap.put(13, 0);
        currentPitMap.put(14, 2);

        GameState expectedGameState = new GameState();
        expectedGameState.setNextPlayer(Player.TWO);
        Map<Integer, Integer> expectedPitMap = expectedGameState.getPitMap();
        expectedPitMap.put(1, 2);
        expectedPitMap.put(2, 2);
        expectedPitMap.put(3, 2);
        expectedPitMap.put(4, 10);
        expectedPitMap.put(5, 10);
        expectedPitMap.put(6, 0);
        expectedPitMap.put(7, 12);
        expectedPitMap.put(8, 2);
        expectedPitMap.put(9, 10);
        expectedPitMap.put(10, 9);
        expectedPitMap.put(11, 9);
        expectedPitMap.put(12, 1);
        expectedPitMap.put(13, 1);
        expectedPitMap.put(14, 2);

        log.info(String.valueOf(game.getGameBoard().getPitMap()));

        GameState newGameState = game.pickAndFillPits(6, Player.ONE);
        Assertions.assertEquals(expectedGameState.getPitMap(), newGameState.getPitMap());
        Assertions.assertEquals(Player.TWO, newGameState.getNextPlayer());
        log.info(String.valueOf(newGameState.getPitMap()));
    }

}
