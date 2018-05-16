package blusterCritter;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class BlusterCritterRunner {
    private BlusterCritterRunner() {
        
    }
    public static void main(String[] args) {
    	ActorWorld world = new ActorWorld();
    	world.add(new Location(7, 8), new BlusterCritter(0));
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new BlusterCritter(1));
        world.add(new Location(5, 5), new BlusterCritter(2));
        world.add(new Location(1, 5), new BlusterCritter(3));
        world.add(new Location(7, 2), new Rock());
        world.add(new Location(4, 4), new BlusterCritter(4));
        world.show();
    }
}
