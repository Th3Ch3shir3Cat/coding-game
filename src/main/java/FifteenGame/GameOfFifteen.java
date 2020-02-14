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

    private String[] strForMove;
    private int numberOfStep;
    private int k;

    private JButton start;
    public Timer timer;
    private static final int VELOCIDAD = 1000;

    private Solve solve; //Решение
    private List<Board> result;
    public int indexSolve; //Индекс для прохождения по решению

    private MainFrameFifteen fifteen;

    public GameOfFifteen(final int size, int dim, int mar, MainFrameFifteen fifteen) {
        this.fifteen = fifteen;
        this.k = 0;
        this.numberOfStep = 0;
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

                    // попал в поле или нет
                    if (ex < 0 || ex > gridSize  || ey < 0  || ey > gridSize)
                        return;

                    // вычисляем позицию клика
                    int c1 = ex / tileSize;
                    int r1 = ey / tileSize;


                    // даём позицию пустой клетке
                    int c2 = blankPos % size;
                    int r2 = blankPos / size;
                    //чекаем возможность на движение
                    checkOnCanMove(c1,c2,r1,r2);
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

    public void setStrForMove(String[] str){
        this.strForMove = str;
        k = 1;
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

        blankPos = tiles.length - 1;
    }

    private void shuffle() {
        int n = nbTiles;

        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
    }

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
        if (tiles[tiles.length - 1] != 0)
            return false;

        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1)
                return false;
        }

        return true;
    }

    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < tiles.length; i++) {
            int r = i / size;
            int c = i % size;
            int x = margin + c * tileSize;
            int y = margin + r * tileSize;

            if(tiles[i] == 0) {
                if (gameOver) {
                    g.setColor(FOREGROUND_COLOR);
                    drawCenteredString(g, "\u2713", x, y);
                }

                continue;
            }

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
            String s = "Заново";
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2,
                    getHeight() - margin);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y) {

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

    public boolean checkOnCanMove(int c1, int c2, int r1, int r2){
        // конвертируем в 1д координаты
        int clickPos = r1 * size + c1;
        System.out.println(clickPos);
        int dir = 0;

        //ищем направление для нескольких ходов плитки одновременно
        if (c1 == c2 && Math.abs(r1 - r2) > 0)
            dir = (r1 - r2) > 0 ? size : -size;
        else if (r1 == r2 && Math.abs(c1 - c2) > 0)
            dir = (c1 - c2) > 0 ? 1 : -1;

        if (dir != 0) {
            // и опять в движении
            do {
                int newBlankPos = blankPos + dir;
                tiles[blankPos] = tiles[newBlankPos];
                blankPos = newBlankPos;
            } while (blankPos != clickPos);
            tiles[blankPos] = 0;
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(k == 1) {
            int x = 0, y = 0, c1 = 0, r1 = 0;
            for (int i = 0; i < tiles.length; i++) {
                if (fifteen.masNumber[numberOfStep] == tiles[i]) {
                    int r = i / size;
                    int c = i % size;
                    x = margin + c * tileSize;
                    y = margin + r * tileSize;
                }
            }
            c1 = x / tileSize;
            r1 = y / tileSize;
            int c2 = blankPos % size;
            int r2 = blankPos / size;
            if(checkOnCanMove(c1,c2,r1,r2) && !gameOver) {
                repaint();
                numberOfStep++;
                if (numberOfStep == strForMove.length) {
                    timer.stop();
                    fifteen.resultComplete();
                    numberOfStep = 0;
                }
                gameOver = isSolved();
            }
            else{
                if (gameOver){
                    JOptionPane.showMessageDialog(this,
                            "Случилось страшное, игра зашла в тупик :(\n" +
                                    "Но не расстраивайтесь!!!\n" +
                                    "Половина всех комбинаций нерешаема, так что самое время\n" +
                                    "начать сначала!!!");
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "К сожалению действие Move(" + fifteen.masNumber[numberOfStep] + ") на данном шаге\n" +
                                    "          не может быть выполненно");
                }
                timer.stop();
            }
        }else {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        this.tiles[i * size + j] = result.get(indexSolve).getBlocks()[i][j];
                        if (this.tiles[i * size + j] == 0)
                            blankPos = i * size + j;
                    }
                }
                indexSolve++;
                if (indexSolve == result.size()) {
                    timer.stop();
                    fifteen.resultComplete();
                }
                repaint();
            }
        }

    public void startAnimacion() {
        timer.restart();
    }


    public void pauseAnimacion() {
        timer.stop();
    }
}
