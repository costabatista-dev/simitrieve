import os
import sys
import glob
import pandas as pd
import numpy as np


def setPath(path):
    os.chdir(path)


def getCSV(csvName):
    csv = pd.read_csv(csvName)
    return csv


def getMaxMeanTupleByVersion(csv, version):
    columnVersion = csv.loc[csv["version"] == version]
    maxMean = np.max(columnVersion["mean"])
    maxMeanTuple = columnVersion[columnVersion["mean"] == maxMean]
    
    return maxMeanTuple


def getMinMeanTupleByVersion(csv, version):
    columnVersion = csv.loc[csv["version"] == version]
    minMean = np.min(columnVersion["mean"])
    minMeanTuple = columnVersion[columnVersion["mean"] == minMean]

    return minMeanTuple


def getMaxMedianTupleByVersion(csv, version):
    columnVersion = csv.loc[csv["version"] == version]
    maxMedian = np.max(columnVersion["median"])
    maxMedianTuple = columnVersion[columnVersion["median"] == maxMedian]

    return maxMedianTuple


def getMinMedianTupleByVersion(csv, version):
    columnVersion = csv.loc[csv["version"] == version]
    minMedian = np.min(columnVersion["median"])
    minMedianTuple = columnVersion[columnVersion["median"] == minMedian]

    return minMedianTuple


def getVersions(csv):
    versions = list(csv["version"].unique())
    return versions


def getTuples(csv):
    versions = getVersions(csv)
    tuples=[]
   
    for version in versions:
        maxMeanTuple = getMaxMeanTupleByVersion(csv, version)
        maxMedianTuple = getMaxMedianTupleByVersion(csv, version)
        minMeanTuple = getMinMeanTupleByVersion(csv, version)
        minMedianTuple = getMinMedianTupleByVersion(csv, version)

        tuples.append([
            version, maxMeanTuple.iloc[0][1], maxMeanTuple.iloc[0][2],
            maxMedianTuple.iloc[0][1], maxMedianTuple.iloc[0][3],
            minMeanTuple.iloc[0][1], minMeanTuple.iloc[0][2],
            minMedianTuple.iloc[0][1], minMedianTuple.iloc[0][3]
        ])
    
    return tuples


def writeTuplesCSV(projectName, tuples=[]):
     headers=["version", "highest mean model", "highest mean value", "highest median model", "highest median value", 
     "lowest mean model", "lowest mean value", "lowest median model", "lowest median value"]
     dataframe = pd.DataFrame(tuples, columns=headers)
     dataframe.to_csv(projectName + "_obv.csv", index=False)


path = sys.argv[1]
setPath(path)

csvName = sys.argv[2]
csv = getCSV(csvName)

projectName = sys.argv[3]

#print(getMaxMeanTupleByVersion(csv, "V1.7.0"))
#print(getMinMeanTupleByVersion(csv, "V1.7.0"))
#print(getVersions(csv))
tuples = getTuples(csv)
writeTuplesCSV(projectName, tuples)