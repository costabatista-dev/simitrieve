import os
import glob
import pandas as pd
import sys
from scipy.stats import mannwhitneyu
from numpy import std, mean, sqrt


def cliffsDelta(lst1,lst2,
                dull = [0.147, # small
                        0.33,  # medium
                        0.474 # large
                        ][0] ): 
  "Returns true if there are more than 'dull' differences"
  m, n = len(lst1), len(lst2)
  lst2 = sorted(lst2)
  j = more = less = 0
  for repeats,x in runs(sorted(lst1)):
    while j <= (n - 1) and lst2[j] <  x: 
      j += 1
    more += j*repeats
    while j <= (n - 1) and lst2[j] == x: 
      j += 1
    less += (n - j)*repeats
  d= (more - less) / (m*n) 
  if abs(d) < dull:
      return "neglible", d
  elif abs(d) >= 0.147 and abs(d) < 0.33:
      return "small", d
  elif abs(d) >= 0.33 and abs(d) < 0.474:
      return "medium", d
  else:
      return "large", d


def runs(lst):
  "Iterator, chunks repeated values"
  for j,two in enumerate(lst):
    if j == 0:
      one,i = two,0
    if one!=two:
      yield j - i,one
      i = j
    one=two
  yield j - i + 1,two

def setPath(path):
    os.chdir(path)


def getAllSimilaritiesCSVsNameList():
    extension="csv"
    result = [i for i in glob.glob("*.{}".format(extension))]
    return result


def getAllSimilaritiesCSVs(csvNameList=[]):
    csvSimilarityList=[]
    for csvName in csvNameList:
        csvSimilarityList.append(pd.read_csv(csvName))
    return csvSimilarityList

def getModels(csvSimilarityList=[]):
    csv=csvSimilarityList[0]
    models=csv.columns.values
    return models[4:]


def corretion(value):
    if value > 1:
        return 1.0
    else:
        return value


def getValuesByModel(model, csvsSimilarityList=[]):
    allValues=[]
    for csvSimilarity in csvsSimilaritiesList:
        values=list(csvSimilarity[model])
        for value in values:
            allValues.append(corretion(value))
    return allValues


def getMannWhitneyForModels(models=[], csvsSimilaritiesList=[]):
    result=[]
    size=len(models)

    for i in range(size):
        values_i = getValuesByModel(models[i], csvsSimilaritiesList)
        for j in range(i+1, size):
            values_j = getValuesByModel(models[j], csvsSimilaritiesList)
            statistic, pValue = mannwhitneyu(values_i, values_j)
            effectSize, dValue = cliffsDelta(values_i, values_j)
            result.append([models[i], models[j], statistic, pValue, effectSize, dValue])
    return result

def writeDataset(projectName, data=[]):
    columns=["model 1", "model 2", "statistic", "pvalue", "effect size", "|D|"]
    dataframe=pd.DataFrame(data, columns=columns)
    name=projectName + "_mannwhitney.csv"
    dataframe.to_csv(name, index=False)

path=sys.argv[1]
setPath(path)

projectName=sys.argv[2]

csvsSimilaritiesNameList=getAllSimilaritiesCSVsNameList()

csvsSimilaritiesList=getAllSimilaritiesCSVs(csvsSimilaritiesNameList)

models=getModels(csvsSimilaritiesList)

data=getMannWhitneyForModels(models,csvsSimilaritiesList)

writeDataset(projectName, data)