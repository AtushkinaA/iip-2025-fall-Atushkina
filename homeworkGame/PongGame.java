package impl.logic;

import impl.GameService;
import impl.model.PixelColor;

public class PongGame implements GameLogic {

    private int width = 0;
    private int height = 0;
    private boolean initialized = false;

    // Ракетки
    private final int paddleHeightDefault = 6;
    private int paddleHeight;
    private int leftPaddleY;
    private int rightPaddleY;
    private final int leftPaddleXOffset = 1;
    private final int rightPaddleXOffset = 2;

    // Мяч
    private double ballX;
    private double ballY;
    private double ballVx;
    private double ballVy;
    private double ballSpeed = 0.9;

    // Счёт
    private int scoreLeft = 0;
    private int scoreRight = 0;

    // Состояние паузы
    private boolean paused = false;

    @Override
    public void init() {
        if (width <= 0 || height <= 0) return;
        paddleHeight = Math.max(3, Math.min(paddleHeightDefault, height / 4));
        leftPaddleY = (height - paddleHeight) / 2;
        rightPaddleY = (height - paddleHeight) / 2;
        resetBall(true);
        scoreLeft = 0;
        scoreRight = 0;
        paused = false;
        initialized = true;
    }

    @Override
    public void loop(PixelColor[][] pixels) {
        if (pixels == null || pixels.length == 0 || pixels[0].length == 0) return;
        if (!initialized) {
            this.height = pixels.length;
            this.width = pixels[0].length;
            init();
        }

        clear(pixels, PixelColor.BLACK);

        // Если пауза — рисуем текущий кадр без обновления логики
        if (!paused) {
            updatePhysics();
        }

        drawCenterLine(pixels);

        drawPaddles(pixels);
        drawBall(pixels);

        drawScoreBar(pixels);
    }

