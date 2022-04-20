package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.List;

public interface IBulletService {
    void fire(GameData gameData, World world, String whoFiredID);
    List<Entity> getBullets(GameData gameData, World world);
}
