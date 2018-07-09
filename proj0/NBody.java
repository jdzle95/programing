public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] ps = readPlanets(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		

		for (int time = 0; time <= T; time += dt) {
			for (int i = 0; i < ps.length; i++) {
				double xforce = ps[i].calcNetForceExertedByX(ps);
				double yforce = ps[i].calcNetForceExertedByY(ps);
				ps[i].update(dt, xforce, yforce);
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet p : ps){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
			// print out something with given code
			StdOut.printf("%d\n", ps.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < ps.length; i++) {
 			   StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                			 ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                  			 ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
}
		}

	} 



	// return a double corresponding to the radius of the universe in that file
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

	// Given a file name, it should return an array of Planets corresponding to the planets in the file
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		in.readDouble();

		Planet[] rst = new Planet[num];
		for (int i = 0; i < num; i++) {
			rst[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
								in.readDouble(), in.readString());
		}
		return rst;
	}
}