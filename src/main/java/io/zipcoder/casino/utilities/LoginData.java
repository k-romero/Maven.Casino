package io.zipcoder.casino.utilities;
import io.zipcoder.casino.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class LoginData {

    ArrayList<Player> playerDataBase;

    public LoginData(){
        playerDataBase = new ArrayList<>();
    }

    public void addPlayerData(Player p){
        playerDataBase.add(p);
    }

    public void updatePlayerData(Player p){
        int index = p.getId()-1;
        playerDataBase.set(index,p);
    }

    public ArrayList<Player> getPlayerDataBase(){
        return playerDataBase;
    }

    public void jsonWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File("playerData.json"), playerDataBase);
    }

    public void jsonLoadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.playerDataBase = objectMapper.readValue(new File("playerData.json"), new TypeReference<ArrayList<Player>>() {
        });
    }


}
