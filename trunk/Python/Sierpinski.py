import turtle

def draw_triangle(point1, point2, point3):
    turtle.up()
    turtle.goto(point1)
    turtle.down()
    turtle.goto(point2)
    turtle.goto(point3)
    turtle.goto(point1)

if __name__ == '__main__':

    padding = 30
    level_count = 6

    triangle_list = []

    turtle.setup()
    side_length = min(turtle.window_height(), turtle.window_width()) - padding

    point2 = (side_length / 2, -(turtle.window_height() / 2 - padding))
    point3 = (-side_length / 2, -(turtle.window_height() / 2 - padding))

    x2,y2 = point2
    y1 = (side_length ** 2 - (side_length / 2) ** 2) ** 0.5 + y2

    point1 = (0, y1)

    draw_triangle(point1, point2, point3)

    triangle_list.append((point1, point2, point3))

    for i in range(level_count):

        new_triangle_list = []
        for triangle in triangle_list:
            point1, point2, point3 = triangle

            x1,y1 = point1
            x2,y2 = point2
            x3,y3 = point3

            new_point1 = ((x1 + x2) / 2, (y1 + y2) / 2)
            new_point2 = ((x2 + x3) / 2, (y2 + y3) / 2)
            new_point3 = ((x3 + x1) / 2, (y3 + y1) / 2)

            new_triangle_list.append((point1, new_point1, new_point3))
            new_triangle_list.append((new_point1, point2, new_point2))
            new_triangle_list.append((new_point3, new_point2, point3))

            draw_triangle(new_point1, new_point2, new_point3)

        triangle_list = new_triangle_list

    turtle.Tkinter.mainloop()
