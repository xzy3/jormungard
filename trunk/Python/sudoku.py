import os

class sudoku_board:
    SINGLE_CANIDATE = "single canidate"
    SINGLE_POSITION = "single position"
    CANIDATE_LINE   = "canidate line"
    NAKED_PAIR      = "naked pair"
    HIDDEN_PAIR     = "hidden pair"
    X_WING          = "X-wing"

    def __init__(self, board_string, empty_cell_char, print_updates=True):
        self.print_updates = print_updates

        symbols = [ ord(i) for i in filter(lambda x: x != empty_cell_char, board_string) ]
        self.SYMBOL_SET = set(symbols)
        self.empty_cell_flag = ord(empty_cell_char)

        self.rows     = [ set(symbols) for j in range(9) ]
        self.columns  = [ set(symbols) for j in range(9) ]
        self.groups   = [ set(symbols) for j in range(9) ]

        self.cell = { }

        self.board = [ [ self.empty_cell_flag for j in range(9) ] for k in range(9) ]

        col,row = 0,0

        for char in board_string:
            if char != empty_cell_char:
                self.update_board(ord(char), row, col)

            col = (col + 1) % 9

            if col == 0:
                row = (row + 1) % 9

    def __repr__(self):
        board_repr = 'sudoku_board("'

        for row in self.board:
            for col in row:
                if col == self.empty_cell_flag:
                    board_repr += "."

                else:
                    board_repr += chr(col)

        board_repr += '",".")'
        return board_repr

    def __str__(self):
        board_str = ""

        for row in self.board:
            for col in row:
                if col == self.empty_cell_flag:
                    board_str += "_"

                else:
                    board_str += chr(col)

            board_str += os.linesep

        return board_str

    def __conditional_print(self, value, tup=()):
        if self.print_updates:
            print value % tup

    def is_complete(self):
        """Returns true when all the positions of a board have a definate answer"""
        for i in range(9):
            if len(self.rows[i]) != 0 or len(self.columns[i]) != 0 or len(self.groups[i]) != 0:
                return False

        for row in self.board:
            for col in row:
                if col == self.empty_cell_flag:
                    return False

        return True

    def update_board(self, value, row=-1, col=-1, cell=-1):
        """Places the symbol value in the board at the given position.
           The cell can eiter be given as update_board(value, (row, col) )
           or update_board(value, row, col)"""

        if row != -1 and col != -1 and cell == -1:
            _row,_col = row,col

        elif row == -1 and col == -1 and type(cell) == tuple:
            _row,_col = cell

        else:
            raise Exception("you must provide either row and column or a cell tuple")

        group = self.calc_group(_row, _col)

        self.rows[_row].discard(value)
        self.columns[_col].discard(value)
        self.groups[group].discard(value)

        self.board[_row][_col] = value

    def eliminate_canidates(self, canidates, positions, exceptions=set([])):
        """Removes value from the list of canidates for a row,
           column, position or cell unless the position is
           given in the exception list."""

        def normalize_input(input, except_string):
            if type(input) == set:
                return input

            elif type(input) == list:
                return set(input)

            elif type(input) == dict:
                return set(input.keys())

            elif type(input) == tuple or type(input) == int:
                return set([input])

            else:
                raise Exception(except_string)

        #normalize the canidates input
        canidate_set = normalize_input(canidates, "canidates must be iterable or an individual symbol")

        #normalize position input
        position_set = normalize_input(positions, "positions must be a iterable or an individual position tuple")

        #normalize the exceptions input
        exception_set = normalize_input(exceptions, "exceptions must be a iterable or an individual position tuple")

        Progress = False
        position_set -= exception_set
        for position in position_set:
            position_canidates = self.get_canidates(cell=position)
            if len(position_canidates & canidate_set) != 0:
                Progress = True

                if position in self.cell:
                    self.cell[position] |= canidate_set
                else:
                    self.cell[position] = canidate_set.copy()


        return Progress

    def calc_group(self, row, col):
        """returns the group number of a position given its row and col"""
        return ((row // 3) * 3 + (col // 3))

    def calc_group_corner(self, cell):
        """Returns the position in the upper left corner of a group"""
        return ((cell // 3) * 3, (cell % 3) * 3)

    def get_canidates(self, row=-1, col=-1, cell=-1):
        """Returns the set of canidate values for a position or the nul set
           if the position has a defined value"""

        if row != -1 and col != -1 and cell == -1:
            _row,_col = row,col
            _cell = (_row,_col)

        elif row == -1 and col == -1 and type(cell) == tuple:
            _row,_col = cell
            _cell = cell

        else:
            raise Exception("you must provide row and col or a cell tuple")

        group = self.calc_group(_row, _col)
        if self.board[_row][_col] == self.empty_cell_flag :
            ret = self.rows[_row] & self.columns[_col] & self.groups[group]

            if _cell in self.cell:
                ret -= self.cell[_cell]

            return ret

        else:
            return set([])

    def get_inverse_possibilities(self, row, col):
        return self.SYMBOL_SET - self.get_possibilities(row, col)

    def get_position_tuples(self, row=-1, col=-1, group=-1):

        if row > 8 or col > 8 or cell > 8:
            raise Exception("the row, col or group number you provided is too big")

        if row > -1 and col < -1 and group < -1:
            return [ (row, i) for i in range(9) ]

        elif row < -1 and col > -1 and group < -1:
            return [ (i,col) for i in range(9) ]

        elif row < -1 and col < -1 and group > -1:
            begin_i,begin_j = self.calc_cell_corner(group)
            return [ (i,j) for i in range(begin_i, begin_i + 3) for j in range(begin_j, begin_j + 3) ]

        else:
            raise Exception("you must provide either a row column or a group number")

    def get_canidate_list(self, *name, **params):
        """Returns a dictonary of all canidate sets for a given part of the board.
           It is of the format { (row,col) : get_canidates(position) }

           - get_canidate_list()
               returns the entire board worth of canidates
           - get_canidate_list(row=i)
               returns the row i
           - get_canidate_list(col=i)
               returns the col i
           - get_canidate_list(cell=i)
               returns the cell i"""

        if "row" in params:
            row = params["row"]
            positions = [ (row, col) for col in range(9) ]

        elif "col" in params:
            col = params["col"]
            positions = [ (row, col) for row in range(9) ]

        elif "cell" in params:
            row,col = self.calc_group_corner(params["cell"])
            positions = [ (i, j) for i in range(row, row + 3) for j in range(col, col + 3) ]

        else:
            positions = [ (row, col) for row in range(9) for col in range(9) ]

        return dict([ (position, self.get_canidates(cell=position)) for position in positions  ])

    def get_canidate_histogram(self, *names, **params):
        """Returns a histogram of how many times and where a value is a canidate in a
           given part of the board. Has the format
                { value : [ (row_0,col_0), (row_1,col_1), ..., (row_n, col_n) ] }

           takes the same paramiters as get_canidate_list()"""
        num = dict([ (i, []) for i in self.SYMBOL_SET ])

        for position,col in self.get_canidate_list(names, **params).iteritems():
            for digit in col:
                num[digit].append(position)

        return num

    def scan_single_canidate(self):

        # get all the canidate sets for the board and filter out those with more then one item
        canidate_list = filter( lambda x: len(x[1]) == 1, self.get_canidate_list().items())

        #grab the first item if any and update the board
        if len(canidate_list) > 0:
            position,symbol = canidate_list[0]
            symbol = symbol.pop()

            self.update_board(symbol, cell=position)
            self.__conditional_print("single canidate at %s for digit %s", (position, chr(symbol)))
            return True

        return False


    def scan_single_position(self):

        #filter_function takes a histogram and retuns true if a digit only appears once
        filter_function = lambda x: len(x[1]) == 1
        found_one = False

        for i in range(9):

            #get the histogram for row[i] and filter out anything that appears > 1 time
            canidate_list = filter( filter_function, self.get_canidate_histogram(row=i).items())

            #if anything is left break the loop
            if len(canidate_list) > 0:
                found_one = True
                break

            #identical to the above except for col
            canidate_list = filter(filter_function, self.get_canidate_histogram(col=i).items())

            if len(canidate_list) > 0:
                found_one = True
                break

            #same as above but for cells
            canidate_list = filter(filter_function, self.get_canidate_histogram(cell=i).items())
            if len(canidate_list) > 0:
                found_one = True
                break

        #update the board something was found
        if found_one:
            digit,position = canidate_list[0]
            position = position[0]

            self.__conditional_print("single position at %s for digit %s", (position,chr(digit)))
            self.update_board(digit, cell=position)

        return found_one

    def scan_canidate_line(self):

        progress = False

        for i in range(9):
            canidate_list = filter(lambda x: len(x[1]) == 2, self.get_canidate_histogram(cell=i).items())

            if len(canidate_list) > 0:
                for canidate in canidate_list:
                    digit, positions = canidate
                    position1 = positions[0]
                    position2 = positions[1]

                    if position1[0] == position2[0]:
                        row = self.get_canidate_list(row=position1[0])
                        progress = self.eliminate_canidates(digit, row, exceptions=positions)

                    elif position1[1] == position2[1]:
                        col = self.get_canidate_list(col=position1[1])
                        progress = self.eliminate_canidates(digit, col, exceptions=positions)

                    if progress:
                        self.__conditional_print("canidate line defined by cells %s and %s for digit %s", (position1, position2, chr(digit)))
                        return True

        return False

    def scan_naked_pair(self):

        def workhorse(list):
            new_list = filter(lambda x: len(x[1]) == 2, list.items())

            for i in range(len(new_list)):
                for j in range(i + 1, len(new_list)):

                    if new_list[i][1] == new_list[j][1]:

                        positions = [new_list[i][0], new_list[j][0]]
                        symbols = new_list[i][1]

                        if self.eliminate_canidates(symbols, list, exceptions=positions):
                            self.__conditional_print("naked pair at positions %s for values %s", (positions,[ chr(i) for i in symbols]))
                            return True

            return False

        for i in range(9):
            if workhorse(self.get_canidate_list(row=i)):
                return True

            if workhorse(self.get_canidate_list(col=i)):
                return True

            if workhorse(self.get_canidate_list(cell=i)):
                return True

        return False

    def scan_hidden_pair(self):

        def workhorse(list, histogram):
            new_list = filter(lambda x: len(x[1]) > 1, list.items())
            new_histogram = dict(filter(lambda x: len(x[1]) == 2, histogram.items()))

            for i in range(len(new_list)):
                for j in range(i + 1, len(new_list)):

                    new_set = new_list[i][1] & new_list[j][1]
                    if len(new_set) == 2 and all([ symbol in new_histogram for symbol in new_set ]):
                        symbols = new_set
                        positions = [new_list[i][0], new_list[j][0]]

                        progress = self.eliminate_canidates(symbols, list, exceptions=positions)
                        progress |= self.eliminate_canidates(self.SYMBOL_SET - symbols, positions)
                        if progress:
                            self.__conditional_print("hidden pair at positions %s for values %s", (positions,[ chr(i) for i in symbols]))
                            return True

            return False

        for i in range(9):
            if (workhorse(self.get_canidate_list(row=i), self.get_canidate_histogram(row=i)) or
                workhorse(self.get_canidate_list(col=i), self.get_canidate_histogram(col=i)) or
                workhorse(self.get_canidate_list(cell=i), self.get_canidate_histogram(cell=i))):

                return True
        return False

    def scan_x_wing(self):
        filter_function = lambda x: len(x[1]) == 2
        map_function = lambda x: (x[0], set(x[1]))

        def workhorse(first_histogram, second_histogram):
            one = map(map_function, filter(filter_function, first_histogram.items()))
            two = dict(map(map_function, filter(filter_function, second_histogram.items())))

            Progress = False
            for item in one:
                symbol, set = item

                if symbol in two and two[symbol] == set:
                    Progress |= self.eliminate_canidates(symbol,


    def solve(self, print_updates = True):
        self.print_updates = print_updates

        techniques = [ (self.SINGLE_CANIDATE, self.scan_single_canidate) ,
                       (self.SINGLE_POSITION, self.scan_single_position) ,
                       (self.NAKED_PAIR, self.scan_naked_pair) ,
                       (self.CANIDATE_LINE, self.scan_canidate_line) ,
                       (self.HIDDEN_PAIR, self.scan_hidden_pair)  ]

        tech_count = dict([ (i[0], 0) for i in techniques ])

        def conditional_print():
            self.__conditional_print(str(self))

        print "inital board"
        conditional_print()
        while not self.is_complete():
            Progress = False

            for tech in techniques:
                name, func = tech

                Progress = func()

                if Progress:
                    tech_count[name] += 1
                    conditional_print()
                    break

            if Progress:
                continue

            break

        solved = self.is_complete()
        if print_updates:
            if solved:
                print "solved"
            else:
                print "give up"
                print repr(self)

            for item in tech_count.iteritems():
                print "used %s, %d times" % item

        return (solved, tech_count)

board_string    = "...7..8......4..3......9..16..5......1..3..4...5..1..75..2..6...3..8..9...7.....2"
scan_only       = "..6.3.7.8.3......12.....6..1..35...6.79.4.15.5...17..4..2.....76......8.4.7.6.2.."
single_canidate = ".91745.6.4....1.7.573....4.....37.5...92.46...3.65.....1....536.4.9....8.2.57649."
canidate_line   = "..1957.63...8.6.7.76913.8.5..726135.312495786.56378...1.86.95.7.9.71.6.8674583..."
naked_pair      = "4..27.6..798156234.2.84...7237468951849531726561792843.82.15479.7..243....4.87..2"
hidden_pair     = "8.1..6.943....9.8.97..8.5..547.62.3.632....5.198375246.8362.915.65198...2195....8"

if __name__ == "__main__":
    board = sudoku_board(scan_only, '.')
    board.solve()
