package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class PlayerDetectionSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Checking if player is too close to an asteroid
        for (Entity player: world.getEntities(Player.class)) {
            PositionPart playerPosition = player.getPart(PositionPart.class);
            for (Entity asteroid: world.getEntities(Asteroid.class)) {
                PositionPart asteroidPosition = asteroid.getPart(PositionPart.class);
                // 8 is the length of the player
                if (Math.sqrt(Math.pow(playerPosition.getX() - asteroidPosition.getX(), 2) +
                        Math.pow(playerPosition.getY() - asteroidPosition.getY(), 2)) <= 8 + asteroid.getRadius()) {
                    world.removeEntity(player);
                    System.out.println("Player hit asteroid");
                }
            }
        }

    }
}
