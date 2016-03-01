public class FactoryPattern {

	static class Enviroment {
		public static Class <? extends ViewFactory> factory;
		//...
	}
	
	public static class Bunker implements ViewFactory {
		public Bunker() {			
		}
		
		@Override
		public Button createButton() {
			return new Button() {
				@Override
				public void view() {
					System.out.println("�̰��� ��Ŀ��ư�̴�.");
				}				
			};
		}

		@Override
		public ImageView createImageView() {
			return new ImageView() {
				@Override
				public void view() {
					System.out.println("�̰��� ��Ŀ �̹������.");				
				}
			};
		}		
	}
	
	class ThirdPartyClass {
		private ImageView imageView;
		private Button button;
		public void makeViews() {
			ViewFactory viewFactory = null;
			try {
				viewFactory = FactoryCreator.getInstance().createViewFactory();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			imageView = viewFactory.createImageView();
			button = viewFactory.createButton();
		}
		
		public ThirdPartyClass() {
			makeViews();
			imageView.view();
			button.view();
		}
	}
	
	private FactoryPattern() {
		Enviroment.factory = Bunker.class;
		new ThirdPartyClass();
	}
	//���� �������� �ٽ� ���ο� ȯ�濡�� �����ϰ� ������ ���ο� ȯ���� �߰��ؼ� ���� �� �ִ�.
	//�⺻ ȯ�濡 �´� ��ư�̳� �̹����並 ����� ���� ������ Map�̳� �迭�� �̿��ؼ� ���ǹ��� �ϳ��� ��ġ�� �ʰ� ���� �����ϴ�.
	
	/** 
	 * *****************************************
	 * �Ʒ� ���ʹ� ���̺귯���� ������ �Ұ����� �κ��̶� �����Ѵ�.   *
	 * *****************************************
	 *  */

	public interface Button {
		public void view();
	}	
	public interface ImageView {
		public void view();
	}
	
	public interface ViewFactory {
		public Button createButton();
		public ImageView createImageView();
	}
	
	public static class WindowFactory implements ViewFactory {

		@Override
		public ImageView createImageView() {
			return null;
		}

		@Override
		public Button createButton() {
			return null;
		}
		
	}
	
	public static class UbuntuFactory implements ViewFactory {

		@Override
		public ImageView createImageView() {
			return null;
		}
		
		@Override
		public Button createButton() {
			return null;
		}		
	}
	
	public static class MacFactory implements ViewFactory {
		@Override
		public ImageView createImageView() {
			return null;
		}
		
		@Override
		public Button createButton() {
			return null;
		}
		
	}
	
	public static class FactoryCreator {
		private static FactoryCreator This;		
		public ViewFactory createViewFactory() throws InstantiationException, 
										   IllegalAccessException {
			return Enviroment.factory.newInstance();
		}
		
		synchronized public static FactoryCreator getInstance() {
			if (This == null)
				This = new FactoryCreator();
			return This;
		}
	}
	
	Class <? extends ViewFactory> []factories = new Class [] {
		UbuntuFactory.class,
		WindowFactory.class,
		MacFactory.class
	};

	private void setDefaultEnviroment() {
		Enviroment.factory = factories[1];
	}
	
	public static void main(String[] args) {
		new FactoryPattern();
	}
}