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

public class Level {

	private Player player;
	private ArrayList<Rectangle> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Projectile> playerBullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Capsule> drops;
	private int timer;
	

	public Level() {

		player = new Player();
		walls = new ArrayList<Rectangle>();
		bullets = new ArrayList<Projectile>();
		playerBullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		drops = new ArrayList<Capsule>();
		enemies.add(new Enemy(250, 400));
		walls.add(new Rectangle(0,-190,1000,200));
		walls.add(new Rectangle(-40,0,50,700));
		walls.add(new Rectangle(985,-10,500,710));
		walls.add(new Rectangle(0,660,1000,40));
		// drops.add(new Capsule(40,40, new Weapon(Weapon.weaponType.SHOTGUN)));
		drops.add(new Capsule(600, 40, new PowerUp(PowerUp.powerUpType.SPEED)));
		//bullets.add(new Projectile());
//		walls.add(new Rectangle(500, 0, 100, 350));
//		walls.add(new Rectangle(60, 350, 300, 100));
		timer = 0;
	}
	

	public void setup(PApplet drawer) {
		player.setup(drawer);
		for (Capsule object : drops) {
			object.getItem().setup(drawer);
		}

	}

	public void draw(PApplet drawer) {
		timer++;
		drawer.clear();
		drawer.pushStyle();
		drawer.background(255);
		drawer.rect(drawer.width - 35, 0, 20, 30);
		drawer.textSize(15);
		drawer.fill(0);
		drawer.text("II", drawer.width - 27, 27);
		Capsule used = player.checkCollection(drops);
		if (used != null) {
			Obtainable drop = used.getItem();
			if (drop instanceof PowerUp) {
				((PowerUp) drop).use(player);
				drops.remove(used);
			}

		}
		player.draw(drawer);
		player.checkCollision(walls);
		drawer.stroke(0);
		for (Rectangle object : walls) {
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

		if (timer % 40 == 0) {
			for (Enemy object : enemies) {
				bullets.add(object.fire(player.getXLoc(), player.getYLoc()));
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
			 
				 for(Rectangle wall : walls) {
					 if(bullets.get(i).getBox().checkCollision(wall)) { 
						remove = true ;
					 }
			 	}
			
			 }
			 if(remove)
				 bullets.remove(i);
		 }
		 
		 for(int i = 0; i<playerBullets.size();i++) {
				
			 boolean remove = false;
			 if(enemies.size()>0 && enemies.get(0)!=null) {
			 if(playerBullets.size()>0 && playerBullets.get(0)!=null) {
			
				 if(enemies.get(0).checkCollision(playerBullets.get(i).getBox())) {
				 	System.out.println("pong");
				 	remove = true;
				 	enemies.get(0).takeDamage(1);
				 	
			 }
			 
				 for(Rectangle wall : walls) {
					 if(playerBullets.get(i).getBox().checkCollision(wall)) { 
						remove = true ;
					 }
			 	}
			
			 }
			 if(remove)
				 playerBullets.remove(i);
			 if(enemies.get(0).getHp()==0)
				 enemies.remove(0);
			 }
		 }
		
		 //System.out.println(player.getHp());
		drawer.popStyle();

	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

//	public void setWalls(ArrayList<Rectangle> walls) {
//		this.walls = walls;
//	}

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

}
