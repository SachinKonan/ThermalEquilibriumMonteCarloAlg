package Skonan;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MonteCarlo 
{
	private double[][] map;
	private int mRow;
	private int mCol;
	private double temps[];
	private int[] place;
	private int iterations;
	private double avg;
	private Random rand = new Random();
	
	//boundtemps: {top , bottom , left , right} each element is a side 
	public MonteCarlo(int row, int col, double[] boundtemps, int index)
	{
		mRow = row;
		mCol = col;
		map = new double[mRow][mCol];
		temps = boundtemps;
		place = new int[]{mRow/2, mCol/2};
		iterations = index;
		avg = 0;
		
		for(int r = 0; r < map.length; r++)
		{
			for(int c = 0; c < map[0].length; c++)
			{
				if(r != 0 && r != mRow-1 && c != 0 && c != mCol-1)
				{
					map[r][c] = 10;
				}
				else
				{
					if(r == 0 ) map[r][c] = temps[0];
					else if(r == mRow-1) map[r][c] = temps[1];
					
					if(c == 0 && r!= 0 && r != mRow -1) map[r][c] = temps[2];
					else if(c == mCol-1 && r != 0 && r!= mRow -1) map[r][c] = temps[3];
				}
				
				
			}
		}
		
		/**
		for(double[] i : map)
		{
			for(double x: i)
			{
				System.out.print(x + " ");
			}
			System.out.println();
		}**/
	}
	
	
	public void moveAround()
	{
		//1:up 2: down 3: left 4: right
		for(int i = 0; i < iterations; i++)
		{
			int move = getDirection(place[0],place[1]);
			if(move ==1) place[0]-=1;
			if(move ==2) place[0]+=1;
			if(move ==3) place[1]-=1;
			if(move == 4) place[1]+=1;
			
			//System.out.println("At " + " "+ place[0] + " " + place[1]);
			if(place[0] != 0 && place[0] != mRow-1 && place[1] != 0 && place[1] != mCol-1) map[place[0]][place[1]] = i;
			else avg +=map[place[0]][place[1]];
			
		}
		
		System.out.println("average temp is: " + avg/iterations);
	}
	
	public void printer()
	{
		for(double[] i : map)
		{
			for(double x: i)
			{
				System.out.print(EasyFormat.format(Double.toString(x), 7, 'l', 0));
			}
			System.out.println();
		}
	}
	public int getDirection(int row1, int col1)
	{
		//1:up 2: down 3: left 4: right
		ArrayList<Integer> list = new ArrayList<Integer>();
		int row = row1;
		int col = col1;
		
		if((row +1 <= mRow-1)) list.add(2);
		if((row -1 >= 0)) list.add(1);
		
		if((col+1 <= mCol -1)) list.add(4);
		if((col -1 >= 0) ) list.add(3);
		
		//System.out.println(list.size() + " " + row + " " + col);
		//System.out.println(list.size());
		return list.get(rand.nextInt(list.size()));
	}
}
