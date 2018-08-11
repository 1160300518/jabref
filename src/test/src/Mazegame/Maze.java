package Mazegame;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.*;

public class Maze extends Applet
{
	private static final long serialVersionUID = 1L;
	int mazeCol = 10;//can change
	int mazeRow = 10;
	int width = 25;
	int zeroX = 10;
	int zeroY = 10;
	int countCell = 0;
	int visitedCount = 0;
	int mazeHeight = zeroY + mazeRow*width;
	int mazeWidth = zeroX + mazeCol*width;
	long begintime,endtime;
	Cell[][] map = null;
	Cell[] visitedCell = null;
	int startpoint;
	String limit;
	static final Color wall_color = Color.BLUE;
	Random r;
	Ball ball = null;
	private Image iBuffer;
	private Graphics gBuffer;
	public void update(Graphics g) {
		if (iBuffer == null) {
			iBuffer = createImage(mazeWidth + 100, mazeHeight+100);
			gBuffer = iBuffer.getGraphics();
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, mazeWidth + 100, mazeHeight+100);
		paint(gBuffer);
		g.drawImage(iBuffer, 0, 0, this);
	}
	public void init(){
		super.init();
		String user = startMaze();
		String[] str = user.split(" ");
		mazeCol = Integer.parseInt(str[0]);
		mazeRow = Integer.parseInt(str[1]);
		checkMaze(mazeCol,mazeRow);
		limit = Timelimit();
		mazeHeight = zeroY + mazeRow*width;
		mazeWidth = zeroX + mazeCol*width;
		countCell = mazeCol*mazeRow;
		visitedCell = new Cell[countCell];
		r = new Random();
		ball = new Ball();
		map = new Cell[mazeCol][mazeRow];
		Cell cell;
		for(int x=0;x<mazeCol;x++)
		{
			for (int y = 0;y<mazeRow;y++)
			{
				cell = new Cell();
				cell.x = zeroX + x*width;
				cell.y = zeroY + y*width;
				cell.row = y;
				cell.col = x;
				map[x][y] = cell;
			}
		}
		startpoint = r.nextInt(3);
		if (startpoint == 0) { 
			ball.ballx = 0; 
			ball.bally = 0; 
		}
		if (startpoint == 1) {
			ball.ballx = mazeCol - 1;
			ball.bally = 0;
		}
		if (startpoint == 2) {
			ball.ballx = 0;
			ball.bally = mazeRow - 1;
		}
		begintime = System.currentTimeMillis();
		this.addKeyListener(ball);
	}
	public void paint(Graphics g) {
		creatMaze();
		paintMaze(g);
		ball.paint(g);
		g.setColor(Color.BLACK);
		g.fillOval(zeroX + (mazeCol-1)*width,zeroY + (mazeRow - 1)*width,width,width);
		g.setFont(new Font("宋体",Font.BOLD,16));
		g.drawString("请控制红球去找黑色的球。", mazeWidth, 30);
		g.drawString("Time:", mazeWidth + 50, 90);
		if (isWin()==false) 
		{
			endtime = (System.currentTimeMillis()-begintime)/1000;
			g.drawString(String.valueOf(endtime), mazeWidth + 50, 120);
		}
		if(endtime>Integer.parseInt(limit)) 
		{
			JOptionPane.showMessageDialog(getParent(),"你输 了。。我能怎么办，我也很绝望啊。。");
			System.exit(0);
		}
		repaint();
	}
	String startMaze() {
		String s = JOptionPane.showInputDialog(getParent(),"请输入你想要的列和行（行列均至少为2，以空格为界）");
		if ( s == null)
			return "20 20";
		return s;
	}
	String Timelimit() {
		String time = JOptionPane.showInputDialog(getParent(),"你想挑战多少秒通关？");
		if(time == null || time.equals("0")) {
			JOptionPane.showMessageDialog(getParent(), "emmmm...那就40秒吧");
			return "40";
		}
		return time;
	}
	void checkMaze(int a,int b) {
		if (a <= 1 || b<= 1) {
			JOptionPane.showMessageDialog(getParent(), "emmmmm....MMP");
			System.exit(0);
		}
	}
	void creatMaze() {
		initMaze(map[r.nextInt(mazeCol)][r.nextInt(mazeRow)]);
	}
	void initMaze(Cell cell) {
		if (visitedCount == countCell)
			return;
		if (cell.visited == false)
		{
			cell.visited = true;
			visitedCell[visitedCount++] = cell;
		}
		Cell[] neibCell = new Cell[4];
		int neibCount = 0;
		Cell nextCell;
		//left
		if (cell.col - 1 >= 0 && !(map[cell.col-1][cell.row].visited))
		{
			neibCell[neibCount++] = map[cell.col - 1][cell.row];
		}
		//top
		if (cell.row - 1 >= 0 && !(map[cell.col][cell.row-1].visited))
		{
			neibCell[neibCount++] = map[cell.col][cell.row - 1];
		}
		//right
		if (cell.col + 1 < mazeCol && !(map[cell.col+1][cell.row].visited))
		{
			neibCell[neibCount++] = map[cell.col + 1][cell.row];
		}
		//down
		if (cell.row + 1 < mazeRow && !(map[cell.col][cell.row+1].visited))
		{
			neibCell[neibCount++] = map[cell.col][cell.row + 1];
		}
		//如果周围都被访问过
		if(neibCount == 0)
	    {
	      int i = r.nextInt(visitedCount);
	      initMaze(visitedCell[i]);
	      return;
	    }
		nextCell = neibCell[r.nextInt(neibCount)];
		//delete the wall
		int left = 0,top = 1,right = 2,down = 3;
		//left
		if (nextCell.col < cell.col) 
		{
			cell.wall[left] = 0;
			nextCell.wall[right] = 0;
		}
		//top
		if (nextCell.row < cell.row) 
		{
			cell.wall[top] = 0;
			nextCell.wall[down] = 0;
		}
		//right
		if (nextCell.col > cell.col)
		{
			cell.wall[right] = 0;
			nextCell.wall[left] = 0;
		}
		//down
		if (nextCell.row > cell.row)
		{
			cell.wall[down] = 0;
			nextCell.wall[top] = 0;
		}
		initMaze(nextCell);
	}
	void paintMaze(Graphics g){
		for(int x=0;x<mazeCol;x++)
		{
			for (int y = 0;y<mazeRow;y++)
			{
				map[x][y].paint(g);
			}
		}
	}
	boolean isWall(Cell cell,int n) {
		if (cell.wall[n] == 1) return true;
		return false;
	}
	boolean isWin() {
		if ((ball.ballx == mazeCol-1) && (ball.bally == mazeRow -1)) return true;
		return false;
	}
	class Cell{
	  /*Left = 0;
	  Top = 1;
	  Right = 2;
	  Down = 3;
	  1 stands for having wall
	  0 stands for no wall*/
	  int x = 0;
	  int y = 0;
	  int col = 0;
	  int row = 0;
	  boolean visited = false;
	  int[] wall = {1,1,1,1};
	  void paint(Graphics g) {
		  g.setColor(wall_color);
		  int x1 = 0,x2 = 0,y1 = 0,y2 = 0;
		  for (int i = 0;i<4;i++)
		  {
			  if (wall[i] == 0)
				  continue;
			  else 
			  {
				  switch(i) 
				  {
				  	case 0:
				  		x1 = x;
				  		y1 = y;
				  		x2 = x;
				  		y2 = y + width;
				  		break;
				  	case 1:
				  		x1 = x;
				  		y1 = y;
				  		x2 = x + width;
				  		y2 = y;
				  		break;
				  	case 2:
				  		x1 = x + width;
				  		y1 = y;
				  		x2 = x + width;
				  		y2 = y + width;
				  		break;
				  	case 3:
				  		x1 = x + width;
				  		y1 = y + width;
				  		x2 = x;
				  		y2 = y + width;
				  		break;
				  }
				  g.drawLine(x1, y1, x2, y2);
			  }
		  }
	  }
  }
	class Ball implements KeyListener{
		int ballx;
		int bally;
		public void paint(Graphics g) {
			g.setColor(Color.RED);
			g.fillOval(zeroX + ballx*width, zeroY+bally*width, width, width);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (isWall(map[ballx][bally],0) == false)
					ballx--;
				break;
			case KeyEvent.VK_UP:
				if (isWall(map[ballx][bally],1) == false)
					bally--;
				break;
			case KeyEvent.VK_RIGHT:
				if (isWall(map[ballx][bally],2) == false)
					ballx++;
				break;
			case KeyEvent.VK_DOWN:
				if (isWall(map[ballx][bally],3) == false)
					bally++;
				break;
			}
			repaint();
			if(isWin()) {
				JOptionPane.showConfirmDialog(getParent(),
					"You win!Time used:"+endtime, "Result", JOptionPane.CANCEL_OPTION);
				System.exit(0);
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}