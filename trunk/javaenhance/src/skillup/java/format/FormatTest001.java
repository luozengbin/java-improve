package skillup.java.format;

import java.util.Date;

public class FormatTest001 {

	public static void main(String[] args) {
		System.out.printf("%h\n", 1212);
		System.out.printf("%x\n", 1212);

		System.out.printf("%b\n", false);

		System.out.printf("%s\n", "Hello");

		System.out.printf("%c\n", 'x');

		System.out.printf("%d\n", 11);

		int a1 = 1, a2 = 2, a3 = 3;
		System.out.printf("a2は%2$d、a3は%3$d、a1は%1$dです。\n", a1, a2, a3);

		int a = 100;
		System.out.printf("%dは16進数で%<x、8進数で%<oです。\n", a);
		
		System.out.printf("a2は%2$d、a3は%d、a1は%dです。\n",a1,a2,a3);
		
		
		System.out.printf("ノーマル :%d%n",a);
		System.out.printf("符号つき:%+d%n",a);
		System.out.printf("スペース:% d%n",a);

		int b = -100;
		System.out.printf("ノーマル :%d%n",b);
		System.out.printf("符号つき:%+d%n",b);
		System.out.printf("括弧つき:%(d%n",b);
		
		a = 1000;
		double c = 10.05;
		System.out.printf("a = %05d %n",a);
		System.out.printf("a = %,05d %n",a);
		System.out.printf("c = %5.1f %n",c);
		System.out.printf("c = %05.2f %n",c);
		
		Date date = new Date();
		System.out.printf("%tY年%<tB%<te日(%<ta)%<tp%<tl時%<tM分 %<TZ",date);




	}
}
