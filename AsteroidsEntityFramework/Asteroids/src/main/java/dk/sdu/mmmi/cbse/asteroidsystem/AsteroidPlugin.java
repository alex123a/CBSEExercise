package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        for (int i = 0; i < 2; i++) {
            asteroid = createAstroidShip(gameData);
            world.addEntity(asteroid);
        }
    }

    private Entity createAstroidShip(GameData gameData) {
        Random random = new Random();

        float deacceleration = 10;
        float acceleration = 40;
        float maxSpeed = 60;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() - 50 - random.nextInt(400);
        float y = gameData.getDisplayHeight();
        // I add PI so it turn 180 degrees and go down instead of up.
        float radians = 3.1415f / 2 + 3.1415f;
        float radius = 30.0f;

        Entity asteroid = new Asteroid();
        // TODO Add life part
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        asteroid.setRadius(30.0f);
        float[] newShapeX = new float[16];
        float[] newShapeY = new float[16];
        asteroid.setShapeX(newShapeX);
        asteroid.setShapeY(newShapeY);
        asteroid.add(new LifePart(100, 30));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }

}
