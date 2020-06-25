package kr.or.ddit.basic;

public class EnumSubject {

	public enum planet {
		
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);

		private double plan;

		planet(double radius) {

			plan = radius * radius * 3.14;
		}
		
		public double getPlan() {
			return plan;
		}
	}
	
	public static void main(String[] args) {		
		planet[] p = planet.values();		
		
		for (int i = 0; i < p.length; i++) {
			System.out.println(p[i].name() + " : " + Math.round(p[i].getPlan()));
		}
	}
}
