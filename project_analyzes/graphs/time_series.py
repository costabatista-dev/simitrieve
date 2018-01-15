import pandas as pd
import os
import sys
import plotly.offline as py
import plotly.graph_objs as go


def setPath(path):
    os.chdir(path)

def getMetricCSV(csvName):
    csv=pd.read_csv(csvName)
    return csv

def getModels(csv):
    models=csv["model"].unique()
    return models


path=sys.argv[1]
setPath(path)

csvName=sys.argv[2]
metricCSV=getMetricCSV(csvName)
models=getModels(metricCSV)
