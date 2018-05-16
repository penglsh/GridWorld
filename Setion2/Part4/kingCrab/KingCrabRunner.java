package kingCrab;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class KingCrabRunner {
	private KingCrabRunner() {
		
	}
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
	    
	    world.add(new Location(3, 5), new Rock());
	    world.add(new Location(5, 4), new Rock());
	    world.add(new Location(3, 4), new Rock());
	    world.add(new Location(7, 3), new Rock());
	    world.add(new Location(3, 6), new Flower());
	    world.add(new Location(4, 3), new Critter());
	    world.add(new Location(4, 5), new KingCrab());
	    world.add(new Location(0, 0), new Flower());
	    world.add(new Location(1, 2), new Rock());
	    world.add(new Location(1, 0), new Flower());
	    world.add(new Location(1, 1), new KingCrab());
	    world.show();
	}
	
}
