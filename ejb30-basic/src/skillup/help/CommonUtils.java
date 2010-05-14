package skillup.help;

public final class CommonUtils {
	
	public static String toString(String ...args){
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]).append(",");
		}
		
		return sb.toString();
	}
	
	
	public static String toString(Object ... args){
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]).append(",");
		}
		
		return sb.toString();
	}

}
