import os 
import sys
import glob
import re
import copy
import pandas as pd


def setPath(path):
    os.chdir(path)


def getCSVList():
    extension="csv"
    csvList=[i for i in glob.glob('*.{}'.format(extension))]
    return csvList


def getAllSimilarityCSVS(csvNameList, projectName, projectVersion):
    csvList=[]
    for csvName in csvNameList:
        csvList.append(readCSVFile(csvName, projectName, projectVersion))
    return csvList


def readCSVFile(csvName, projectName, version):
    csv=pd.read_csv(csvName)
    similarityProcessName=getCSVSimilarityProcessName(csvName, projectName, version)
    csv.similarityProcess=similarityProcessName
    
    return csv

def getCSVSimilarityProcessName(csvName, projectName, version):
    finalName=re.sub("\.csv$", "", csvName)
    finalName=re.sub(projectName + "_" + version + "_", "", finalName)
    return finalName


def mergeCSVSimilarities(projectName, version, csvsSimilarities=[]):
    headers=["First Package", "Second Package", "First Class", "Second Class"]
    dataframe=pd.DataFrame(csvsSimilarities[0][headers])
    for csv in csvsSimilarities:
       headers.append(csv.similarityProcess)
       currentFrame=pd.DataFrame(csv["Similarity"])
       dataframe=pd.concat([dataframe, currentFrame], axis=1)

    dataframe.columns=headers  
    dataframe.to_csv(projectName + "_"+ version + "_" + "similarities.csv", index=False)


'''Bellow: python script summarize execution'''

setPath(sys.argv[1])
csvList=getCSVList()
projectName=sys.argv[2]
projectVersion=sys.argv[3]

csvsSimilarities=getAllSimilarityCSVS(csvList, projectName, projectVersion)

headers=["First Package", "Second Package", "First Class", "Second Class"]
mergeCSVSimilarities(projectName, projectVersion, csvsSimilarities)