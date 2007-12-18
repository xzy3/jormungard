import turtle

def fibonacci():
    """Generator returning the standard fibonacci series starting 1,1"""
    a = 1
    b = 1
    while True:
        yield a
        c = a
        a = b
        b += c

def spiral_points(count,origin=(0,0)):
    """Generator that returns tuples containing points (x,y)
       on the natilus spiral"""
    gen = fibonacci()
    x,y = origin
    for i in range(count):
        yield (x,y)

        step = gen.next()
        dir = i % 4
        if dir == 0:
            x += step
            y += step
        elif dir == 1:
            x += step
            y -= step
        elif dir == 2:
            x -= step
            y -= step
        else:
            x -= step
            y += step


if __name__ == '__main__':
    count = 15
    padding = 30

    point_list = [ i for i in spiral_points(count) ]
    min_x,min_y = point_list[1]
    max_x,max_y = point_list[1]

    for x,y in point_list:
        if x < min_x:
            min_x = x
        if y < min_y:
            min_y = y
        if x > max_x:
            max_x = x
        if y > max_y:
            max_y = y

    width = abs(min_x) + abs(max_x) + padding
    height = abs(min_y) + abs(max_y) + padding

    origin = (-(width / 2 + min_x - padding / 2),
              -(height / 2 + min_y - padding / 2))

    turtle.setup(width=width, height=height,x=0,y=60)
    turtle.up()

    points = spiral_points(count, origin)
    turtle.goto(points.next())
    for x,y in points:
        curr_x,curr_y = turtle.position()

        turtle.down()
        turtle.goto(curr_x, y)
        turtle.goto(x, y)
        turtle.goto(x, curr_y)
        turtle.goto(curr_x, curr_y)

        turtle.up()
        turtle.goto(x, y)

    fib = fibonacci()
    points = spiral_points(count, origin)

    direction = [270, 180, 90, 0]

    turtle.color('red')
    for i in range(count - 1):
        radius = fib.next()
        turtle.up()
        turtle.goto(points.next())

        turtle.setheading(direction[i % 4])

        turtle.down()
        turtle.circle(radius, -90)

    turtle.Tkinter.mainloop()
