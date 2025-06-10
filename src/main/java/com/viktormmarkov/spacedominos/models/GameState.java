package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class GameState {
    private String gameId;
    private Player[] players;
    private String currentPlayerId;
    private String[] playerOrder;
    private GameTile[] gameTiles;
    private GameTile[] draftTiles;
    private GamePhaseEnum gamePhase;
    private HashMap<String, Board> playerBoards;
    private int draftTilesCount;
    private HashMap<String, Integer> playerDraftPicks;
    private int turnCounter = 0;

    public GameState(String gameId, Player[] players, GameTile[] gameTiles, int draftTilesCount) {
        this.gameId = gameId;
        this.players = players;
        this.gameTiles = gameTiles;
        this.draftTilesCount = draftTilesCount;
        this.draftTiles = new GameTile[draftTilesCount];
        this.gamePhase = GamePhaseEnum.CHOOSE_TILES;
        this.playerBoards = new HashMap<>();
        this.playerDraftPicks = new HashMap<>();
        this.initPlayerOrder();
        // todo: init player boards
    }

    private void initPlayerOrder() {
        if(players == null || players.length == 0) {
            return;
        }
        Player[] playersCopy = players.clone();
        this.playerOrder = new String[players.length];
        for (int i = 0; i < playersCopy.length; i++) {
            int randomIndex = (int) (Math.random() * playersCopy.length);
            Player temp = playersCopy[randomIndex];
            this.playerOrder[i] = temp.getId();
            //drop player from playersCopy to avoid duplicates
        }
        this.currentPlayerId = playerOrder[0];
    }

    private void initPlayerBoards() {
        for (Player player : players) {
            Board board = new Board(5, 5);
            board.initPlayerCastle();
            playerBoards.put(player.getId(), board);
        }
    }

    public GameTile[] getNewDraftTiles() {
        GameTile[] newDraftItems = new GameTile[draftTilesCount];
        for (int i = 0; i < draftTilesCount; i++) {
            int randomIndex = (int) (Math.random() * gameTiles.length);
            GameTile randomTile = gameTiles[randomIndex];
            // newDraftItems.push(randomTile);
            // remove title from gameTiles to avoid duplicates
        }
        // should be sorted by GameTile number

        this.setDraftTiles(newDraftItems);
        return newDraftItems;
    }

    public void pickDraftOption(int draftOptionIndex, String playerId) {
        if (draftOptionIndex < 0 || draftOptionIndex >= draftTilesCount) {
            throw new IllegalArgumentException("Invalid draft option index");
        }
        if (!playerDraftPicks.containsKey(playerId)) {
            playerDraftPicks.put(playerId, draftOptionIndex);
            // add check for last player to change game phase
        } else {
            throw new IllegalStateException("Draft option already picked");
        }
    }


    public void wipeDraftOptions() {
        playerDraftPicks.clear();
    }
}
