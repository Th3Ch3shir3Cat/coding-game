package FifteenGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class GameOfFifteen extends JPanel implements ActionListener {

    private int size;
    private int nbTiles;
    private int dimension;
    private static final Color FOREGROUND_COLOR = new Color(239, 83, 80); // we use arbitrary color
    private static final Random RANDOM = new Random();
    private int[] tiles;
    private int tileSize;
    private int blankPos;
    private int margin;
    private int gridSize;
    private boolean gameOver; // true если конец игре, false иначе


    private JButton start;
    public Timer timer;
    private static final int VELOCIDAD = 1000;

    private Solve solve; //Решение
    private List<Board> result;
    public int indexSolve; //Индекс для прохождения по решению

    private MainFrameFifteen fifteen;

    public GameOfFifteen(int size, int dim, int mar, MainFrameFifteen fifteen) {
        this.fifteen = fifteen;

        this.size = size;
        dimension = dim;
        margin = mar;
        this.indexSolve = 0;
        nbTiles = size * size - 1;
        tiles = new int[size * size];

        // вычисляем размер поля по великим формулам
        gridSize = (dim - 2 * margin);
        tileSize = gridSize / size;

        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(Color.WHITE);
        setForeground(FOREGROUND_COLOR);
        setFont(new Font("SansSerif", Font.BOLD, 60));

        gameOver = true;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) {
                    newGame();
                } else {
                    // позиция клика
                    int ex = e.getX() - margin;
                    int ey = e.getY() - margin;

                    // click in the grid ?
                    if (ex < 0 || ex > gridSize  || ey < 0  || ey > gridSize)
                        return;

                    // get position in the grid
                    int c1 = ex / tileSize;
                    int r1 = ey / tileSize;

                    // get position of the blank cell
                    int c2 = blankPos % size;
                    int r2 = blankPos / size;

                    // конвертируем в 1д координаты
                    int clickPos = r1 * size + c1;

                    int dir = 0;

                    // we search direction for multiple tile moves at once
                    if (c1 == c2  &&  Math.abs(r1 - r2) > 0)
                        dir = (r1 - r2) > 0 ? size : -size;
                    else if (r1 == r2 && Math.abs(c1 - c2) > 0)
                        dir = (c1 - c2) > 0 ? 1 : -1;

                    if (dir != 0) {
                        // движение это жизнь
                        do {
                            int newBlankPos = blankPos + dir;
                            tiles[blankPos] = tiles[newBlankPos];
                            blankPos = newBlankPos;
                        } while(blankPos != clickPos);
                        tiles[blankPos] = 0;
                    }

                    // чекаем можно ли решать дальше
                    gameOver = isSolved();
                }

                // перерисуем панель
                repaint();
            }
        });
        timer = new Timer(VELOCIDAD, this);


        newGame();

    }

    private void newGame() {
        do {
            reset(); // reset in intial state
            shuffle(); // shuffle
        } while(!isSolvable()); // make it until grid be solvable

        gameOver = false;
    }

    private void reset() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }

        // we set blank cell at the last
        blankPos = tiles.length - 1;
    }

    private void shuffle() {
        // don't include the blank tile in the shuffle, leave in the solved position
        int n = nbTiles;

        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
    }

    // Only half permutations of the puzzle are solvable.
    // Whenever a tile is preceded by a tile with higher value it counts
    // as an inversion. In our case, with the blank tile in the solved position,
    // the number of inversions must be even for the puzzle to be solvable
    private boolean isSolvable() {
        int countInversions = 0;

        for (int i = 0; i < nbTiles; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i])
                    countInversions++;
            }
        }

        return countInversions % 2 == 0;
    }

    public int[] getTiles(){
        return this.tiles;
    }

    private boolean isSolved() {
        if (tiles[tiles.length - 1] != 0) // if blank tile is not in the solved position ==> not solved
            return false;

        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1)
                return false;
        }

        return true;
    }

    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < tiles.length; i++) {
            // we convert 1D coords to 2D coords given the size of the 2D Array
            int r = i / size;
            int c = i % size;
            // we convert in coords on the UI
            int x = margin + c * tileSize;
            int y = margin + r * tileSize;

            // check special case for blank tile
            if(tiles[i] == 0) {
                if (gameOver) {
                    g.setColor(FOREGROUND_COLOR);
                    drawCenteredString(g, "\u2713", x, y);
                }

                continue;
            }

            // for other tiles
            g.setColor(getForeground());
            g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(tiles[i]), x , y);
        }
    }

    private void drawStartMessage(Graphics2D g) {
        if (gameOver) {
            g.setFont(getFont().deriveFont(Font.BOLD, 18));
            g.setColor(FOREGROUND_COLOR);
            String s = "Click to start new game";
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2,
                    getHeight() - margin);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y) {
        // center string s for the given tile (x,y)
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g.drawString(s,  x + (tileSize - fm.stringWidth(s)) / 2,
                y + (asc + (tileSize - (asc + desc)) / 2));
    }

    public void getInfoAboutSituation(){
        int [] arr1 = this.getTiles();
        int[][] situationOnBoard = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                situationOnBoard[i][j] = arr1[i*size + j];
            }
        }

        Board initial = new Board(situationOnBoard);
        this.solve = new Solve(initial);
        this.result = solve.getSolution();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2D);
        drawStartMessage(g2D);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    this.tiles[i * size + j] = result.get(indexSolve).getBlocks()[i][j];
                    if(this.tiles[i*size + j] == 0)
                        blankPos = i*size+j;
                }
            }
            indexSolve++;
            if (indexSolve == result.size()) {
                timer.stop();
                fifteen.resultComplete();
            }
            repaint();
    }


    public void startAnimacion() {
        timer.restart();
    }


    public void pauseAnimacion() {
        timer.stop();
    }
}
