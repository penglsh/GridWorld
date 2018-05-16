/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */
import org.junit.Test;
import junit.framework.Assert;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class JumperTest {
	private ActorWorld world = new ActorWorld();
	/*
	 *t
	 * Test for canJump method
	 */
	@Test
	public void testJumperCanJump(){
		Jumper jumper = new Jumper();
		world.add(new Location(2, 3), jumper);
		world.add(new Location(0, 3), new Rock());
		Assert.assertEquals(false, jumper.canJump());
	}
	
	/*
	 *t
	 * Test for jump method
	 */
	@Test
	public void testJumperJump(){
		Jumper jumper = new Jumper();
		world.add(new Location(2, 3), jumper);
		world.add(new Location(1, 3), new Rock());
		jumper.jump();	
		Grid<Actor> grid = world.getGrid();
		Actor actor = grid.get(new Location(2, 3));
		Actor actor1 = grid.get(new Location(0, 3));
		Assert.assertEquals(null, actor);
		Assert.assertEquals(jumper, actor1);
	}
	
	/*
	 *t
	 * Test for canturn
	 */
	@Test
	public void testTurn() {
		Jumper jumper = new Jumper();
		world.add(new Location(2, 3), jumper);
		jumper.turn();
		Assert.assertEquals(Location.NORTHEAST, jumper.getDirection());
	}	
}