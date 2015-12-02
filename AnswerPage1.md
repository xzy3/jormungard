# exclusive-or #
The values are swapped.
#  #
```
x = 0xBEBEBEBE
y = 0xACACACAC
```

loose proof:

First you have to know a few of the algebraic properties of `^`.
#  #
> | `a ^ 0 = a` | exclusive-or identity |
|:------------|:----------------------|
> | `a ^ a = 0` | self inverse          |
> | `(a ^ b) ^ c = a ^ (b ^ c)` | associativity         |
> | `a ^ b = b ^ a` | communitivity         |
#  #
  1. now if you add variables to remove overwrites and solve these
    1. `x ^ y = a`
    1. `a ^ y = b`
    1. `a ^ b = c`
  1. substitute 1.1 into 1.2 to get an equation for b
    1. `(x ^ y) ^ y = b`
    1. `x ^ (y ^ y) = b`
    1. `x ^ 0 = b`
    1. `x = b`
  1. substitute 1.1 and 1.2 into 1.3 to get an equation for c
    1. `(x ^ y) ^ (a ^ y) = c`
    1. `(x ^ y) ^ ((x ^ y) ^ y) = c`
    1. `(x ^ y) ^ (x ^ (y ^ y)) = c`
    1. `(x ^ y) ^ (x ^ 0) = c`
    1. `(x ^ y) ^ x = c`
    1. `(x ^ x) ^ y = c`
    1. `0 ^ y = c`
    1. `y = c`

you see that the values of x and y are swapped. because in the code `x == c` and `y == b`.

# modulo power of 2 #
```
print y & (x - 1)
```
This works because x is a power of 2. However it only works with unsigned (or known positive) integral types.