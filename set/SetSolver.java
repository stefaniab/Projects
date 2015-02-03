package set;

import java.util.*;
import edu.princeton.cs.introcs.*;

class SetSolver
{
	// Card attributes
	enum Colour {RED, GREEN, PURPLE};
	enum Fill {OPEN, STRIPED, FILLED};
	enum Shape {SQUIGGLE, DIAMOND, OVAL};
	
	Collection<Set> solution;
	int size = 12;
	
	class Card
	{
		int number;
	    Colour colour;
	    Fill fill;
	    Shape shape;
	    public Card(int number, Colour colour, Fill fill, Shape shape)
	    {
	    	this.number = number;
	    	this.colour = colour;
	    	this.fill = fill;
	    	this.shape = shape;
	    }
	    @Override
	    public boolean equals(Object o)
	    {
	    	if (!(o instanceof Card)) return false;
        	Card c = (Card) o;
        	return number == c.number && colour == c.colour && fill == c.fill && shape == c.shape;
	    }
	    void print()
	    {
	    	int newNumber = number;
	    	if (newNumber == 0) newNumber = 3;
	    	StdOut.print(newNumber + " " + colour.toString() + " " + fill.toString() + " " + shape.toString() + " ");
	    }
	}
	
	class Set
	{
		Card c1, c2, c3;
		public Set(Card c1, Card c2, Card c3)
		{
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
		}
		void print()
		{
			c1.print();
			StdOut.print("\t");
			c2.print();
			StdOut.print("\t");
			c3.print();
			StdOut.println();
		}
	}

	// returns the card that would form a set with c1 and c2
	Card thirdCard(Card c1, Card c2)
	{
		int number = (6 - c1.number - c2.number) % 3;
		Colour colour = Colour.values()[(6 - c1.colour.ordinal() - c2.colour.ordinal()) % 3];
		Fill fill = Fill.values()[(6 - c1.fill.ordinal() - c2.fill.ordinal()) % 3];
		Shape shape = Shape.values()[(6 - c1.shape.ordinal() - c2.shape.ordinal()) % 3];
		return new Card(number, colour, fill, shape);
	}
	
	// finds all the sets in the board
	Collection<Set> findSets(Card[] board)
	{
		Collection<Set> solution = new ArrayList<Set>();
		for (int i = 0; i < size - 2; i++)
		{
			for (int j = i + 1; j < size - 1; j++)
			{
				Card toFind = thirdCard(board[i], board[j]);
				for (int k = j + 1; k < size; k++)
				{
					if (toFind.equals(board[k]))
					{
						solution.add(new Set(board[i], board[j], toFind));
						break;
					}
				}
			}
		}
		return solution;
	}
	
	Card readCard()
	{
		int number;
		Colour colour;
		Fill fill;
		Shape shape;
		String attributes = StdIn.readString();
		// in the input cards are represented with a 4-char string
		// number: 1,2,3, colour: r,g,b, fill: o,s,f, shape: s,o,d
		switch(attributes.charAt(0))
		{
			case '1' :  number = 1; break;
			case '2' :  number = 2; break;
			default  :  number = 0; break;
		}
		switch(attributes.charAt(1))
		{
			case 'r' :  colour = Colour.RED; break;
			case 'g' :  colour = Colour.GREEN; break;
			default  :  colour = Colour.PURPLE; break;
		}
		switch(attributes.charAt(2))
		{
			case 'o' :  fill = Fill.OPEN; break;
			case 's' :  fill = Fill.STRIPED; break;
			default	 :  fill = Fill.FILLED; break;
		}
		switch(attributes.charAt(3))
		{
			case 's' :  shape = Shape.SQUIGGLE; break;
			case 'o' :  shape = Shape.OVAL; break;
			default	 :  shape = Shape.DIAMOND; break;
		}
		return new Card(number, colour, fill, shape);
	}
	
	public SetSolver()
	{
		Card[] board = new Card[size];
		for (int i = 0; i < 12; i++)
		{
			board[i] = readCard();
		}
		solution = findSets(board);
	}
	void print()
	{
		for (Set s : solution)
		{
			s.print();
		}
	}
}