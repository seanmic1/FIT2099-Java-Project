package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.AldrichTheDevourer;
import game.enemies.Chest;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.grounds.Bonfire;
import game.grounds.Cemetery;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.FogDoor;
import game.grounds.Valley;
import game.grounds.Wall;
import game.weapons.StormRuler;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Jurassic World game.
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
																	  new Cemetery(), new FogDoor());

			List<String> mapOne = Arrays.asList(
					"..++++++..+++.................c.........++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"....c......+++.......................................................+++++......",
					"........................................................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++.........c.....",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"...c........+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............+...............................++.",
					"___.......___#.++++++++++++++.+++.........=..........................c........++");

			List<String> mapTwo = Arrays.asList(
					"c.++++++..+++................c......=...++++......++",
					"........+++++..............................+++++++..",
					"....c......+++......................................",
					"....................................................",
					".......................................###__###.....",
					"............................+..........#______#.....",
					".............................+++.......###__###.....",
					".............................++.....................",
					"....................................................",
					"....................................................",
					"............##___####################...............",
					"............#..........._....#......#...............",
					"............#....#.....#........+..##...............",
					"...c........#.................._....#............c..",
					"............#.....#............#....#...............",
					"............#...............++......#...............",
					"............##___####################...............",
					".+++................................................",
					"......+++..................................c...++...",
					"........c........+++................++++............");

			GameMap profaneCapital = new GameMap(groundFactory, mapOne);
			world.addGameMap(profaneCapital);

			GameMap AnorLondo = new GameMap(groundFactory, mapTwo);
			world.addGameMap(AnorLondo);

			// Place bonfire in both maps
			Bonfire pCBonfire = new Bonfire("Firelink Shrine's Bonfire", profaneCapital.at(38,11), true);
			Bonfire aLBonfire = new Bonfire("Anor Londo's Bonfire", AnorLondo.at(43,5));
			profaneCapital.at(38,11).setGround(pCBonfire);
			AnorLondo.at(43,5).setGround(aLBonfire);

			// Add each other into the instance of teleportBonfire, so that Player can move from one bonfire to another
			pCBonfire.addOtherBonfire(aLBonfire);
			aLBonfire.addOtherBonfire(pCBonfire);

			Player player = new Player("Unkindled", '@', 100, profaneCapital.at(38,12));
			world.addPlayer(player, profaneCapital.at(38,12));

			// Add both maps into Player's gameMap instance
			player.setGameMaps(profaneCapital);
			player.setGameMaps(AnorLondo);

			profaneCapital.at(37,11).addActor(new Vendor("Fire Keeper",'F', 1000));

			// Place Lord of Cinder in the map
			profaneCapital.at(6, 25).addActor(new YhormTheGiant(profaneCapital.at(6, 25)));
			AnorLondo.at(22, 13).addActor(new AldrichTheDevourer(AnorLondo.at(22, 13)));

			// Place Storm Ruler next to Yhorm The Giant
			profaneCapital.at(7, 25).addItem(new StormRuler());

			// Add skeleton in different location in both maps
			profaneCapital.at(0,0).addActor(new Skeleton("Skeleton Soldier 1", profaneCapital.at(0,0)));
			profaneCapital.at(79,0).addActor(new Skeleton("Skeleton Soldier 2", profaneCapital.at(79,0)));
			profaneCapital.at(15,10).addActor(new Skeleton("Skeleton Soldier 3", profaneCapital.at(15,10)));
			profaneCapital.at(62,16).addActor(new Skeleton("Skeleton Soldier 4", profaneCapital.at(62,16)));
			profaneCapital.at(75,25).addActor(new Skeleton("Skeleton Soldier 5", profaneCapital.at(75,25)));
			AnorLondo.at(4,4).addActor(new Skeleton("Skeleton Soldier 6", AnorLondo.at(4,4)));
			AnorLondo.at(5,8).addActor(new Skeleton("Skeleton Soldier 7", AnorLondo.at(5,8)));
			AnorLondo.at(24,5).addActor(new Skeleton("Skeleton Soldier 8", AnorLondo.at(24,5)));
			AnorLondo.at(37,6).addActor(new Skeleton("Skeleton Soldier 9", AnorLondo.at(37,6)));
			AnorLondo.at(45,13).addActor(new Skeleton("Skeleton Soldier 10", AnorLondo.at(45,13)));
			AnorLondo.at(27,19).addActor(new Skeleton("Skeleton Soldier 11", AnorLondo.at(27,19)));

			// Place chest in different location in both maps
			profaneCapital.at(48, 22).addActor(new Chest(profaneCapital.at(48, 22)));
			profaneCapital.at(5, 7).addActor(new Chest(profaneCapital.at(5, 7)));
			profaneCapital.at(35, 4).addActor(new Chest(profaneCapital.at(35, 4)));
			profaneCapital.at(46, 12).addActor(new Chest(profaneCapital.at(46, 12)));
			profaneCapital.at(18, 21).addActor(new Chest(profaneCapital.at(18, 21)));
			profaneCapital.at(65, 5).addActor(new Chest(profaneCapital.at(65, 5)));
			AnorLondo.at(5, 9).addActor(new Chest(AnorLondo.at(5, 9)));
			AnorLondo.at(14, 15).addActor(new Chest(AnorLondo.at(14, 15)));
			AnorLondo.at(50, 3).addActor(new Chest(AnorLondo.at(50, 3)));
			AnorLondo.at(44, 13).addActor(new Chest(AnorLondo.at(44, 13)));
			AnorLondo.at(42, 19).addActor(new Chest(AnorLondo.at(42, 19)));

			// run the game
			world.run();
	}
}
