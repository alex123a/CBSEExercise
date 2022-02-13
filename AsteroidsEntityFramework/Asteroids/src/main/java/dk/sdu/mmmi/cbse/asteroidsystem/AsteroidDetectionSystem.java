package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.bulletsystem.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Random;

public class AsteroidDetectionSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Checking if asteroid get hits by bullet
        for (Entity asteroid: world.getEntities(Asteroid.class)) {
            PositionPart asteroidPosition = asteroid.getPart(PositionPart.class);
            for (Entity bullet: world.getEntities(Bullet.class)) {
                PositionPart bulletPosition = bullet.getPart(PositionPart.class);
                if (Math.sqrt(Math.pow(asteroidPosition.getX() - bulletPosition.getX(), 2) +
                        Math.pow(asteroidPosition.getY() - bulletPosition.getY(), 2)) <= bullet.getRadius() + asteroid.getRadius()) {
                    world.removeEntity(bullet);
                    if (asteroid.getRadius() == 30.0f) {
                        for (int i = 0; i < 2; i++) {
                            float deacceleration = 10;
                            float acceleration = 40;
                            float maxSpeed = 60;
                            float rotationSpeed = 5;
                            float x = asteroidPosition.getX() - 15 + (30 * i);
                            float y = asteroidPosition.getY();
                            // I add PI so it turn 180 degrees and go down instead of up.
                            float radians = 3.1415f / 2 + 3.1415f;
                            float radius = 15.0f;

                            Entity newAsteroid = new Asteroid();
                            newAsteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
                            newAsteroid.add(new PositionPart(x, y, radians));
                            newAsteroid.setRadius(radius);
                            float[] newShapeX = new float[16];
                            float[] newShapeY = new float[16];
                            newAsteroid.setShapeX(newShapeX);
                            newAsteroid.setShapeY(newShapeY);
                            newAsteroid.add(new LifePart(100, 30));
                            world.addEntity(newAsteroid);
                        }
                    }
                    world.removeEntity(asteroid);
                }
            }
        }
    }
}
