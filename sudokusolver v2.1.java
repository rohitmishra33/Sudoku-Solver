class sudokusolver3
{
	static boolean check(int puzzle[][],int row,int col,int num)
	{
		int r,c,chk;
		for(chk=0;chk<9;chk++)
			if(puzzle[row][chk]==num || puzzle[chk][col]==num)	// Checks Row & Column simultaneously for occurrence of number.
				return false;
		for(r=0;r<3;r++)
			for(c=0;c<3;c++)
				if(puzzle[r+(row-row%3)][c+(col-col%3)]==num)	// Checks corresponding Grid for occurrence of number.
					return false;
		return true;
	}
	static boolean solve(int puzzle[][],int constraints[][])
	{
		int row=0,col=0,max=0,r,c;
		for(r=0;r<9;r++)
			for(c=0;c<9;c++)
				if(constraints[r][c]>max)
				{
					max=constraints[r][c];
					row=r;col=c;
				}
		if(max==0)
			return true;
		constraints[row][col]*=-1;
		for(int num=1;num<=9;num++)
			if(check(puzzle,row,col,num))
			{
 				puzzle[row][col]=num;
				if(solve(puzzle,constraints))
					return true;
				puzzle[row][col]=0;
			}
		constraints[row][col]*=-1;
		return false;
	}
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int puzzle[][]={{0,0,0,7,0,0,0,0,0},
						{1,0,0,0,0,0,0,0,0},
						{0,0,0,4,3,0,2,0,0},
						{0,0,0,0,0,0,0,0,6},
						{0,0,0,5,0,9,0,0,0},
						{0,0,0,0,0,0,4,1,8},
						{0,0,0,0,8,1,0,0,0},
						{0,0,2,0,0,0,0,5,0},
						{0,4,0,0,0,0,3,0,0}},row,col,chk,r,c;	// Supposedly World's toughest Sudoku puzzle
		int constraints[][]=new int[9][9];
		for(row=0;row<9;row++)
			for(col=0;col<9;col++)
				if(puzzle[row][col]==0)
				{
					for(chk=0;chk<9;chk++)
					{
						if(puzzle[row][chk]!=0)
							constraints[row][col]++;
						if(puzzle[chk][col]!=0)
							constraints[row][col]++;
					}
				/*	for(r=0;r<3;r++)
						for(c=0;c<3;c++)
							if(r+(row-row%3)!=row && c+(col-col%3)!=col && puzzle[r+(row-row%3)][c+(col-col%3)]!=0)
								constraints[row][col]++; */	// Checks Grid for constraints but generates 46% - 75% Time overhead
				}
		if(solve(puzzle,constraints))
			for(row=0;row<9;row++)
			{
				for(col=0;col<9;col++)
					System.out.print(puzzle[row][col]+" ");
				System.out.println();
			}
		long end = System.currentTimeMillis();
		System.out.println("\n\n\nTime Elapsed is "+(end - start)+" milliseconds.\n");
	}
}