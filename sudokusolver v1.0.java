class sudokusolver
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
	static boolean solve(int puzzle[][])
	{
		for(int row=0;row<9;row++)
			for(int col=0;col<9;col++)
				if(puzzle[row][col]==0)
				{
					for(int num=1;num<=9;num++)
						if(check(puzzle,row,col,num))
						{
							puzzle[row][col]=num;
							if(solve(puzzle))
								return true;
							puzzle[row][col]=0;
						}
					return false;
				}
		return true;
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
						{0,4,0,0,0,0,3,0,0}};	// Supposedly World's toughest Sudoku puzzle - takes 13 seconds to solve - Improvement required
		if(solve(puzzle))
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
					System.out.print(puzzle[i][j]+" ");
				System.out.println();
			}
		long end = System.currentTimeMillis();
		System.out.println("\n\n\nTime Elapsed is "+(end - start)+" milliseconds.\n");
	}
}