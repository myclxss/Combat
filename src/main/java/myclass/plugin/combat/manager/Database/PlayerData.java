package myclass.plugin.combat.manager.Database;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import myclass.plugin.combat.API;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class PlayerData {

    private final UUID uuid;
    private String name;
    private int kills;
    private int deaths;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        kills = 0;
        deaths = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKills(){
        return kills;
    }

    public void addKill(){
        kills++;
    }

    public void setKills(int kills){
        this.kills = kills;
    }

    public int getDeaths(){
        return deaths;
    }

    public void addDeath(){
        deaths++;
    }

    public void setDeaths(int deaths){
        this.deaths = deaths;
    }

    public void load(){
        Document document = API.getInstance().getDatabaseConection().getCollection().find(Filters.eq("UUID", uuid.toString())).first();

        if (document == null) return;

        deaths = document.getInteger("deaths");
        kills = document.getInteger("kills");
    }
    public void save(){
        Document document = new Document();

        document.put("UUID", uuid.toString());
        document.put("name", name);
        document.put("deaths", deaths);
        document.put("kills", kills);

        API.getInstance().getDatabaseConection().getCollection().replaceOne(Filters.eq("UUID", uuid.toString()), document, new ReplaceOptions().upsert(true));

    }
}
