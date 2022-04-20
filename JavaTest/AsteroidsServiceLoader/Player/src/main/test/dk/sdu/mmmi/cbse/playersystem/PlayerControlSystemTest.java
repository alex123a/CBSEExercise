package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerControlSystemTest {
    World world;
    GameData gameData;
    IGamePluginService playerPlugin;
    IEntityProcessingService playerService;
    IBulletService bullet = null;

    @Before
    public void setUp() throws Exception {
        world = new World();
        gameData = new GameData();
        playerPlugin = new PlayerPlugin();
        playerService = new PlayerControlSystem();
        for (IBulletService bulletService: SPILocator.locateAll(IBulletService.class)) {
            bullet = bulletService;
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void process() {
        // Testing if a new bullet is fired when pressing space bar
        gameData.getKeys().setKey(6, true);
        playerPlugin.start(gameData, world);
        playerService.process(gameData, world);
        List<Entity> bullets = bullet.getBullets(gameData, world);
        assertEquals(bullets.size(), 1);
    }
}