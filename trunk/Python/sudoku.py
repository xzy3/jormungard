def construct_num_set(begin,end):
    return set(range(begin,end))

class sudoku_board:
    def __init__(self, board_string):
        self.row  = [ construct_num_set(1,10) for j in range(9) ]
        self.col  = [ construct_num_set(1,10) for j in range(9) ]
        self.cell = [ construct_num_set(1,10) for j in range(9) ]

        self.board = [ [ -1  for j in range(9) ] for k in range(9) ]

        i,j = 0,0

        for char in board_string:
            if char != ".":
                num = set([int(char)])
                self.row[j] -= num
                self.col[i] -= num

                self.cell[self.calc_cell(i,j)] -= num

                self.board[j][i] = int(char)

            i = (i + 1) % 9

            if i == 0:
                j = (j + 1) % 9

    def calc_cell(self, i,j):
        return ((i // 3) * 3 + (j // 3))

    def get_possibilities(self, i,j):
        if self.board[j][i] == -1:
            return self.row[j] & self.col[i] & self.cell[self.calc_cell(i,j)]

        else:
            return set([])

    def single_choice(self):
        for i in range(9):
            for j in range(9):
                if self.board[j][i] == -1 and len(self.get_possibilities(i,j)) == 1:
                        return (i, j)

        return (-1,-1)

    def identical_pair_row(self, row):
        for i in range(9):
            first = self.get_possibilites(row, i)

            if len(first) == 2:
                for j in range(9):
                    second = self.get_possibilities(row, j)

                    if len(second) == 2:
                        if first.issubset(second) and first.issuperset(second)

board_string = "...7..8......4..3......9..16..5......1..3..4...5..1..75..2..6...3..8..9...7.....2"

board = sudoku_board(board_string)
print board.single_choice()
