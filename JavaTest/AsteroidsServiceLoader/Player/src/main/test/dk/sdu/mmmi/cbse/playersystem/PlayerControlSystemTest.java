package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import static org.junit.Assert.*;

public class PlayerControlSystemTest {
    World world;
    GameData gameData;
    IGamePluginService playerPlugin;
    IEntityProcessingService playerPrcessing;

    @org.junit.Before
    public void setUp() throws Exception {
        world = new World();
        gameData = new GameData();
        playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void process() {

    }
}