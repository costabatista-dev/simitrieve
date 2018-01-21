import os
import sys
import pandas as pd
import numpy as np


def setPath(path):
    os.chdir(path)


def getCSV(csvName):
    return pd.read_csv(csvName)


def organizeCSV(csvName):
    csv = getCSV(csvName)
    csv['sort'] = csv['version'].str.extract('(\d+\.\d+)', expand=False).astype(float)
    csv.sort_values('sort',inplace=True, ascending=True)
    csv = csv.drop('sort', axis=1)
    csv.to_csv(csvName, index=False)


path = sys.argv[1]
setPath(path)

csvName= sys.argv[2]
organizeCSV(csvName)

