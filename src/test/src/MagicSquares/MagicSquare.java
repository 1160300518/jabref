package MagicSquares;

import java.io.*;

public class MagicSquare {
	static String filepath = System.getProperty("user.dir")+"\\src";
	public static void main(String args[]) {
		
		String filepath1 = filepath + "\\P1\\1.txt";
		String filepath2 = filepath + "\\P1\\2.txt";
		String filepath3 = filepath + "\\P1\\3.txt";
		String filepath4 = filepath + "\\P1\\4.txt";
		String filepath5 = filepath + "\\P1\\5.txt";
		String filepath6 = filepath + "\\P1\\6.txt";

		MagicSquare magicsquare = new MagicSquare();
		System.out.println(magicsquare.isLegalMagicSquare(filepath1));
		System.out.println(magicsquare.isLegalMagicSquare(filepath2));
		System.out.println(magicsquare.isLegalMagicSquare(filepath3));
		System.out.println(magicsquare.isLegalMagicSquare(filepath4));
		System.out.println(magicsquare.isLegalMagicSquare(filepath5));
		generateMagicSquare(5);
		generateMagicSquare(4);
		generateMagicSquare(-1);
		System.out.println(magicsquare.isLegalMagicSquare(filepath6));
	}
	public boolean isLegalMagicSquare(String fileName) {
		final int N = 150;
		File file = new File(fileName);
		BufferedReader reader = null;
		String value[][] = new String[N][N];
		int flag = 0;
		int line = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String nextline;
			while((nextline = reader.readLine()) != null) {
				value[line] = nextline.split("\t");
				for(int i=0;i<value[line].length;i++)
				{
					if(!value[line][i].matches("^(\\-|\\+)?\\d+(\\.\\d+)?$")) {
						System.out.print("数字间并非使用\\t进行分割，");
						reader.close();
						return false;
					}
				}
				line++;
			}			
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		int sum = 0;
		for(int i=0;i<line;i++) {
			sum += Integer.valueOf(value[0][i]);
			if(line != value[i].length) {
				System.out.print("并非矩阵，");
				return false;
			}
		}
		int newsum_row;
		int newsum_col;
		for(int i=1;i<line;i++)
		{
			newsum_row = 0;
			newsum_col = 0;
			for(int j=0;j<line;j++)
			{
				if(value[i][j].matches("[0-9]+")&&value[j][i].matches("[0-9]+")) {
					newsum_row += Integer.valueOf(value[i][j]);	
					newsum_col += Integer.valueOf(value[j][i]);
				}
				else {
					System.out.print("有非正整数，");
					return false;
				}
			}
			if(newsum_row != sum || newsum_col != sum) flag = 1;
		}
		int newsum_dia_1 = 0;
		int newsum_dia_2 = 0;
		for(int i=0;i<line;i++) {
			if(value[i][i].matches("[0-9]+")&&value[i][line-i-1].matches("[0-9]+")) {
				newsum_dia_1 += Integer.valueOf(value[i][i]);
				newsum_dia_2 += Integer.valueOf(value[i][line-i-1]);
			}
			else {
				System.out.print("有非正整数，");
				return false;
			}
		}
		if(newsum_dia_1 != sum || newsum_dia_2 != sum) flag = 1;
		if (flag == 1) return false;
		return true;
	}
	public static boolean generateMagicSquare(int n) { 
		if(n % 2 ==0) {
			System.out.println("n为偶数");
			return false;
		}
		if(n < 0) {
			System.out.println("n为负数");
			return false;
		}
		int magic[][] = new int[n][n];   
		int row = 0, col = n / 2, i, j, square = n * n; 
		  
		File file = new File(filepath + "\\P1\\6.txt");
		  
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();
		}
		try {
			FileWriter fileWriter = new FileWriter(file);
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					fileWriter.write(magic[i][j] + "\t");
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
