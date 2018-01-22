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


def getMaxMeanTupleByVersion(csv, version, normalization):
    columnVersion = csv.loc[csv["version"] == version]
    columnNormalization = columnVersion.loc[columnVersion["model"].str.contains(normalization)]
    maxMean = np.max(columnNormalization["mean"])
    maxMeanTuple = columnNormalization[columnNormalization["mean"] == maxMean]
    
    return maxMeanTuple


def getMinMeanTupleByVersion(csv, version, normalization):
    columnVersion = csv.loc[csv["version"] == version]
    columnNormalization = columnVersion.loc[columnVersion["model"].str.contains(normalization)]
    minMean = np.min(columnNormalization["mean"])
    minMeanTuple = columnNormalization[columnNormalization["mean"] == minMean]

    return minMeanTuple


def getMaxMedianTupleByVersion(csv, version, normalization):
    columnVersion = csv.loc[csv["version"] == version]
    columnNormalization = columnVersion.loc[columnVersion["model"].str.contains(normalization)]
    maxMedian = np.max(columnNormalization["median"])
    maxMedianTuple = columnNormalization[columnNormalization["median"] == maxMedian]

    return maxMedianTuple


def getMinMedianTupleByVersion(csv, version, normalization):
    columnVersion = csv.loc[csv["version"] == version]
    columnNormalization = columnVersion.loc[columnVersion["model"].str.contains(normalization)]
    minMedian = np.min(columnNormalization["median"])
    minMedianTuple = columnNormalization[columnNormalization["median"] == minMedian]

    return minMedianTuple


def getVersions(csv):
    versions = list(csv["version"].unique())
    return versions


def getTuples(csv):
    versions = getVersions(csv)
    tuples=[]
    normalizations=["Lsi", "Tfidf", "Naive"]
    for version in versions:
         maxLSIMeanTuple = getMaxMeanTupleByVersion(csv, version, normalizations[0])
         maxLSIMedianTuple = getMaxMedianTupleByVersion(csv, version, normalizations[0])
         maxTFIDFMeanTuple = getMaxMeanTupleByVersion(csv, version, normalizations[1])
         maxTFIDFMedianTuple = getMaxMedianTupleByVersion(csv, version, normalizations[1])
         maxNaiveMeanTuple = getMaxMeanTupleByVersion(csv, version, normalizations[2])
         maxNaiveMedianTuple = getMaxMedianTupleByVersion(csv, version, normalizations[2])
         minLSIMeanTuple = getMinMeanTupleByVersion(csv, version, normalizations[0])
         minLSIMedianTuple = getMinMedianTupleByVersion(csv, version, normalizations[0])
         minTFIDFMeanTuple = getMinMeanTupleByVersion(csv, version, normalizations[1])
         minTFIDFMedianTuple = getMinMedianTupleByVersion(csv, version, normalizations[1])
         minNaiveMeanTuple = getMinMeanTupleByVersion(csv, version, normalizations[2])
         minNaiveMedianTuple = getMinMedianTupleByVersion(csv, version, normalizations[2])
         tuples.append([
             version, maxLSIMeanTuple.iloc[0][1], maxLSIMeanTuple.iloc[0][2],
             maxLSIMedianTuple.iloc[0][1], maxLSIMedianTuple.iloc[0][3],
             maxTFIDFMeanTuple.iloc[0][1], maxTFIDFMeanTuple.iloc[0][2],
             maxTFIDFMedianTuple.iloc[0][1], maxTFIDFMedianTuple.iloc[0][3],
             maxNaiveMeanTuple.iloc[0][1], maxNaiveMeanTuple.iloc[0][2],
             maxNaiveMedianTuple.iloc[0][1], maxNaiveMedianTuple.iloc[0][3],
             minLSIMeanTuple.iloc[0][1], minLSIMeanTuple.iloc[0][2],
             minLSIMedianTuple.iloc[0][1], minLSIMedianTuple.iloc[0][3],
             minTFIDFMeanTuple.iloc[0][1], minTFIDFMeanTuple.iloc[0][2],
             minTFIDFMedianTuple.iloc[0][1], minTFIDFMedianTuple.iloc[0][3],
             minNaiveMeanTuple.iloc[0][1], minNaiveMeanTuple.iloc[0][2],
             minNaiveMedianTuple.iloc[0][1], minNaiveMedianTuple.iloc[0][3]
             ])
    
    return tuples


def writeTuplesCSV(projectName, tuples=[]):
     headers=["version", "highest mean LSI model", "highest LSI mean value", "highest LSI median model", "highest LSI median value", 
     "highest TFIDF mean model", "highest TFIDF mean value", "highest TFIDF median model", "highest TFIDF median value", "highest Naive mean model",
     "highest Naive mean value","highest Naive median model", "highest Naive mean value","lowest LSI mean model", "lowest LSI mean value", 
     "lowest LSI median model", "lowest LSI median value", "lowest TFIDF mean model", "lowest TFIDF mean value", "lowest TFIDF median model",
     "lowest TFIDF median value", "lowest Naive mean model", "lowest Naive mean value","lowest Naive median model","lowest Naive median value"]
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
#print(tuples)
writeTuplesCSV(projectName, tuples)