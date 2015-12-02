# wx.ActiveXWindow #

Its amazing how hard it is to find any information on using com objects. wxPython is no exception.

package: `wx.activex`

constructor: `wx.activex.ActiveXWindow(parent, clsId, id=wx.ID_ANY, title="")`

The constructor requires at least a parent frame and the class id of the ActiveX control. If you use the string form of the class id you should pass it through the `wx.activex.CLSID()` macro.