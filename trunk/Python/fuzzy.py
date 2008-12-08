def posSlope(p1, p2):
    m = 1.0 / (p2 - p1)
    b = -m * p1
    return (m, b)

def negSlope(p1, p2):
    m = -1.0 / (p2 - p1)
    b = -m * p2
    return (m, b)

def lin(m, x, b):
    return m * x + b

class Is:
    def __init__(self, key, clause):
        self.key = key
        self.clause = clause

    def __call__(self, kwargs):
        return self.clause(kwargs[self.key])

class Very:
    def __init__(self, set):
        self.set = set

    def __call__(self, x):
        return self.set(x) ** 0.5

class Fairly:
    def __init__(self, set):
        self.set = set

    def __call__(self, x):
        return self.set(x) ** 2

class Or:
    def __init__(self, *args):
        self.sets = args

    def __call__(self, x):
        return max([f(x) for f in self.sets])

class And:
    def __init__(self, *args):
        self.sets = args

    def __call__(self, x):
        return min([f(x) for f in self.sets])

class Not:
    def __init__(self, set):
        self.set = set

    def __call__(self, x):
        return 1 - self.set(x)

class FuzzyManifold:
    def __init__(self, begin, end, *args):
        self.sets = args

        self.range = (begin, end)

        for s in self.sets:
            s.manifold = self

    def getRange():
        return self.range

    def getCentroid(self, point_count, kwargs):

        beg,end = self.range

        def frange(begin, end, count):
            s = (end - begin) / point_count
            t = begin

            while t < end:
                yield t
                t += s

            yield end

        points = [ x for x in frange(beg, end, point_count) ]

        doms = []
        for x in points:
            doms.append(max([ set(x, clip=kwargs.get(set, 0.0)) for set in self.sets ]))

        return sum(map(lambda a,b: a * b, points, doms)) / sum(doms)

class MembershipSet:
    def __init__(self):
        pass

class RightSet(MembershipSet):
    def __init__(self, p1, p2):
        MembershipSet.__init__(self)
        self.p1 = float(p1)
        self.p2 = float(p2)

        self.m,self.b = posSlope(p1, p2)

    def __call__(self, x, clip=1.0):
        if x >= self.p2:
            return clip

        if x <= self.p1:
            return 0

        return min((clip, lin(self.m, x, self.b)))

class LeftSet(MembershipSet):
    def __init__(self, p1, p2):
        MembershipSet.__init__(self)
        self.p1 = float(p1)
        self.p2 = float(p2)

        self.m,self.b = negSlope(p1, p2)

    def __call__(self, x, clip=1.0):
        if x <= self.p1:
            return clip

        if x >= self.p2:
            return 0

        return min((clip, lin(self.m, x, self.b)))

class TriangleSet(MembershipSet):
    def __init__(self, p1, p2, p3):
        MembershipSet.__init__(self)
        self.p1 = float(p1)
        self.p2 = float(p2)
        self.p3 = float(p3)

        self.m12, self.b12 = posSlope(p1, p2)

        self.m23, self.b23 = negSlope(p2, p3)

    def __call__(self, x, clip=1.0):
        if x <= self.p1 or x >= self.p3:
            return 0

        if x == self.p2:
            return clip

        return min((clip,
                    lin(self.m12, x, self.b12),
                    lin(self.m23, x, self.b23)))

class TrapazoidSet(MembershipSet):
    def __init__(self, p1, p2, p3, p4):
        MembershipSet.__init__(self)
        self.p1 = float(p1)
        self.p2 = float(p2)
        self.p3 = float(p3)
        self.p4 = float(p4)

        self.m12, self.b12 = posSlope(p1, p2)
        self.m34, self.b34 = negSlope(p3, p4)

    def __call__(self, x, clip=1.0):
        if x <= self.p1 or x >= self.p4:
            return 0

        if x == self.p2 or x == self.p3 or (x > self.p2 and x < self.p3):
            return clip

        return min((clip,
                    lin(self.m12, x, self.b12),
                    lin(self.m34, x, self.b34)))

class Rule:
    def __init__(self, antecedent, *args):
        self.antecedent = antecedent
        self.consequent = args

    def __call__(self, **kwargs):
        dom = self.antecedent(kwargs)

        return dict().fromkeys(self.consequent, dom)

class RuleSet:
    def __init__(self, *args):
        self.rules = args

    def __call__(self, **kwargs):
        print kwargs
        val = {}

        for rule in self.rules:
            for k,v in rule(**kwargs).iteritems():
                val[k] = max((val.get(k, 0.0), v))

        return val

def fuzzymain():
    target_close = Is('dist_to_target', LeftSet(25, 150))
    target_medium = Is('dist_to_target', TriangleSet(25, 50, 300))
    target_far = Is('dist_to_target', RightSet(150, 300))

    ammo_low = Is('ammo', LeftSet(0, 10))
    ammo_ok = Is('ammo', TriangleSet(0, 10, 30))
    ammo_loads = Is('ammo', RightSet(10, 30))

    undesirable = LeftSet(25, 50)
    desirable = TriangleSet(25, 50, 75)
    very_desirable = RightSet(50, 75)
    desirability = FuzzyManifold(0, 100, undesirable, desirable, very_desirable)

    r = RuleSet(Rule(And(target_far, ammo_low), desirable),
                Rule(And(target_far, ammo_ok), undesirable),
                Rule(And(target_far, ammo_low), undesirable),
                Rule(And(target_medium, ammo_loads), very_desirable),
                Rule(And(target_medium, ammo_ok), very_desirable),
                Rule(And(target_medium, ammo_low), desirable),
                Rule(And(target_close, ammo_loads), undesirable),
                Rule(And(target_close, ammo_ok), undesirable),
                Rule(And(target_close, ammo_low), undesirable))

    dis = r(dist_to_target=200, ammo=8)
    print dis

    print desirability.getCentroid(10, dis)

if __name__=='__main__':
    fuzzymain()
