class sudokusolver2
{
	static boolean check(int puzzle[][][],int row,int col,int num)
	{
		int r,c,chk;
		for(chk=0;chk<9;chk++)
			if(puzzle[0][row][chk]==num || puzzle[0][chk][col]==num)
				return false;
		for(r=0;r<3;r++)
			for(c=0;c<3;c++)
				if(puzzle[0][r+(row-row%3)][c+(col-col%3)]==num)
					return false;
		return true;
	}
	static boolean solve(int puzzle[][][])
	{
		int r=0,c=0,max=0;
		for(int row=0;row<9;row++)
			for(int col=0;col<9;col++)
				if(puzzle[1][row][col]>max)
				{
					max = puzzle[1][row][col];
					r = row;
					c = col;
				}
		if(max == 0)
			return true;
		puzzle[1][r][c] = -puzzle[1][r][c];
		for(int num=1;num<=9;num++)
			if(check(puzzle,r,c,num))
			{
				puzzle[0][r][c]=num;
				if(solve(puzzle))
					return true;
				puzzle[0][r][c]=0;
			}
		puzzle[1][r][c] = -puzzle[1][r][c];
		return false;
	}
	public static void main(String args[])
	{
		long start = System.currentTimeMillis();
		int[][][] puzzle=new int[2][9][9];
		int i,j,k;
		
		/*puzzle[][][]={{0,0,0,7,0,0,0,0,0},
						1,0,0,0,0,0,0,0,0,
						0,0,0,4,3,0,2,0,0,
						0,0,0,0,0,0,0,0,6,
						0,0,0,5,0,9,0,0,0,
						0,0,0,0,0,0,4,1,8,
						0,0,0,0,8,1,0,0,0,
						0,0,2,0,0,0,0,5,0,
						0,4,0,0,0,0,3,0,0}};*/
		for(i=0;i<2;i++)
			for(j=0;j<9;j++)
				for(k=0;k<9;k++)
					puzzle[i][j][k]=0;
		puzzle[0][0][3]=7;
		puzzle[0][1][0]=1;
		puzzle[0][2][3]=4;puzzle[0][2][4]=3;puzzle[0][2][6]=2;
		puzzle[0][3][8]=6;
		puzzle[0][4][3]=5;puzzle[0][4][5]=9;
		puzzle[0][5][6]=4;puzzle[0][5][7]=1;puzzle[0][5][8]=8;
		puzzle[0][6][4]=8;puzzle[0][6][5]=1;
		puzzle[0][7][2]=2;puzzle[0][7][7]=5;
		puzzle[0][8][1]=4;puzzle[0][8][6]=3;
		for(i=0;i<9;i++)
			for(j=0;j<9;j++)
				if(puzzle[0][i][j] == 0)
					for(k=0;k<9;k++)
					{
						if(puzzle[0][i][k] != 0)
							puzzle[1][i][j]++;
						if(puzzle[0][k][j] != 0)
							puzzle[1][i][j]++;
					}
		if(solve(puzzle))
			for(i=0;i<9;i++)
			{
				for(j=0;j<9;j++)
					System.out.print(puzzle[0][i][j]+" ");
				System.out.println();
			}
		long end = System.currentTimeMillis();
		System.out.println("\n\n\nTime Elapsed is "+(end - start)+" milliseconds.\n");
	}
}