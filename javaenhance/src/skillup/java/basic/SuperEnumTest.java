package skillup.java.basic;

import static org.junit.Assert.*;

import org.junit.Test;


public class SuperEnumTest {
	
	@Test
	public void testSuperEnum() throws Exception {
		
		System.out.println(WeekDay.日曜日);
		
		System.out.println(WeekDay.日曜日.nextDay());
		
		assertEquals(WeekDay.火曜日, WeekDay.月曜日.nextDay());
	}
	
	public enum WeekDay{
		日曜日(7){
			@Override
			public WeekDay nextDay(){
				return 月曜日;
			}
		},
		月曜日(1){
			@Override
			public WeekDay nextDay(){
				return 火曜日;
			}
		},
		火曜日(2){
			@Override
			public WeekDay nextDay(){
				return 水曜日;
			}
		},
		水曜日(3){
			@Override
			public WeekDay nextDay(){
				return 木曜日;
			}
		},
		木曜日(4){
			@Override
			public WeekDay nextDay(){
				return 金曜日;
			}
		},
		金曜日(5){
			@Override
			public WeekDay nextDay(){
				return 土曜日;
			}
		},
		土曜日(6){
			@Override
			public WeekDay nextDay(){
				return 月曜日;
			}
		};
		
		WeekDay(int day){
			this.day = day;
		}
		
		public abstract WeekDay nextDay();
		
		private int day;
		
		public int getNativeDay(){
			return day;
		}
	}
}
