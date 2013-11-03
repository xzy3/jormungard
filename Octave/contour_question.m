%Seth Sims November 2, 2013
%Made to answer a question asking for code that would find peaks and troughs
%in a landscape loaded from a file as a matrix of terrain heights.
%
%generate a list of numbers evenly spaced from [-30, 30] with 49 divisions
tx = ty = linspace(-30, 30, 49);

%turn that into two matrices giving the x, and y for every spot
[xx, yy] = meshgrid(tx, ty);

%This is just a "magic" function made up to look like a reasonable
%terrain.
%Some related functions used when verifying the stable point finding algorithm
%below:
% dz/dx = 2/5 * cos(x / 5) + 60/x;
% dz/dy = -sin(y / 3) - y/60;
% d^2z/dx^2 = -2/25 * sin(x / 5) + 1/60;
% d^2z/dy^2 = -1/3 * cos(y / 3) - 1/60;
% d^2z/dxy = d^2z/dyx = 0;
tz = 2 * sin(xx / 5) + 3 * cos(yy / 3) + (xx .^ 2 - yy .^ 2) / 120;

%display a 3d mesh plot of the terrain function
figure(1);
mesh(tx, ty, tz);

%display a contour plot of the terrain function
figure(2);
contour(xx, yy, tz);

%search for points in the interior of the grid that have the central point
%be higher or lower then all eight of its neighbors.
for row = 2:rows(tz)-1
    for column = 2:columns(tz)-1
	peak_counter = 0;
	trough_counter = 0;

        for i = row - 1:row + 1
	    for j = column - 1:column + 1
	       if(tz(row, column) > tz(i, j))
	           peak_counter += 1;

	       elseif(tz(row, column) < tz(i, j))
	           trough_counter += 1;
	       endif
	    end
	end

	if(peak_counter > 7)
	    printf("Peak found at %d, %d\n", xx(row, column), yy(row, column));
	elseif(trough_counter > 7)
	    printf("Trough found at %d, %d\n", xx(row, column), yy(row, column));
	endif
    end
end
