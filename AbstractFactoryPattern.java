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
					System.out.println("이것은 벙커버튼이다.");
				}				
			};
		}

		@Override
		public ImageView createImageView() {
			return new ImageView() {
				@Override
				public void view() {
					System.out.println("이것은 벙커 이미지뷰다.");				
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
	//여기 위까지가 핵심 새로운 환경에서 실행하고 싶으면 새로운 환경을 추가해서 넣을 수 있다.
	//기본 환경에 맞는 버튼이나 이미지뷰를 만들고 싶을 때에도 Map이나 배열을 이용해서 조건문을 하나도 거치지 않고 생성 가능하다.
	
	/** 
	 * *****************************************
	 * 아래 부터는 라이브러리로 수정이 불가능한 부분이라 가정한다.   *
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