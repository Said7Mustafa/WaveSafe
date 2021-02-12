
public class GunAmmo {
	private int ammo = 17;
	private int defaultAmmo = 17;
	public int bulletCounter() {
		return ammo;
	}
	public int getDefaultAmmo() {
		return defaultAmmo;
	}
	public void reload() {
		ammo = getDefaultAmmo();
	}
	
	public int discharge() {
		
		if (bulletCounter() <= 0 ) 
			return 0;
		else {
			ammo = ammo -1;
			return (ammo);
		}
	}
	
	
}
