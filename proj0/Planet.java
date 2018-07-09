public class Planet {
	// any instant variable
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	// constructor
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	// other behavior/ methods
	// calculate the distance between the current planet with a given planet
	public double calcDistance(Planet p) {
		double dx = p.xxPos - xxPos;
		double dy = p.yyPos - yyPos;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	// returns a double describing the force exerted on this planet by the given planet
	public double calcForceExertedBy(Planet p) {
		double constantG = 6.67 * (Math.pow(10, -11));
		double distance = this.calcDistance(p);
		return constantG * this.mass * p.mass / (Math.pow(distance, 2));
	}

	// calculate the gravity force on x
	public double calcForceExertedByX(Planet p) {
		double forceBy = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return forceBy / distance * (p.xxPos - this.xxPos);
	}

	// calculate the gravity force on y
	public double calcForceExertedByY(Planet p) {
		double forceBy = this.calcForceExertedBy(p);
		double distance = this.calcDistance(p);
		return forceBy / distance * (p.yyPos - this.yyPos);
	}

	// check if the current planet equals the given planet
	public boolean equals(Planet p) {
		return (this.xxPos == p.xxPos &&
				this.yyPos == p.yyPos &&
				this.xxVel == p.xxVel &&
				this.yyVel == p.yyVel &&
				this.mass == p.mass &&
				this.imgFileName == p.imgFileName);
	}

	// calculate the next force on x
	public double calcNetForceExertedByX(Planet[] ps) {
		double sum = 0;
		for (Planet p : ps) {
			if (this.equals(p)) continue;
			sum += this.calcForceExertedByX(p);
		}
		return sum;
	}

	// calculate the next force on y
	public double calcNetForceExertedByY(Planet[] ps) {
		double sum = 0;
		for (Planet p : ps) {
			if (this.equals(p)) continue;
			sum += this.calcForceExertedByY(p);
		}
		return sum;
	}

	/*
	determines how much the forces exerted on the planet will cause that planet to accelerate, 
	and the resulting change in the planet’s velocity and position in a small period of time dt
	*/
	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		xxVel = this.xxVel + ax * dt;
		yyVel = this.yyVel + ay * dt;
		xxPos = this.xxPos + xxVel * dt;
		yyPos = this.yyPos + yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos, "images/" + this.imgFileName);
	}


}