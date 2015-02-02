package fractals;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class DragonCurve
{
	private int level;
	private double x, y;					// used for scaling picture
	private Vector<Integer> sequence;
	
	public DragonCurve(int lvl)		// generates a curve of level lvl
	{
		level = 1;
		x = 2;
		y = 1;
		sequence = new Vector<Integer>();
		sequence.add(3);
		sequence.add(1);
		for (int i = 0; i < lvl; i++) level_up();
	}

	private void level_up()
	{
		level++;
		sequence = nextCurve(sequence);
		if(level % 2 != 0)
		{
			x = (2 * x + 1);
			y = (2 * y + 1);
		}
	}
	
	private Vector<Integer> nextCurve(Vector<Integer> old)	// generates the next sequence
	{
		boolean clockwise = true;
		Vector<Integer> newCurve = new Vector<Integer>();
		for (int i : old)
		{
			if(clockwise) 
			{
				newCurve.add((i + 1) % 8); 
				newCurve.add((i + 7) % 8);
			}
			else 
			{
				newCurve.add((i + 7) % 8); 
				newCurve.add((i + 1) % 8);
			}
			clockwise = !clockwise;
		}
		return newCurve;
	}
	
	public void draw()
	{
		double d = 1.0 / (x * 1.2);
		double x_coord = 0.2, y_coord = 0.6;
		StdDraw.setCanvasSize(1200, 1200);
		
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
		int level = 10;
		DragonCurve d = new DragonCurve(level);
		d.draw();
	}
}