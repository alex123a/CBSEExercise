package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * Precondition: Game has started and world exist
     * Postcondition: Updates an entity in the world.
     */
    void process(GameData gameData, World world);
}
