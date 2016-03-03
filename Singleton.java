public class Singleton {
	public static Singleton This;
	public static final Object mutex = new Object();

	private Singleton() {
		This = this;
	}
	
	public static Singleton getInstance() {
		if (This == null) {
			synchronized (mutex) {
				if (This == null)
					new Singleton();
			}
		}
		return This;
	}
}
