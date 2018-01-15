import os
import sys
import glob
import re
import copy
import pandas as pd 
import numpy as np

def setPath(path):
    os.chdir(path)


def getCSVNameList():
    extension="csv"
    csvList=[i for i in glob.glob('*.{}'.format(extension))]
    return csvList


def getCSV(csvName):
    csv=pd.read_csv(csvName)
    return csv


def getAllCSVs(projectName, csvNameList=[]):
    csvList=[]
    for csvName in csvNameList:
        csv=getCSV(csvName)
        csv.version=getVersion(projectName, csvName)
        csvList.append(csv)
    return csvList


def getVersion(projectName, csv):
    version=copy.deepcopy(csv)
    version=re.sub("_similarities\.csv$","", version)
    version=re.sub(projectName + "_","", version)
    return version

def getModels(csv):
    models=list(csv.columns.values)
    return models[4:]


def getColumn(csv, model):
    column=csv[model]
    return column


def getMetricObject(csv, version, model):
    column=csv[model]
    metricObject=column
    metricObject.model=model
    metricObject.version=version
    metricObject.mean=np.mean(column)
    metricObject.median=np.median(column)
    metricObject.min=np.min(column)
    metricObject.max=np.max(np.round(np.max(column),1,out=None))
    if metricObject.max > 1:
        metricObject.max = 1.0

    return metricObject


def getAllMetricObjects(projectName, csvNameList=[]):
    csvList=getAllCSVs(projectName, csvNameList)
    metricObjects=[]
    models=getModels(csvList[0])
    for csv in csvList:
        for model in models:
            metricObject=getMetricObject(csv, csv.version, model)
            metricObjects.append(metricObject)
    return metricObjects


def writeMetricsCSV(projectName, metricObjects=[]):
    columns=["version", "model", "mean","median", "max", "min"]
    rowNumber, columnNumber= len(metricObjects), len(columns)
    data=[[0 for x in range(columnNumber)] for y in range(rowNumber)]
    for row in range(rowNumber):
            data[row][0]=metricObjects[row].version
            data[row][1]=metricObjects[row].model
            data[row][2]=metricObjects[row].mean
            data[row][3]=metricObjects[row].median
            data[row][4]=metricObjects[row].max
            data[row][5]=metricObjects[row].min
    dataframe=pd.DataFrame(data, columns=columns)
    dataframe.to_csv(projectName + "_" + "metrics.csv", index=False)


path=sys.argv[1]
setPath(path)
projectName=sys.argv[2]
csvNameList=getCSVNameList()
metricList=getAllMetricObjects(projectName, csvNameList)

writeMetricsCSV(projectName, metricList)