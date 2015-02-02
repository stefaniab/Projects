package fractals;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

/*
Draws a Levy C curve (http://en.wikipedia.org/wiki/L%C3%A9vy_C_curve)
02.02.2015
Stefan�a Berglj�t Stef�nsd�ttir
*/


public class LevyC
{
	private int level;
	private double x;					// used for scaling picture
	private Vector<Integer> sequence;
	
	public LevyC(int lvl)		// generates a fractal of level lvl
	{
		level = 1;
		x = 1;
		sequence = new Vector<Integer>();
		sequence.add(2);
		for (int i = 0; i < lvl; i++) level_up();
	}

	private void level_up()
	{
		level++;
		sequence = nextCurve(sequence);
		if(level % 2 != 0)
		{
			x = (2 * x + 1);
		}
	}
	
	private Vector<Integer> nextCurve(Vector<Integer> old)	// generates the next sequence
	{
		Vector<Integer> newCurve = new Vector<Integer>();
		for (int i : old)
		{
			newCurve.add((i + 1) % 8); 
			newCurve.add((i + 7) % 8);
		}
		return newCurve;
	}
	
	public void draw()
	{
		double d = 0.75 / (x);
		if (level % 2 == 0) d /= 2;
		double x_coord = 0.25, y_coord = 0.6;
		StdDraw.setCanvasSize(650, 650);
		
		for (int i : sequence)
		{
			switch (i) 
			{
				case 0 :	StdDraw.line(x_coord, y_coord, x_coord, y_coord + d);
							y_coord += d;
							break;
				case 1 : 	StdDraw.line(x_coord, y_coord, x_coord + d, y_coord + d);
							y_coord += d;
							x_coord += d;
							break;
				case 2 : 	StdDraw.line(x_coord, y_coord, x_coord + d, y_coord);
							x_coord += d;
							break;
				case 3 : 	StdDraw.line(x_coord, y_coord, x_coord + d, y_coord - d);
							y_coord -= d;
							x_coord += d;
							break;
				case 4 : 	StdDraw.line(x_coord, y_coord, x_coord, y_coord - d);
							y_coord -= d;
							break;
				case 5 : 	StdDraw.line(x_coord, y_coord, x_coord - d, y_coord - d);
							y_coord -= d;
							x_coord -= d;
							break;
				case 6 : 	StdDraw.line(x_coord, y_coord, x_coord - d, y_coord);
							x_coord -= d;
							break;
				case 7 : 	StdDraw.line(x_coord, y_coord, x_coord - d, y_coord + d);
							y_coord += d;
							x_coord -= d;
							break;
				
			}
		}
	}
	
	public void print_sequence()
	{
		for (int i : sequence) System.out.print(i + " ");
	}
	
	public static void main(String[] args)
	{

		LevyC c = new LevyC(13);
		c.draw();
	}
}