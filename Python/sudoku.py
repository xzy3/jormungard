def construct_num_set(begin,end):
    return set(range(begin,end))

class sudoku_board:
    def __init__(self, board_string):
        self.row  = [ construct_num_set(1,10) for j in range(9) ]
        self.col  = [ construct_num_set(1,10) for j in range(9) ]
        self.cell = [ construct_num_set(1,10) for j in range(9) ]

        self.board = [ [ set([])  for j in range(9) ] for k in range(9) ]

        col,row = 0,0

        for char in board_string:
            if char.isdigit():
                self.update_board(row, col, int(char))

            col = (col + 1) % 9

            if col == 0:
                row = (row + 1) % 9

    def __repr__(self):
        board_repr = 'sudoku_board("'

        for row in self.board:
            for col in row:
                if type(col) == set:
                    board_repr += "."

                else:
                    board_repr += str(col)

        board_repr += '")'
        return board_repr

    def __str__(self):
        board_str = ""

        for row in self.board:
            for col in row:
                if type(col) == set:
                    board_str += "_"

                else:
                    board_str += str(col)

            board_str += "\n"

        return board_str

    def update_board(self, row, col, value):
        print "update board", row, col, value
        num = set([value])
        self.row[row] -= num
        self.col[col] -= num

        self.cell[self.calc_cell(row, col)] -= num

        self.board[row][col] = value

    def calc_cell(self, row, col):
        return ((row // 3) * 3 + (col // 3))

    def calc_cell_corner(self, cell):
        return ((cell // 3) * 3, (cell % 3) * 3)

    def get_possibilities(self, row, col):
        if type(self.board[row][col]) == set :
            return (self.row[row] & self.col[col] & self.cell[self.calc_cell(row,col)]) - self.board[row][col]

        else:
            return set([])

    def get_inverse_possibilities(self, row, col):
        return set(range(1,10)) - self.get_possibilities(row, col)

    def get_poss_list(self, *name, **params):

        if "row" in params:
            row = params["row"]
            out = [ (row, col, self.get_possibilities(row, col)) for col in range(9) ]

        elif "col" in params:
            col = params["col"]
            return [ (row, col, self.get_possibilities(row, col)) for row in range(9) ]

        elif "cell" in params:
            row,col = self.calc_cell_corner(param["cell"])
            return [ (i, j, self.get_possibilities(i, j)) for i in range(row, row + 3) for j in range(col, col + 3)]

    def poss_histogram(self, *names, **params):
        num = dict([ (i, []) for i in range(1,10) ])

        if "row" in params:
            row = params["row"]
            for col in range(9):
                for poss in self.get_possibilities(row, col):
                    num[poss].append((row,col))

            return num

        if "col" in params:
            col = params["col"]
            for row in range(9):
                for poss in self.get_possibilities(row, col):
                    num[poss].append((row,col))

            return num

        if "cell" in params:
            row_begin,col_begin = self.calc_cell_corner(params["cell"])

            for row in range(row_begin, row_begin + 3):
                for col in range(col_begin, col_begin + 3):
                    for poss in self.get_possibilities(row, col):
                        num[poss].append((row,col))

            return num

    def scan_single_position(self):

        for i in range(9):
            for digit,row in self.poss_histogram(row=i).iteritems():
                if len(row) == 1:
                    i,j = row[0]

                    self.update_board( i, j, digit)

                    return True

            for digit,col in self.poss_histogram(col=i).iteritems():
                if len(col) == 1:
                    i,j = col[0]

                    self.update_board( i, j, digit)

                    return True

            for digit,cell in self.poss_histogram(cell=i).iteritems():
                if len(cell) == 1:
                    i,j = cell[0]

                    self.update_board( i, j, digit)

                    return True

        return False

    def scan_canidate_line(self):
        histogram_vector = [ self.poss_histogram(cell=i) for i in range(9) ]

        for histogram in histogram_vector:
            for digit, pos in histogram.iteritems():
                if len(pos) == 2:
                    row1,col1 = pos[0]
                    row2,col2 = pos[1]

                    print pos, digit
                    if row1 == row2:
                        for i in range(9):
                            if i != col1 and i != col2 and type(self.board[row1][i]) == set:
                                self.board[row1][i] |= set([digit])

                    if col1 == col2:
                        for i in range(9):
                            if i != row1 and i != row2 and type(self.board[i][col1]) == set:
                                self.board[i][col1] |= set([digit])


        return True

board_string =  "...7..8......4..3......9..16..5......1..3..4...5..1..75..2..6...3..8..9...7.....2"
board_string_single_canidate = ".91745.6.4....1.7.573....4.....37.5...92.46...3.65.....1....536.4.9....8.2.57649."
board_string_canidate_line = "..1957.63...8.6.7.76913.8.5..726135.312495786.56378...1.86.95.7.9.71.6.8674583..."

board = sudoku_board(board_string_canidate_line)

print board

while True:

   if board.scan_single_position():
       print "single position"
       print board
       continue

   if board.scan_canidate_line():
        print "canidate line"
        print board
        continue

   break

