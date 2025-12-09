public class ArtificialIntelligencePlayerV1 extends AbstractPlayer {
    @Override
    public void doTurn() {
        System.out.println("ArtificialIntelligenceV1 doing turn...");
        int pos = 5;

        while (gameService.getSymbolByLocation(pos) != Symbol.NONE) {
            int originalPos = pos;

            if (gameService.getSymbolByLocation(6) == Symbol.O &&
                    gameService.getSymbolByLocation(4) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 4;
            } else if (gameService.getSymbolByLocation(1) == Symbol.O &&
                        gameService.getSymbolByLocation(9) == Symbol.NONE &&
                        gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 9;
            } else if (gameService.getSymbolByLocation(3) == Symbol.O &&
                    gameService.getSymbolByLocation(7) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 7;
            } else if (gameService.getSymbolByLocation(2) == Symbol.O &&
                    gameService.getSymbolByLocation(8) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 8;
            } else if (gameService.getSymbolByLocation(7) == Symbol.O &&
                    gameService.getSymbolByLocation(3) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 3;
            } else if (gameService.getSymbolByLocation(7) == Symbol.O &&
                    gameService.getSymbolByLocation(9) == Symbol.NONE &&
                    gameService.getSymbolByLocation(8) == Symbol.O) {
                pos = 9;
            } else if (gameService.getSymbolByLocation(3) == Symbol.X &&
                    gameService.getSymbolByLocation(9) == Symbol.NONE &&
                    gameService.getSymbolByLocation(6) == Symbol.X) {
                pos = 9;
            } else if (gameService.getSymbolByLocation(3) == Symbol.X &&
                    gameService.getSymbolByLocation(6) == Symbol.NONE &&
                    gameService.getSymbolByLocation(9) == Symbol.X) {
                pos = 6;
            } else if (gameService.getSymbolByLocation(9) == Symbol.X &&
                    gameService.getSymbolByLocation(3) == Symbol.NONE &&
                    gameService.getSymbolByLocation(6) == Symbol.X) {
                pos = 3;
            } else if (gameService.getSymbolByLocation(1) == Symbol.X &&
                    gameService.getSymbolByLocation(2) == Symbol.NONE &&
                    gameService.getSymbolByLocation(3) == Symbol.X) {
                pos = 2;
            } else if (gameService.getSymbolByLocation(1) == Symbol.X &&
                    gameService.getSymbolByLocation(3) == Symbol.NONE &&
                    gameService.getSymbolByLocation(2) == Symbol.X) {
                pos = 3;
            } else if (gameService.getSymbolByLocation(3) == Symbol.X &&
                    gameService.getSymbolByLocation(1) == Symbol.NONE &&
                    gameService.getSymbolByLocation(2) == Symbol.X) {
                pos = 1;
            } else if (gameService.getSymbolByLocation(4) == Symbol.X &&
                    gameService.getSymbolByLocation(1) == Symbol.NONE &&
                    gameService.getSymbolByLocation(7) == Symbol.X) {
                pos = 1;
            } else if (gameService.getSymbolByLocation(1) == Symbol.X &&
                    gameService.getSymbolByLocation(4) == Symbol.NONE &&
                    gameService.getSymbolByLocation(7) == Symbol.X) {
                pos = 4;
            } else if (gameService.getSymbolByLocation(4) == Symbol.X &&
                    gameService.getSymbolByLocation(7) == Symbol.NONE &&
                    gameService.getSymbolByLocation(1) == Symbol.X) {
                pos = 7;
            } else if (gameService.getSymbolByLocation(2) == Symbol.X &&
                    gameService.getSymbolByLocation(1) == Symbol.NONE &&
                    gameService.getSymbolByLocation(3) == Symbol.X) {
                pos = 1;
            } else if (gameService.getSymbolByLocation(7) == Symbol.X &&
                    gameService.getSymbolByLocation(9) == Symbol.NONE &&
                    gameService.getSymbolByLocation(8) == Symbol.X) {
                pos = 9;
            } else if (gameService.getSymbolByLocation(7) == Symbol.X &&
                    gameService.getSymbolByLocation(8) == Symbol.NONE &&
                    gameService.getSymbolByLocation(9) == Symbol.X) {
                pos = 8;
            } else if (gameService.getSymbolByLocation(9) == Symbol.X &&
                    gameService.getSymbolByLocation(7) == Symbol.NONE &&
                    gameService.getSymbolByLocation(8) == Symbol.X) {
                pos = 7;
            } else if (gameService.getSymbolByLocation(3) == Symbol.X &&
                    gameService.getSymbolByLocation(9) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 9;
            } else if (gameService.getSymbolByLocation(9) == Symbol.X &&
                    gameService.getSymbolByLocation(1) == Symbol.NONE &&
                    gameService.getSymbolByLocation(5) == Symbol.O) {
                pos = 1;
            } else {
                pos++;
                if (pos > 9) pos = 1;
            }

            if (pos == originalPos) {
                for (int i = 1; i <= 9; i++) {
                    if (gameService.getSymbolByLocation(i) == Symbol.NONE) {
                        pos = i;
                        break;
                    }
                }
                break;
            }
        }

        gameService.doTurn(symbol, pos);
    }
}