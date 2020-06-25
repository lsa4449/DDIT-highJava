
public class test {

	public static void main(String[] args) {

		String num = "";
		for (int i = 0; i < 13; i++) {
			int random = (int) (Math.random() * 10);
			num += random;
		}
		System.out.print(num);

	}
}
