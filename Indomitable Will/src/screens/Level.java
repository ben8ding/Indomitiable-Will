package screens;

import java.awt.Rectangle;
import java.util.ArrayList;

import Pickups.Capsule;
import Pickups.Obtainable;
import Pickups.PowerUp;
import Pickups.Weapon;
import processing.core.PApplet;
import sprites.Enemy;
import sprites.Player;
import sprites.Projectile;
/**
 * 
 * @author Matthew, Nathaniel, Ben
 * @version 5-22-18
 *
 */
public class Level {

	private Player player;
	private PlayerHUD hud;
	private ArrayList<Rectangle> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Projectile> playerBullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Capsule> drops;
	private int timer;
	private boolean cleared;
	private int lid;


	public Level() {
		player = new Player(); 
		walls = new ArrayList<Rectangle>();
		bullets = new ArrayList<Projectile>();
		playerBullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		drops = new ArrayList<Capsule>();

		//enemies.add(new Enemy(40, 40));
		hud = new PlayerHUD(player);
		walls.add(new Rectangle(-10,-190,1010,200));

		walls.add(new Rectangle(-40,0,50,660));
		walls.add(new Rectangle(990,0,10,660)); 
		walls.add(new Rectangle(0,660,1000,10));


		timer = 0;
		cleared = false;
		for(Enemy e : enemies) {
			walls.add(e.getBox());
		}
		drops.add(new Capsule(600,50, new PowerUp(PowerUp.powerUpType.FIRERATE)));
	}
	public Level(Level l, Player p) {
		this();
		hud = l.hud;
		walls = l.getWalls();
		enemies = l.getEnemies();
		drops = l.getDrops();
		player = p;
		player.setXLoc(50);
		player.setYLoc(300);
	}
	public void setup(PApplet drawer) {
		hud.setup(drawer);
		player.setup(drawer);
		for (Capsule object : drops) {
			object.getItem().setup(drawer);
		}

	}
	
	public void setID(int i) {
		lid = i;
	}

	public void draw(PApplet drawer) {
		timer++;
		drawer.clear();
		drawer.pushStyle();
		drawer.background(255);
		hud.draw(drawer);
		drawer.rect(drawer.width - 35, 0, 20, 30);
		drawer.textSize(15);
		drawer.fill(0);
		drawer.text("II", drawer.width - 27, 27);
		Capsule used = player.checkCollection(drops);
		if (used != null) {
			if(used.getItem() instanceof PowerUp) {
				PowerUp pu = (PowerUp)used.getItem();
				pu.use(player);
			}
			drops.remove(used);
		}
		player.draw(drawer);
		player.checkCollision(walls);
		drawer.stroke(0);
		for (Rectangle object : walls) {
			if(object.getSize().getWidth() > 40 || object.getSize().getHeight() > 40)
			drawer.rect(object.x, object.y, object.width, object.height);
		}
		for (Projectile object : bullets) {
			object.draw(drawer);
		}
		for (Projectile object : playerBullets) {
			object.draw(drawer);
		}
		for (Enemy object : enemies) {
			object.draw(drawer);
		}
		for (Capsule object : drops) {
			object.draw(drawer);
		}
		if (player.isFiring()) {
			playerBullets.addAll(player.fire());
		}


		if (timer % 10 == 0) {					// Enemy can fire at high rates as long as it has ammo
			for (Enemy object : enemies) {
				if(object.getAmmoCount() > 0 && object.getPerceptron().inferPerceptron(player.getXLoc(), player.getYLoc()) > 0) {		// Enemy AI tells whether to fire or not based on prediction of hit
					bullets.add(object.fire(player.getXLoc(), player.getYLoc()));
				}
			}
		}

		if (timer % 80 == 0) {					// Enemy ammo is added at much low rate
			for (Enemy object : enemies) {		// Thus, it is beneficial for enemy to conserve ammo if it cannot hit target
				object.addAmmo();				// so that if the ammo cache level is high, it can fire at high rate.
			}									// Otherwise, if enemy always fire, its firing rate is limited to the rate of ammo re-supply.
		}

		if (timer % 5 == 0) {					// enemy AI training happens continuously.
			for (Enemy object : enemies) {
				object.getPerceptron().trainPerceptron(lid);
			}
		}
	
		 for(int i = 0; i<bullets.size();i++) {
			
			 boolean remove = false;
			 if(bullets.size()>0 && bullets.get(0)!=null) {
			
				 if(player.checkCollision(bullets.get(i).getBox())) {
				 	//System.out.println("pong");
				 	remove = true;
				 	player.takeDamage();
			 }
			 
			 				if (player.checkCollision(bullets.get(i).getBox())) {
			 					// System.out.println("pong");
			 					remove = true;
			 					player.takeDamage();
			 				}
			 
			 				for (Rectangle wall : walls) {
			 					if (bullets.get(i).getBox().checkCollision(wall) || bullets.get(i).getXLoc() > 1000 || bullets.get(i).getXLoc() < 0 || bullets.get(i).getYLoc() > 700 || bullets.get(i).getYLoc() < 0) {
			 						if(wall.getSize().getWidth() > 40)
			 						remove = true;
			 					}
			 				}
			 
			 			}
			 			if (remove)
			 				bullets.remove(i);
			 		}
			 
			 		for (int i = 0; i < playerBullets.size(); i++  ) {
			 
			 			boolean remove = false;
			 			if (enemies.size() > 0 && enemies.get(0) != null) {
			 				if (playerBullets.size() > 0 && playerBullets.get(0) != null) {
			 
			 					if (enemies.get(0).checkCollision(playerBullets.get(i).getBox())) {
			 						System.out.println("pong");
			 						remove = true;
			 						enemies.get(0).takeDamage(1);
			 
			 					}
			 
			 					for (Rectangle wall : walls) {
			 						if (playerBullets.get(i).getBox().checkCollision(wall)|| playerBullets.get(i).getXLoc() > 1000 || playerBullets.get(i).getXLoc() < 0 || playerBullets.get(i).getYLoc() > 700 || playerBullets.get(i).getYLoc() < 0) {
			 							remove = true;
			 						}
			 					}
			 
			 				}
			 				if (remove)
			 					playerBullets.remove(i);
			 				if (enemies.get(0).getHp() == 0)
			 					enemies.remove(0);
			 			}
			 		}
			 
			 		if (enemies.size() == 0)
				cleared = true;
			// System.out.println(player.getHp());
		drawer.popStyle();

	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

	// public void setWalls(ArrayList<Rectangle> walls) {
	// this.walls = walls;
	// }

	public ArrayList<Projectile> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Projectile> bullets) {
		this.bullets = bullets;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Capsule> getDrops() {
		return drops;
	}

	public void setDrops(ArrayList<Capsule> drops) {
		System.out.println("");
		this.drops = drops;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addObstacle(Rectangle rect) {
		walls.add(rect);
	}
	
	public void addEnemy(Enemy buddy) {
		enemies.add(buddy);
	}

	public ArrayList<Projectile> getPlayerBullets() {
		return playerBullets;
	}

	public void setPlayerBullets(ArrayList<Projectile> playerBullets) {
		this.playerBullets = playerBullets;
	}

	public boolean isCleared() {
		return cleared;
	}

	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}

	public void setWalls(ArrayList<Rectangle> walls) {
		this.walls = walls;
	}
}
