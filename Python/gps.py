import thread
import math

MAJOR_AXIS = 6378137.0 #earth semimajor axis
RECIPROCAL_FLATTENING = 298.257223563
ECCENTRICITY_SQUARED = 6.69437999014E-3

def llh2ecef(tup):
    """Converts from radian latitude, longitude and altitude in meters
    to WGS-84 ECEF coordinates. Returns a tuple (X,Y,Z).
    """

    lat,lon,h = tup

    chi = math.sqrt(1 - ECCENTRICITY_SQUARED * math.sin(lat) **2)
    t = MAJOR_AXIS / chi + h
    X = t * math.cos(lat) * math.cos(lon)
    Y = t * math.cos(lat) * math.sin(lon)
    Z = (MAJOR_AXIS * (1 - ECCENTRICITY_SQUARED) / chi + h) * math.sin(lat)
    return (X, Y, Z)

def ecef2enu(ref, pos):
    """Converts ECEF coordinate pos into local-tangent-plane ENU
    coordinates relative to another ECEF coordinate ref. Returns a tuple
    (East, North, Up).
    """
    xr,yr,zr = ref
    x,y,z = pos

    phiP = math.atan2(zr, math.sqrt(xr ** 2 + yr ** 2))
    lambd = math.atan2(yr,xr)

    xmxr = x - xr
    ymyr = y - yr
    zmzr = z - zr

    cosLambd = math.cos(lambd)
    sinLambd = math.sin(lambd)

    cosPhiP = math.cos(phiP)
    sinPhiP = math.sin(phiP)

    e = -sinLambd * xmxr + cosLambd * ymyr
    n = -sinPhiP * cosLambd * xmxr - sinPhiP * sinLambd * ymyr + cosPhiP * zmzr
    u = cosPhiP * cosLambd * xmxr + cosPhiP * sinLambd * ymyr + sinPhiP * zmzr
    return (e, n, u)

class Model:
    """Class that reads and parses NEMA sentences from any object implementing
    readline. You can also update the data manually by passing a valid sentence
    to update. The model will keep track of the most recent data it has read.
    """
    def __init__(self):
        self.continue_reading = False

        self.__parsers = ({ '$GPGLL' : self.__parse_gpgll,
                            '$GPRMC' : self.__parse_gprmc,
                            '$GPGGA' : self.__parse_gpgga })

        self.__update_callbacks = set()

    def start(self, connection):
        """Spawn a thread that will continually call readline on the given
        object passing each sentence in turn to update.
        """
        self.continue_reading = True

        thread.start_new_thread(self.__reader_thread, (connection,))

    def stop(self):
        """Stops any previously spawned reader thread"""
        self.continue_reading = False

    def getLatitude(self):
        """Return the most recent Latitude reading as a list.
        [degrees, decimal minutes, direction (n/s)]
        """
        return self.__parse_coord(self.latitude, self.ns)

    def getLongitude(self):
        """Return the most recent Longitude reading as a list.
        [degrees, decimal minutes, direction (e/w)]
        """
        return self.__parse_coord(self.longitude, self.ew)

    def getAltitude(self):
        """Returns the most recent altitude reading as a float."""
        return float(self.altitude)

    def getGeoidHeight(self):
        """Returns the most recent geoidHeight above the ellipsoid."""
        return float(self.geoidHeight)

    def __parse_coord(self, c, d):
        i = c.rindex('.') - 2
        l = len(c) - 1

        deg = float(c[:i])
        min = float(c[i:l])

        return [deg, min, d.lower()]

    def update(self, sentence):
        """Update this model's data with the given NEMA sentence."""
        s = sentence.rpartition('*')[0]
        s = s.split(',')

        if s[0] in self.__parsers:
            self.__parsers[s[0]](s)

            for callback in self.__update_callbacks:
                callback(s[0])

    def addCallback(self, call):
        """Regester a callback function. The function should take one parameter
        that will be the NEMA sentence type specifier. The function will be
        called every time a NEMA sentence is successfully parsed.
        """
        self.__update_callbacks.add(call)

    def removeCallback(self, call):
        """Remove a callback regestered with addCallback."""
        self.__update_callbacks.remove(call)

    def __reader_thread(self, connection):
        while self.continue_reading:
            sentence = connection.readline()

            self.update(sentence)

    def __parse_gpgll(self, sentence):
        self.latitude = sentence[1]
        self.ns = sentence[2]
        self.longitude = sentence[3]
        self.ew = sentence[4]

        if len(data) > 5:
            self.lastFixTime = sentence[5]

    def __parse_gprmc(self, sentence):
        self.lastFixTime = sentence[1]
        self.receiverWarning = sentence[2]
        self.latitude = sentence[3]
        self.ns = sentence[4]
        self.longitude = sentence[5]
        self.ew = sentence[6]
        self.speed = sentence[7]
        self.trueCourse = sentence[8]
        self.lastFixDate = sentence[9]
        self.magneticVariation = sentence[10]
        self.magneticDirection = sentence[11]

    def __parse_gpgga(self, sentence):
        self.lastFixTime = sentence[1]
        self.latitude = sentence[2]
        self.ns = sentence[3]
        self.longitude = sentence[4]
        self.ew = sentence[5]
        self.fixQuality = sentence[6]
        self.satellitesTracked = sentence[7]
        self.hdop = sentence[8]
        self.altitude = sentence[9]
        self.geoidHeight = sentence[11]

class View:
    def __init__(self, model):
        self.model = model

class RadianView(View):
    def __init__(self, model):
        View.__init__(self, DecimalDegreeView(model))

    def getPosition(self):
        lat,lon,h = self.model.getPosition()
        return (math.radians(lat), math.radians(lon), h)

class DecimalDegreeView(View):
    def __init__(self, model):
        View.__init__(self, model)

    def getPosition(self):
        deg,min,dir = self.model.getLatitude()

        lat = deg + min / 60.0

        if dir == 's':
            lat = -lat

        deg,min,dir = self.model.getLongitude()

        lon = deg + min / 60.0

        if dir == 'w':
            lon = -lon

        return (lat, lon, model.getAltitude())

class ECEFView(View):
    def __init__(self, model):
        View.__init__(self, RadianView(model))

    def getPosition(self):
        lat,lon,h = self.model.getPosition()
        return llh2ecef(lat, lon, h)

class ENUView(View):
    def __init__(self, model, ref):
        View.__init__(self, ECEFView(model))
        self.ref = ref

    def getPosition(self):
        return ecef2enu(self.ref, model.getPosition)
