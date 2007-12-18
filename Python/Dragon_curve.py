import turtle
""" draws the fractal called the dragon curve."""


if __name__ == "__main__":

    fold_right = [True]
    step_count = 14
    origin=(-220,110)
    length = 2
    turtle.tracer(False)

    #build the list of folds for the dragon curve
    for i in range(step_count):
        #make a reversed copy of the previous iteration's list
        begin = fold_right[ len(fold_right) :: -1]
        fold_right.append(True)

        #append the list from the last iteration
        #in reverse order with the directions flipped
        for dir in begin:
            fold_right.append(not dir)

    turtle.up()
    turtle.setheading(270)
    turtle.goto(origin)
    turtle.down()
    turtle.forward(length)

    for dir in fold_right:
        if dir:
            turtle.right(90)
            turtle.forward(length)

        else:
            turtle.left(90)
            turtle.forward(length)

    turtle.Tkinter.mainloop()
