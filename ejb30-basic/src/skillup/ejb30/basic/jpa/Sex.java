package skillup.ejb30.basic.jpa;

public enum Sex {
	Boy{
		@Override
		public String getName() {
			return "男性";
		}
	},
	Girl{
		@Override
		public String getName() {
			return "女性";
		}
	},
	NewHalfrl{
		@Override
		public String getName() {
			return "変性";
		}
	};
	public abstract String getName();
}