    @Override
    public void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case 38: // VK_UP
            case 87: // W
                moveLeftPaddle(-1);
                break;
            case 40: // VK_DOWN
            case 83: // S
                moveLeftPaddle(1);
                break;
            case 82: // R - перезапуск
                init();
                break;
            case 32: // Space - пауза/продолжение
                paused = !paused;
                break;
            default:
                // игнорируем другие клавиши
                break;
        }
    }

    private void updatePhysics() {
        // Простая AI логика для правой ракетки
        int aiCenter = rightPaddleY + paddleHeight / 2;
        int ballCenterY = (int)Math.round(ballY);
        if (aiCenter < ballCenterY) {
            rightPaddleY = Math.min(rightPaddleY + 1, height - paddleHeight);
        } else if (aiCenter > ballCenterY) {
            rightPaddleY = Math.max(rightPaddleY - 1, 0);
        }

        // Обновляем положение мяча
        ballX += ballVx;
        ballY += ballVy;

        if (ballY < 0) {
            ballY = 0;
            ballVy = -ballVy;
        } else if (ballY >= height) {
            ballY = height - 1;
            ballVy = -ballVy;
        }

        // Проверка столкновения с левой ракеткой
        int leftPaddleX = leftPaddleXOffset;
        if ((int)Math.round(ballX) <= leftPaddleX + 0) {
            int by = (int)Math.round(ballY);
            if (by >= leftPaddleY && by < leftPaddleY + paddleHeight) {
                ballX = leftPaddleX + 1;
                reflectFromPaddle(leftPaddleY);
            } else {
                scoreRight++;
                resetBall(true);
            }
        }

        int rightPaddleX = width - rightPaddleXOffset - 1;
        if ((int)Math.round(ballX) >= rightPaddleX - 0) {
            int by = (int)Math.round(ballY);
            if (by >= rightPaddleY && by < rightPaddleY + paddleHeight) {
                ballX = rightPaddleX - 1;
                reflectFromPaddle(rightPaddleY);
            } else {
                scoreLeft++;
                resetBall(false);
            }
        }
    }

    // Отражение мяча при попадании в ракетку;
    private void reflectFromPaddle(int paddleTopY) {
        double relative = (ballY - (paddleTopY + paddleHeight / 2.0)) / (paddleHeight / 2.0);
        // ограничиваем
        if (relative > 1.0) relative = 1.0;
        if (relative < -1.0) relative = -1.0;
        double speed = Math.sqrt(ballVx * ballVx + ballVy * ballVy);
        double newVy = relative * speed * 0.9;
        double newVx = Math.signum(ballVx) * -1.0 * Math.abs(Math.sqrt(Math.max(0.01, speed * speed - newVy * newVy)));
        ballVx = newVx;
        ballVy = newVy;
        // Немного ускоряем мяч с каждой отскок/удачным попаданием
        ballVx *= 1.04;
        ballVy *= 1.04;
        double maxSpeed = Math.max(1.6, ballSpeed * 3.0);
        double curSpeed = Math.sqrt(ballVx * ballVx + ballVy * ballVy);
        if (curSpeed > maxSpeed) {
            double scale = maxSpeed / curSpeed;
            ballVx *= scale;
            ballVy *= scale;
        }
    }

    private void resetBall(boolean towardRight) {
        ballX = width / 2.0;
        ballY = height / 2.0;
        double angle = (Math.random() * 0.6 - 0.3);
        ballVx = (towardRight ? 1 : -1) * ballSpeed * (0.8 + Math.random() * 0.6);
        ballVy = angle * ballSpeed * (0.8 + Math.random() * 0.6);
    }

    private void moveLeftPaddle(int dy) {
        leftPaddleY += dy;
        if (leftPaddleY < 0) leftPaddleY = 0;
        if (leftPaddleY > height - paddleHeight) leftPaddleY = height - paddleHeight;
    }

    // Рисование
    private void drawPaddles(PixelColor[][] pixels) {
        int leftX = leftPaddleXOffset;
        int rightX = width - rightPaddleXOffset - 1;
        for (int i = 0; i < paddleHeight; i++) {
            int y1 = leftPaddleY + i;
            if (inBounds(leftX, y1, pixels)) pixels[y1][leftX] = PixelColor.WHITE;
            int y2 = rightPaddleY + i;
            if (inBounds(rightX, y2, pixels)) pixels[y2][rightX] = PixelColor.WHITE;
        }
    }

    private void drawBall(PixelColor[][] pixels) {
        int bx = (int)Math.round(ballX);
        int by = (int)Math.round(ballY);
        if (inBounds(bx, by, pixels)) {
            pixels[by][bx] = PixelColor.YELLOW;
        }
    }

    private void drawCenterLine(PixelColor[][] pixels) {
        for (int y = 0; y < height; y++) {
            if ((y % 2) == 0) {
                int cx = width / 2;
                if (inBounds(cx, y, pixels)) pixels[y][cx] = PixelColor.BLUE;
            }
        }
    }

    private void drawScoreBar(PixelColor[][] pixels) {
        int maxDisplay = Math.max(1, (width - 4) / 2);
        int leftDisplay = Math.min(scoreLeft, maxDisplay);
        int rightDisplay = Math.min(scoreRight, maxDisplay);
        for (int i = 0; i < leftDisplay; i++) {
            int x = 2 + i;
            if (inBounds(x, 0, pixels)) pixels[0][x] = PixelColor.GREEN;
        }
        for (int i = 0; i < rightDisplay; i++) {
            int x = width - 3 - i;
            if (inBounds(x, 0, pixels)) pixels[0][x] = PixelColor.RED;
        }
    }

    private void clear(PixelColor[][] pixels, PixelColor color) {
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                pixels[y][x] = color;
            }
        }
    }

    private boolean inBounds(int x, int y, PixelColor[][] pixels) {
        return y >= 0 && y < pixels.length && x >= 0 && x < pixels[y].length;
    }
}

