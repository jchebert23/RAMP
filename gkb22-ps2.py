from engines import Engine
from copy import deepcopy
from board import Board

class StudentEngine(Engine):
    """ Game engine that you should you as skeleton code for your
    implementation. """

    alpha_beta = False
    depth = 0
    count = 0
    maxPlayer = 1
    color = 1
    countNodes = 0
    move = ()
    minimaxbranchingFactor = 0
    maxbranchFactor = 0
    minbranchFactor = 0
    maxfactors = []
    moveNum = 0
    avgRuntime = []
    timeRemainingPrev = 0
    boards = set()
    dupeCount = 0

    def get_move(self, board, color, move_num=None,
                 time_remaining=None, time_opponent=None):
        """ Wrapper function that chooses either vanilla minimax or
        alpha-beta. """
        f = self.get_ab_minimax_move if self.alpha_beta else self.get_minimax_move
        return f(board, color, move_num, time_remaining, time_opponent)

    def get_minimax_move(self, board, color, move_num=None,
                 time_remaining=None, time_opponent=None):
        StudentEngine.depth = 0
        StudentEngine.color = color
        StudentEngine.moveNum = move_num
        moves = board.get_legal_moves(color)
        move_scores = {}
        for move in moves:
            boardCopy = deepcopy(board)
            boardCopy.execute_move(move, color)
            move_scores[move] = self.get_min_val(boardCopy, -1*color, move, 2)
        v = list(move_scores.values())
        k = list(move_scores.keys())
        return k[v.index(max(v))]

    def get_max_val(self, board, color, move, depth):
        if depth == 0:
            return self.evaluate(board, StudentEngine.color)
        val = float("-inf")
        for action in board.get_legal_moves(color):
            boardCopy = deepcopy(board)
            boardCopy.execute_move(action, color)
            get_min_val_result = self.get_min_val(boardCopy, -1*color, action, depth - 1)
            val = max(val, get_min_val_result)
        return val

    def get_min_val(self, board, color, move, depth):
        if depth == 0:
            return self.evaluate(board, StudentEngine.color)
        val = float("inf")
        for action in board.get_legal_moves(color):
            boardCopy = deepcopy(board)
            boardCopy.execute_move(action, color)
            val = min(val, self.get_max_val(boardCopy, -1*color, action, depth - 1))
        return val

    def evaluate(self, board, color):
        coins = self.calculate_coins(board, color)
        corners = self.calculate_corners(board, color)
        return 25*coins + 30*corners

    def get_ab_minimax_move(self, board, color, move_num=None,
                 time_remaining=None, time_opponent=None):
        """ Skeleton code from greedy.py to get you started. """
        # Get a list of all legal moves.
        StudentEngine.depth = 0
        StudentEngine.color = color
        StudentEngine.moveNum = move_num
        moves = board.get_legal_moves(color)
        a = float("-inf")
        b = float("inf")
        move_scores = {}
        for move in moves:
            boardCopy = deepcopy(board)
            boardCopy.execute_move(move, color)
            move_scores[move] = self.get_ab_min_val(boardCopy, -1*color, 2, a, b)
        v = list(move_scores.values())
        k = list(move_scores.keys())
        return k[v.index(max(v))]

    def get_ab_max_val(self, board, color, depth, a, b):
        if depth == 0:
            evalu = self.evaluate(board, StudentEngine.color)
            return evalu
        val = float("-inf")
        moves = board.get_legal_moves(color)
        for action in moves:
            boardCopy = deepcopy(board)
            boardCopy.execute_move(action, color)
            eval = self.get_ab_min_val(boardCopy, -1*color, depth - 1, a, b)
            val = max(val, eval)
            a = max(a, eval)
            if b <= a:
                break
        return val

    def get_ab_min_val(self, board, color, depth, a, b):
        if depth == 0:
            evalu = self.evaluate(board, StudentEngine.color)
            return evalu
        val = float("inf")
        moves = board.get_legal_moves(color)
        for action in moves:
            boardCopy = deepcopy(board)
            boardCopy.execute_move(action, color)
            eval = self.get_ab_max_val(boardCopy, -1*color, depth - 1, a, b)
            val = min(val, eval)
            b = min(b, eval)
            if b <= a:
                break
        return val

    def calculate_coins(self, board, color):
        coins_max = board.count(color)
        coins_min = board.count(-1*color)
        if (coins_max + coins_min == 0):
            coins = 0
        else:
            coins = (100*(coins_max - coins_min))/(coins_max + coins_min)
        return coins

    def calculate_corners(self, board, color):
        corners_capt_max = 0
        corners_capt_min = 0
        bad_squares_max = 0
        bad_squares_min = 0
        kindof_bad_squares_min = 0
        kindof_bad_squares_max = 0

        corners = [(0, 0), (7, 7), (0, 7), (7, 0)]
        bad_squares = [(1, 1), (1, 6), (6, 1), (6, 6)]

        for corner in corners:
            if board[corner[0]][corner[1]] == color:
                corners_capt_max = corners_capt_max + 1
            elif board[corner[0]][corner[1]] == -1*color:
                corners_capt_min = corners_capt_min + 1
        corner_val_max = 4*corners_capt_max
        corner_val_min = 4*corners_capt_min
        corners_fin = 0
        if (corner_val_max + corner_val_min) == 0:
            corners_fin = 0
        else:
            corners_fin = 100*(corner_val_max - corner_val_min)/(corner_val_max + corner_val_min)
        return corners_fin

    def _get_cost(self, board, color, move):
        """ Return the difference in number of pieces after the given move
        is executed. """

        # Create a deepcopy of the board to preserve the state of the actual board
        newboard = deepcopy(board)
        newboard.execute_move(move, color)

        # Count the # of pieces of each color on the board
        num_pieces_op = len(newboard.get_squares(color*-1))
        num_pieces_me = len(newboard.get_squares(color))

        # Return the difference in number of pieces
        return num_pieces_me - num_pieces_op



engine = StudentEngine
