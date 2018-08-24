package day7;

public class Unit {
	
	private Weapon weapon;
	
	private String name;
	
	public Unit() {}
	public Unit(String name) {
		this.name=name;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void attack() {
		weapon.attack();
		// 만약 weapon이라는 규격(인터페이스)가 없다면 어떤 무기를 사용할지 모르니
		// 우리가 이 코드를 작성할 때 Gun.attack(); 이나 Sword.attack();을
		// 사용할지 선택해야 한다. 하지만 인터페이스를 정의함으로써 이를 통합할 수 잇다.
	}
	
	public static void main(String[] args) {
		Unit unit = new Unit("SCV");
		
		Weapon weapon = null;
		
		weapon = new Gun();
		unit.setWeapon(weapon);
		unit.attack();
		
		weapon = new Sword();
		unit.setWeapon(weapon);
		unit.attack();
	}

}
