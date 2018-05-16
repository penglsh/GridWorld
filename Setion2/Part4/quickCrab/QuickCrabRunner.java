package quickCrab;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class QuickCrabRunner {
    private QuickCrabRunner() {
        
    }
	public static void main(String[] args)
    {
		
        ActorWorld world = new ActorWorld();
        
        world.add(new Location(7, 5), new Rock());
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(4, 4), new Rock());
        world.add(new Location(4, 6), new Rock());
        world.add(new Location(7, 3), new Rock());
        world.add(new Location(4, 1), new Flower());
        world.add(new Location(3, 5), new Flower());
        world.add(new Location(4, 5), new QuickCrab());
        world.show();
        
    }
}
