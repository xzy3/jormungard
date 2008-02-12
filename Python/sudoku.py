def construct_num_set(begin,end):
    return set(range(begin,end))

class sudoku_board:
    def __init__(self, board_string):
        self.row  = [ construct_num_set(1,10) for j in range(9) ]
        self.col  = [ construct_num_set(1,10) for j in range(9) ]
        self.cell = [ construct_num_set(1,10) for j in range(9) ]

        self.board = [ [ -1  for j in range(9) ] for k in range(9) ]

        col,row = 0,0

        for char in board_string:
            if char.isdigit():
                self.update_board(row, col, int(char))

            col = (col + 1) % 9

            if col == 0:
                row = (row + 1) % 9

    def update_board(self, row, col, value):
        num = set([value])
        self.row[row] -= num
        self.col[col] -= num

        self.cell[self.calc_cell(row, col)] -= num

        self.board[row][col] = value

    def calc_cell(self, row, col):
        return ((row // 3) * 3 + (col // 3))

    def calc_cell_corner(self, cell):
        return ((cell // 3) * 3, (cell % 3) * 3)

    def get_inverse_possibilities(self, row, col):
        return set(range(1,10)) - self.get_possibilities(row, col)

    def get_row(self, row):
        return [ self.get_possibilities(row, col) for col in range(9) ]

    def get_col(self, col):
        return [ self.get_possibilities(row, col) for row in range(9) ]

    def get_cell(self, cell):
        row,col = self.calc_cell_corner(cell)

        return [ self.get_possibilities(row + i, col + j) for i in range(3) for j in range(3)]

    def print_board(self):
        board_str = ""

        for row in self.board:
            for col in row:
                if col == -1:
                    board_str += "_"

                else:
                    board_str += repr(col)

            board_str += "\n"

        print board_str

    def get_possibilities(self, row, col):
        if self.board[row][col] == -1:
            return self.row[row] & self.col[col] & self.cell[self.calc_cell(row,col)]

        else:
            return set([])

    def find_single(self):
        for row in range(9):
            for col in range(9):
                pos = self.get_possibilities(row, col)

                if len(pos) == 1:
                   self.update_board(row, col, pos.pop())
                   return True

        return False

    def poss_histogram(self, list):
        num = dict([ (i, 0) for i in range(1,10) ])

        for pos in list:
            for i in pos:
                num[i] += 1

        return num

    def find_hidden_single(self):
        rows =  [ self.poss_histogram(self.get_row(i)) for i in range(9) ]
        cols =  [ self.poss_histogram(self.get_col(i)) for i in range(9) ]
        cells = [ self.poss_histogram(self.get_cell(i)) for i in range(9) ]

        for row in range(9):
            for col in range(9):
                pos = self.get_possibilities(row, col)


                for digit in pos:
                    if (rows[row][digit] == 1 or
                        cols[col][digit] == 1 or
                        cells[self.calc_cell(row,col)][digit] ==1):

                        self.update_board(row, col, digit)
                        return True

        return False


board_string =  "...7..8......4..3......9..16..5......1..3..4...5..1..75..2..6...3..8..9...7.....2"
board_string_scan_only = ".91745.6.4....1.7.573....4.....37.5...92.46...3.65.....1....536.4.9....8.2.57649."

board = sudoku_board(board_string)

print board.print_board()

while True:
    if board.find_single():
        print board.print_board()
        continue

    if board.find_hidden_single():
        print board.print_board()
        continue

    break
