import pandas as pd
import os
import sys
import glob
import numpy as np
import plotly.offline as py
import plotly.graph_objs as go


def setPath(path):
    os.chdir(path)


def getCSVNameList():
    extension = "csv"
    result = [i for i in glob.glob("*.{}".format(extension))]
    return result


def getAllSimilaritiesCSVs(csvNameList=[]):
    csvList=[]
    for csvName in csvNameList:
        csvList.append(pd.read_csv(csvName))
    return csvList


def getModels(csv):
    models=list(csv.columns.values)
    return models[4:]


def correction(value):
    if value > 1.0:
        return 1.0
    else:
        return value

def getYArraySimilarities(model, csvSimilarityList=[]):
    allSimilarities=[]
    for csvSimilarity in csvSimilarityList:
        similarities=csvSimilarity[model]
        for similarity in similarities:
            allSimilarities.append(correction(similarity))
    return allSimilarities


path=sys.argv[1]
setPath(path)

projectName=sys.argv[2]

csvNameList=getCSVNameList()
csvSimilarityList=getAllSimilaritiesCSVs(csvNameList)

models=getModels(csvSimilarityList[0])

data=[]

for model in models:
    data.append(
        go.Box(
            x=model,
            y=getYArraySimilarities(model, csvSimilarityList),
            name=model
        )
    )

layout=go.Layout(
    title="Project: " + projectName,
    xaxis=dict(
        title="Model",
        titlefont=dict(
            family='Courier New, monospace',
            size=18,
            color='#7f7f7f'
        )
    ),
    yaxis=dict(
        title="Similarity",
        titlefont=dict(
            family='Courier New, monospace',
            size=18,
            color='#7f7f7f'
        )
    )
)

fig=go.Figure(data=data, layout=layout)
plot_url=py.plot(fig, filename=projectName)