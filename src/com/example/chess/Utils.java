package com.example.chess;

public class Utils {

    public static final int SINGLE_PLAYER_MODE = 1;
    public static final int MULTIPLAYER_MODE = 2;
    public static final int PC_FIGHT_MODE = 3;

    public static final int PAWN = 100;
    public static final int KNIGHT = 320;
    public static final int BISHOP = 330;
    public static final int ROOK = 500;
    public static final int QUEEN = 900;
    public static final int KING = 20000;

    public static final byte[][] PAWN_BONUS = {
            {0,  0,  0,  0,  0,  0,  0,  0,},
            {50, 50, 50, 50, 50, 50, 50, 50,},
            {10, 10, 20, 30, 30, 20, 10, 10,},
            {5,  5, 10, 25, 25, 10,  5,  5,},
            {0,  0,  0, 20, 20,  0,  0,  0,},
            {5, -5,-10,  0,  0,-10, -5,  5,},
            {5, 10, 10,-20,-20, 10, 10,  5,},
            {0,  0,  0,  0,  0,  0,  0,  0},
    };

    public static final byte[][] KNIGHT_BONUS = {
            {-50,-40,-30,-30,-30,-30,-40,-50,},
            {-40,-20,  0,  0,  0,  0,-20,-40,},
            {-30,  0, 10, 15, 15, 10,  0,-30,},
            {-30,  5, 15, 20, 20, 15,  5,-30,},
            {-30,  0, 15, 20, 20, 15,  0,-30,},
            {-30,  5, 10, 15, 15, 10,  5,-30,},
            {-40,-20,  0,  5,  5,  0,-20,-40,},
            {-50,-40,-30,-30,-30,-30,-40,-50,},
    };

    public static final byte[][] BISHOP_BONUS = {
            {-20,-10,-10,-10,-10,-10,-10,-20,},
            {-10,  0,  0,  0,  0,  0,  0,-10,},
            {-10,  0,  5, 10, 10,  5,  0,-10,},
            {-10,  5,  5, 10, 10,  5,  5,-10,},
            {-10,  0, 10, 10, 10, 10,  0,-10,},
            {-10, 10, 10, 10, 10, 10, 10,-10,},
            {-10,  5,  0,  0,  0,  0,  5,-10,},
            {-20,-10,-10,-10,-10,-10,-10,-20,},
    };

    public static final byte[][] ROOK_BONUS = {
            {0,  0,  0,  0,  0,  0,  0,  0,},
            {5, 10, 10, 10, 10, 10, 10,  5,},
            {-5,  0,  0,  0,  0,  0,  0, -5,},
            {-5,  0,  0,  0,  0,  0,  0, -5,},
            {-5,  0,  0,  0,  0,  0,  0, -5,},
            {-5,  0,  0,  0,  0,  0,  0, -5,},
            {-5,  0,  0,  0,  0,  0,  0, -5,},
            {0,  0,  0,  5,  5,  0,  0,  0},
    };

    public static final byte[][] QUEEN_BONUS = {
            {-20,-10,-10, -5, -5,-10,-10,-20,},
            {-10,  0,  0,  0,  0,  0,  0,-10,},
            {-10,  0,  5,  5,  5,  5,  0,-10,},
            {-5,  0,  5,  5,  5,  5,  0, -5,},
            {0,  0,  5,  5,  5,  5,  0, -5,},
            {-10,  5,  5,  5,  5,  5,  0,-10,},
            {-10,  0,  5,  0,  0,  0,  0,-10,},
            {-20,-10,-10, -5, -5,-10,-10,-20},
    };

    public static final byte[][] KING_BONUS = {
            {-30,-40,-40,-50,-50,-40,-40,-30,},
            {-30,-40,-40,-50,-50,-40,-40,-30,},
            {-30,-40,-40,-50,-50,-40,-40,-30,},
            {-30,-40,-40,-50,-50,-40,-40,-30,},
            {-20,-30,-30,-40,-40,-30,-30,-20,},
            {-10,-20,-20,-20,-20,-20,-20,-10,},
            {20, 20,  0,  0,  0,  0, 20, 20,},
            {20, 30, 10,  0,  0, 10, 30, 20 },
    };
}
