package junior.global;

public class MathUtils {
	
	public static int clamp(int value, int min, int max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
	
	public static double clamp(double value, double min, double max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
	
	public static float clamp(float value, float min, float max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
	
	public static long clamp(long value, long min, long max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
	
	public static byte clamp(byte value, byte min, byte max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
	
	public static short clamp(short value, short min, short max) {
		if(value >= max) return max; else if(value <= min) return min;
		return value;
	}
}
