package skillup.java.basic;

import static org.junit.Assert.*;

import org.junit.Test;

//hashcodeを理解する
public class HashCodeTest {

	@Test
	public void testHashCode() throws Exception {
		Point p1 = new Point(10,10,10);
		
		Point p2 = new Point(10,10,10);
		
		Point p3 = new Point(10,10,20);
		
		System.out.println("p1:" + p1);
		System.out.println("p2:" + p2);
		System.out.println("p3:" + p3);
		
		assertEquals(p1, p2);
		
		//zはhashcode運算と関係ないから、p1 = p3が成立です。
		assertEquals(p1, p3);
		
		//hashcode運算で使われていますので、値を変更すると、アドレスの引用も変わる可能性があります。
		p2.setX(100);
		
		System.out.println("updated p2:" + p2);
		assertEquals(p1, p2);
		
	}
	
	//テスト用pojo
	public class Point{
		
		private int x;
		
		private int y;
		
		private int z;
		
		
		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
		

		public int getZ() {
			return z;
		}

		public void setZ(int z) {
			this.z = z;
		}

		//xとyの値を元にhashcodeの計算する
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Point))
				return false;
			Point other = (Point) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private HashCodeTest getOuterType() {
			return HashCodeTest.this;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Point [x=").append(x).append(", y=").append(y)
					.append(", z=").append(z).append("]");
			return builder.toString();
		}
	}
	
}
