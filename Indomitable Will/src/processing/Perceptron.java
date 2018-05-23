package processing;
/**
 * 
 * @author Ben
 *
 */
public class Perceptron {

	public double getWx() {
		return wx;
	}

	public void setWx(double wx) {
		this.wx = wx;
	}

	public double getWy() {
		return wy;
	}

	public void setWy(double wy) {
		this.wy = wy;
	}

	public double getWc() {
		return wc;
	}

	public void setWc(double wc) {
		this.wc = wc;
	}

	public double getL_rate() {
		return l_rate;
	}

	public void setL_rate(double l_rate) {
		this.l_rate = l_rate;
	}


	int pout;		// perceptron output
	double wx, wy, wc; 	// weights for x, y, and constant
	int goal;		// whether hit goal
	int lid;		// level id
	double l_rate;	// learning rate
	
	public Perceptron() {
		init();
	}
	
	public void init() {
		
		wx = 0.1 * (Math.random() - 0.5);			// randomize the initial weight
		wy = 0.1 * (Math.random() - 0.5);
		wc = 0.1 * (Math.random() - 0.5);
		
		l_rate = 0.002;
	}
	
	public void trainPerceptron(int level) {			// Generate trainig data to learn where the boundary is.
		
		lid = level;
		
		if(lid < 3) 
			return;
		
		
		for(int i=0; i<5; i++) {
			
			double x_s = Math.random();
			double y_s = Math.random();		// scale x, y
	
			if(lid == 3) {
				if(x_s + y_s > 1) goal = 0;
				else goal = 1;
			}
			else if(lid == 4) {
				if(x_s - y_s > 0.25) goal = 0;
				else goal = 1;
			}			
			
			double p = wx * x_s + wy * y_s + wc * 1.0;
			
			if(p > 0) pout = 1;
			else pout = 0;
			
			wx += (goal - pout) * l_rate * x_s;
			wy += (goal - pout) * l_rate * y_s;
			wc += (goal - pout) * l_rate;
			
		double norm = (wx*wx + wy*wy + wc*wc);
		
		norm = Math.sqrt(norm);
			
//			System.out.printf("weights %f, %f %f ", wx, wy, wc);
//			System.out.printf("(goal, pout) %d, %d, %f\n", goal, pout, norm);
		}		
	}
	
	public int getPout() {
		return pout;
	}

	public void setPout(int pout) {
		this.pout = pout;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	
	public int inferPerceptron(int x, int y) {
		
		double x_s = (float)x / (float)600;
		double y_s = (float)y / (float)600;
		
		double p = wx * x_s + wy * y_s + wc * 1.0;		

		if(p > 0 || lid < 3) {
			pout = 1;
		
			wx += (goal - pout) * l_rate * x_s;
			wy += (goal - pout) * l_rate * y_s;
			wc += (goal - pout) * l_rate;
		
//		System.out.printf("weights %f, %f %f", wx, wy, wc);
//		System.out.printf("goal-pout %d, %d\n", goal, pout);

		}
		else {
			pout = 0;
		}	
		
		return pout;			// perceptron output indicate whether it can hit or not
	}
	
	
	
	
	
}
