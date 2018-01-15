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


def getVersions(csv):
    versions=csv["version"].unique()
    return list(versions)


def selectMeans(csv, model):
   return list(csv.loc[(csv["model"] == model)]["mean"])   


path=sys.argv[1]
setPath(path)
projectName=sys.argv[3]

csvName=sys.argv[2]
metricCSV=getMetricCSV(csvName)

if len(sys.argv) <= 4:
    x_array=getVersions(metricCSV)
else:
    x_array=[]
    for i in range(4, len(sys.argv)):
        x_array.append(sys.argv[i])

models=getModels(metricCSV)
data=[]

for model in models:
    data.append(
        go.Scatter(
            x=x_array,
            y=selectMeans(metricCSV, model),
            mode="lines+markers",
            name=model
        )
    )
layout = go.Layout(
    title="Project: " + projectName,
    xaxis=dict(
       title='Version',
       titlefont=dict(
           family='Courier New, monospace',
           size=18,
           color='#7f7f7f'
       )
    ),
    yaxis=dict(
       title='Similarity (cosine)',
       titlefont=dict(
           family='Courier New, monospace',
           size=18,
           color='#7f7f7f'
       )
    )
)

fig = go.Figure(data=data, layout=layout)
plot_url=py.plot(fig, filename=projectName)
