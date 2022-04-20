package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import static org.junit.Assert.*;

public class PlayerControlSystemTest {
    World world;
    GameData gameData;
    IGamePluginService playerPlugin;
    IEntityProcessingService playerService;
    Entity entity;
    PositionPart positionPart;
    MovingPart movingPart;

    @Before
    public void setUp() throws Exception {
        gameData = new GameData();
        gameData.setDelta(0.01f);
        gameData.setDisplayHeight(1080);
        gameData.setDisplayWidth(1920);
        world = new World();
        playerPlugin = new PlayerPlugin();
        playerService = new PlayerControlSystem();
        playerPlugin.start(gameData, world);
        entity = world.getEntities(Player.class).get(0);
        movingPart = entity.getPart(MovingPart.class);
        positionPart = entity.getPart(PositionPart.class);
        positionPart.setY(10);
        positionPart.setX(20);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void process() {

    }

    @Test
    public void testOfPlayerMovement() {
        float yBefore = positionPart.getY();
        // Using Delta time to move and I set it to 10 iteration.
        for (int i = 0; i < 10; i++) {
            gameData.getKeys().setKey(UP, true);
            gameData.getKeys().setKey(RIGHT, true);
            movingPart.setUp(gameData.getKeys().isDown(UP));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.process(gameData, entity);
        }
        float yAfter = positionPart.getY();
        System.out.println(yAfter);
        assertNotEquals(yBefore, yAfter);
    }
}