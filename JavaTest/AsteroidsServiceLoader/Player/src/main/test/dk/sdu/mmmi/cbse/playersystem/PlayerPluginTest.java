package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerPluginTest {
    World world;
    GameData gameData;
    IGamePluginService playerPlugin;

    @org.junit.Before
    public void setUp() throws Exception {
        world = new World();
        gameData = new GameData();
        playerPlugin = new PlayerPlugin();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void start() {
        playerPlugin.start(gameData, world);
        Entity player = null;
        for (Entity entity: world.getEntities(Player.class)) {
            player = entity;
            break;
        }
        assertNotNull(player);
    }

    @Test
    public void stop() {
        playerPlugin.start(gameData, world);
        playerPlugin.stop(gameData, world);
        Entity player = null;
        for (Entity entity: world.getEntities(Player.class)) {
            player = entity;
            break;
        }
        assertNull(player);
    }
}