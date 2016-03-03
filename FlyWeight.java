import java.util.HashMap;
import java.util.Map;

public class FlyWeight {
	
	abstract class Material {
		private String mColor;
		private String mName;
		private String mHeight;
		public Material(String color, String name, String height) {
			mColor = color;
			mName = name;
			mHeight = height;
		}
		
		public String getColor() {
			return mColor;
		}
		
		public String getName() {
			return mName;
		}
		
		public String getHeight() {
			return mHeight;
		}
		
		abstract void beHavior();
	}
	
	class Wood extends Material {

		public Wood() {
			super("green", "wood", "2m");
			System.out.println("나무 생성");
		}

		@Override
		void beHavior() {
			System.out.println("나무가 흔들흔들");
		}		
	}
	
	class Tile extends Material {

		public Tile() {
			super("gray", "tile", "1cm");
			System.out.println("타일 생성");
		}

		@Override
		void beHavior() {
			System.out.println("동작 없음");
		}
	}
	
	class Sand extends Material {

		public Sand() {
			super("gray", "sand", "1cm");
			System.out.println("모래 생성");
		}

		@Override
		void beHavior() {
			System.out.println("바람에 모래가 날림");
		}
	}
	
	private Map<String, Material> lookUpMap = new HashMap<>();
	private Material [][] map = new Material[3][];
	
	private void init() {
		for (int i = 0; i < 3; i++)
			map[i] = new Material[3];
		lookUpMap.put("wood", new Wood());
		lookUpMap.put("tile", new Tile());
		lookUpMap.put("sand", new Sand());
	}
	
	private void fillingTile() {
		for (int i = 0; i < 3; i++)
			map[0][i] = lookUpMap.get("wood");		
		for (int i = 0; i < 3; i++)
			map[1][i] = lookUpMap.get("tile");		
		for (int i = 0; i < 3; i++)
			map[2][i] = lookUpMap.get("sand");		
	}
	
	private void showMaterial(int x, int y) {
		System.out.print(map[y][x].mName + ":");
		map[y][x].beHavior();
	}
	
	public FlyWeight() {
		init();
		fillingTile();
		showMaterial(0, 0);
	}
	
	public static void main(String[] args) {
		new FlyWeight();
	}
}