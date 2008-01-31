import turtle

SEGMENT_LENGTH = 2
WINDOW_WIDTH = 772
WINDOW_HEIGHT = 501

if __name__ == "__main__":

    move_list = "F"

    for i in range(15):
        move_list = move_list.replace("F", "+F--F+")

    turtle.setup(width=WINDOW_WIDTH, height=WINDOW_HEIGHT)
    turtle.tracer(False)
    turtle.up()
    turtle.goto(-180, -135)

    turtle.down()

    max_x, max_y, min_x, min_y = 0, 0, 0, 0

    for i in move_list:
        if i == "F":
            turtle.forward(SEGMENT_LENGTH)

        elif i == "+":
            turtle.left(45)

        else:
            turtle.right(45)

    turtle.Tkinter.mainloop()
