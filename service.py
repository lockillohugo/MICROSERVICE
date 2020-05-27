import json 
import requests
import datetime
from dateutil.relativedelta import relativedelta
from wsgiref.simple_server import make_server

def myconverter(o):
    if isinstance(o, datetime.datetime):
        return o.__str__()

def obtenerFechasFaltantes(environ, start_response):
    #Consumir api 
    response = requests.get('http://127.0.0.1:8080/periodos/api', headers={'Accept': 'application/json'})

    #obtener json del api
    jsonPeriodos = response.json()
    
    #guardo fechas para evaluar
    fecha_ini = datetime.date.fromisoformat(jsonPeriodos["fechaCreacion"])
    fecha_fin = datetime.date.fromisoformat(jsonPeriodos["fechaFin"])
    newFecha = fecha_ini

    fechasFaltantes = []

    while (newFecha <= fecha_fin):
        encontroFecha = False

        for fecha in jsonPeriodos["fechas"]:
            if fecha == newFecha:
                encontroFecha = True
                break
        
        if (not encontroFecha):
            fechasFaltantes.append(newFecha.strftime("%Y-%m-%d"))
      
        #agregar un mes a la fecha
        newFecha +=  relativedelta(months=+1)

    start_response('200 OK', [ ('Content-type', 'application/json') ])
    
    return [ 
        bytes(
            json.dumps(
                { 
                    'fechaInicio' : fecha_ini.strftime("%Y-%m-%d"), 
                    'fechaFin' : fecha_fin.strftime("%Y-%m-%d"), 
                    'fechasFaltantes' : fechasFaltantes, 
                    'fechas' : jsonPeriodos["fechas"] 
                }
            ), 'utf-8'
        ) 
    ]


servidor = make_server('localhost', 8000, obtenerFechasFaltantes)
servidor.serve_forever()
